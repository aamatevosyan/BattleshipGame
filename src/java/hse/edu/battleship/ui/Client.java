package hse.edu.battleship.ui;

import hse.edu.battleship.core.Ocean;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Client
 */
public class Client extends Thread {

    /**
     * Connection variables
     */
    final String ip;
    final int port;
    final String name;
    String enemyName;
    Stage stage;

    Socket s;
    DataInputStream dis;
    DataOutputStream dos;

    Ocean ocean;

    Broadcaster toManager;
    Broadcaster fromManager;
    ClientGameManager clientGameManager;
    PrintStreamCapturer printStream;
    AtomicBoolean isGameOver;

    /**
     * Default constructor
     *
     * @param ip   ip address
     * @param port port
     * @param name player's name
     */
    public Client(String ip, int port, String name) {
        this.ip = ip;
        this.port = port;
        this.name = name;
        isGameOver = new AtomicBoolean(false);

        try {
            s = new Socket(ip, port);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            Tools.showError("Error!!!", e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            /*
             * Greeting
             */
            String tmp = dis.readUTF();
            if (!Messenger.isGreetSucceeded(tmp)) {
                throw new Exception(Messenger.NOT_SUPPORTED_CODE);
            }

            /*
            Setup
             */
            enemyName = Messenger.getNameFromGreet(tmp);
            dos.writeUTF(Messenger.createGreetMessage(name));
            System.out.println(enemyName);

            tmp = dis.readUTF();
            dos.writeUTF(Messenger.createSetupMessage(ocean));
            Ocean enemyOcean = Messenger.getOcean(tmp);
            enemyOcean.printDebug();
            ocean.setShown(true);

            toManager = new Broadcaster(false);
            fromManager = new Broadcaster(false);

            Platform.runLater(() -> {
                clientGameManager = new ClientGameManager(new NetworkGameWindow(ocean, enemyOcean));
                clientGameManager.isTurnAvailable = new AtomicBoolean(true);
                clientGameManager.fromServer = toManager;
                clientGameManager.toServer = fromManager;
                clientGameManager.name = name;
                clientGameManager.enemyName = enemyName;
                clientGameManager.gameWindow.primaryStage.setTitle(clientGameManager.gameWindow.primaryStage.getTitle() + " - " + name);
                clientGameManager.gameWindow.controller.oceanView.updateOceanView();
                clientGameManager.start();
                stage.close();
            });

            /*
            Waiting for GUI
             */
            while (clientGameManager == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            printStream = clientGameManager.gameWindow.controller.printStreamCapturer;

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
                                Tools.showInfo("Game over", String.format("%s had shoot %d times.\n%s had shoot %d times.\n", name, score, enemyName, score));
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
            Manager-Client communication
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

                        isGameOver.set(true);
                        dos.writeUTF(fromManager.message);

                        Platform.runLater(() -> {
                            int score = Messenger.getScore(fromManager.message);
                            Tools.showInfo("Game over", String.format("%s had shoot %d times.\n%s had shoot %d times.\n", name, score, enemyName, score - 1));
                            System.exit(0);
                        });

                        break;
                    } else {
                        Messenger.getShootDetails(fromManager.message).print(name, printStream);
                    }

                    dos.writeUTF(fromManager.message);
                    fromManager.initialCondition.set(false);
                    fromManager.notify();
                }
            }

            dis.close();
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
