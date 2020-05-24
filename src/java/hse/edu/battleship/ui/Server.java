package hse.edu.battleship.ui;

import hse.edu.battleship.core.Ocean;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Server
 */
public class Server extends Thread {

    /**
     * Connection variables
     */

    final int port;
    final String name;
    final ExecutorService executorService = Executors.newFixedThreadPool(4);
    boolean isFirstClient;
    ServerSocket ss;
    Ocean ocean;
    Ocean enemyOcean;
    Stage stage;
    AtomicBoolean isRunning;
    AtomicBoolean isClientConnected;
    AtomicBoolean isGameOver;

    /**
     * Default constructor
     *
     * @param port port
     * @param name player's name
     */
    public Server(int port, String name) {
        this.port = port;
        this.name = name;
        isFirstClient = true;
        isRunning = new AtomicBoolean(false);
        isClientConnected = new AtomicBoolean(false);
        isGameOver = new AtomicBoolean(false);

        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            Tools.showError("Error!!!", e.getMessage());
        }
    }

    @Override
    public void run() {
        System.out.println("Server started");
        System.out.println(Thread.currentThread().getName() + " started.");
        try {
            isRunning.set(true);
            while (isRunning.get()) {
                Socket s = null;
                try {
                    // socket object to receive incoming client requests
                    s = ss.accept();

                    System.out.println("A new client is connected : " + s);

                    // obtaining input and out streams
                    DataInputStream dis = new DataInputStream(s.getInputStream());
                    DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                    System.out.println("Assigning new thread for this client");

                    // create a new thread object
                    ClientHandler clientHandler = new ClientHandler(s, dis, dos, isFirstClient, name);

                    // Invoking the start() method
                    executorService.execute(clientHandler);

                    if (isFirstClient)
                        isFirstClient = false;
                } catch (Exception e) {
                    try {
                        if (s != null)
                            s.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            interrupt();
        }

        System.out.println("Server ended");
        System.out.println(Thread.currentThread().getName() + " ended.");
    }

    @Override
    public void interrupt() {
        super.interrupt();
        try {
            ss.close();
            executorService.shutdown();
            isRunning.set(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Class for handling all clients
     */
    class ClientHandler extends Thread {

        final DataInputStream dis;
        final DataOutputStream dos;
        final Socket s;
        final boolean isFirstClient;
        final String name;
        String enemyName;

        Broadcaster toManager;
        Broadcaster fromManager;
        ServerGameManager serverGameManager;

        /**
         * Default constructor
         *
         * @param s             socket
         * @param dis           input stream
         * @param dos           output stream
         * @param isFirstClient true, if the first client
         * @param name          player's name
         */
        public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, boolean isFirstClient, String name) {
            this.s = s;
            this.dis = dis;
            this.dos = dos;
            this.isFirstClient = isFirstClient;
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " started");
            boolean correctClient = false;

            try {
                /*
                 * Checks for one client option
                 */
                if (!isFirstClient) {
                    dos.writeUTF(Messenger.NOT_SUPPORTED_CODE);
                } else {
                    /*
                    Greeting
                     */
                    isClientConnected.set(true);
                    correctClient = true;
                    dos.writeUTF(Messenger.createGreetMessage(name));
                    enemyName = Messenger.getNameFromGreet(dis.readUTF());
                    System.out.println(enemyName);

                    /*
                    Setup
                     */
                    dos.writeUTF(Messenger.createSetupMessage(ocean));
                    String tmp = dis.readUTF();
                    enemyOcean = Messenger.getOcean(tmp);
                    enemyOcean.printDebug();
                    ocean.setShown(true);

                    PrintStreamCapturer printStream;

                    toManager = new Broadcaster(false);
                    fromManager = new Broadcaster(false);
                    Platform.runLater(() -> {
                        serverGameManager = new ServerGameManager(new NetworkGameWindow(ocean, enemyOcean));
                        serverGameManager.isTurnAvailable = new AtomicBoolean(false);
                        serverGameManager.fromServer = toManager;
                        serverGameManager.toServer = fromManager;
                        serverGameManager.name = name;
                        serverGameManager.enemyName = enemyName;
                        serverGameManager.gameWindow.primaryStage.setTitle(serverGameManager.gameWindow.primaryStage.getTitle() + " - " + name);
                        serverGameManager.gameWindow.controller.oceanView.updateOceanView();
                        serverGameManager.start();
                        stage.setTitle("Close");
                        stage.close();
                    });

                    /*
                    Waits for GUI
                     */
                    while (serverGameManager == null) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    printStream = serverGameManager.gameWindow.controller.printStreamCapturer;

                    /*
                    Client-Server communication
                     */
                    new Thread(() -> {
                        try {
                            while (true) {
                                String received = dis.readUTF();
                                System.out.println(received);

                                if (Messenger.isWinMessage(received)) {
                                    System.out.println("onWin");
                                    int score = Messenger.getScore(received);
                                    isGameOver.set(true);
                                    Platform.runLater(() -> {
                                        Tools.showInfo("Game over", String.format("%s had shoot %d times.\n%s had shoot %d times.\n", name, score, enemyName, score - 1));
                                        System.exit(0);
                                    });
                                    break;
                                }

                                synchronized (toManager) {
                                    while (toManager.initialCondition.get()) {
                                        try {
                                            toManager.wait();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    toManager.message = received;

                                    Messenger.getShootDetails(toManager.message).print(enemyName, printStream);
                                    toManager.initialCondition.set(true);
                                    toManager.notify();
                                }
                            }
                        } catch (NullPointerException | SocketException e) {
                            if (!isGameOver.get()) {
                                Platform.runLater(() -> {
                                    Tools.showInfo("Information", enemyName + " had quited a game.");
                                    System.exit(0);
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();

                    /*
                    Manager-Server communication
                     */
                    while (true) {
                        synchronized (fromManager) {
                            while (!fromManager.initialCondition.get()) {
                                try {
                                    fromManager.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            System.out.println("From manager: " + fromManager.message);
                            if (Messenger.isWinMessage(fromManager.message)) {
                                System.out.println("onWin");

                                dos.writeUTF(fromManager.message);
                                isGameOver.set(true);
                                int score = Messenger.getScore(fromManager.message);
                                Platform.runLater(() -> {
                                    Tools.showInfo("Game over", String.format("%s had shoot %d times.\n%s had shoot %d times.\n", name, score, enemyName, score));
                                    System.exit(0);
                                });
                            } else {
                                Messenger.getShootDetails(fromManager.message).print(name, printStream);
                            }

                            dos.writeUTF(fromManager.message);
                            fromManager.initialCondition.set(false);
                            fromManager.notify();
                        }
                    }
                }

                dis.close();
                dos.close();

            } catch (SocketException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*
            Exit handling if client is correct
             */
            if (correctClient && !isGameOver.get()) {
                Platform.runLater(() -> {
                    Tools.showInfo("Information", enemyName + " had quited a game.");
                    System.exit(0);
                });
            }

            System.out.println(Thread.currentThread().getName() + " exited");
        }
    }
}
