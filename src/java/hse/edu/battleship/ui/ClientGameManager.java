package hse.edu.battleship.ui;

import hse.edu.battleship.core.Ocean;
import javafx.application.Platform;

import java.util.concurrent.atomic.AtomicBoolean;

public class ClientGameManager extends Thread {
    /**
     * GameWindow
     */
    final NetworkGameWindow gameWindow;
    Broadcaster toServer;
    Broadcaster fromServer;
    String name;
    String enemyName;

    AtomicBoolean isTurnAvailable;
    int score = 0;

    /**
     * Default constructor
     *
     * @param gameWindow game's window
     */
    public ClientGameManager(NetworkGameWindow gameWindow) {
        this.gameWindow = gameWindow;
        gameWindow.controller.showCurrentPlayer(true);

        /*
         * Sets cell adapter
         */
        gameWindow.controller.setCellAdapter((i, j) -> {
            if (!isTurnAvailable.get()) {
                Tools.showError("Error!!!", "It's not your turn, please wait....");
                return;
            }

            synchronized (toServer) {
                while (toServer.initialCondition.get()) {
                    try {
                        toServer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Ocean ocean = gameWindow.controller.enemyOceanView.ocean;
                String message = "";

                if (ocean.getAt(i, j).isHit(i, j)) {
                    Tools.showError("Oops, selected ship had been damaged.", "Please select another cell.");
                    return;
                } else {
                    if (ocean.shootAt(i, j)) {
                        if (ocean.getAt(i, j).isSunk())
                            message = Messenger.createShootMessage(ShootDetails.SUNK_CODE, i, j);
                        else
                            message = Messenger.createShootMessage(ShootDetails.DAMAGED_CODE, i, j);
                    } else {
                        message = Messenger.createShootMessage(ShootDetails.EMPTY_CODE, i, j);
                    }

                    isTurnAvailable.set(false);
                    gameWindow.controller.showCurrentPlayer(false);


                    if (ocean.isGameOver()) {
                        message = Messenger.createWinMessage(ocean.getShotsFired());
                    } else {
                        Platform.runLater(() -> {
                            gameWindow.controller.enemyOceanView.updateOceanView();
                            gameWindow.controller.setStats();
                        });
                    }
                }

                toServer.message = message;
                toServer.initialCondition.set(true);
                toServer.notify();
            }
        });

        gameWindow.controller.quitButton.setOnAction(actionEvent -> {
            Platform.runLater(() -> {
                Tools.showInfo("Information", name + " had quited a game.");
                System.exit(0);
            });
        });

        gameWindow.primaryStage.setOnCloseRequest(windowEvent -> {
            Platform.runLater(() -> {
                Tools.showInfo("Information", name + " had quited a game.");
                System.exit(0);
            });
        });
    }

    @Override
    public void run() {
        while (true) {
            synchronized (fromServer) {
                while (!fromServer.initialCondition.get()) {
                    try {
                        fromServer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(fromServer.message);
                if (Messenger.isShootMessage(fromServer.message)) {
                    ShootDetails shootDetails = Messenger.getShootDetails(fromServer.message);

                    Ocean ocean = gameWindow.controller.oceanView.ocean;
                    ocean.shootAt(shootDetails.i, shootDetails.j);
                    ocean.print();

                    Platform.runLater(() -> {
                        gameWindow.controller.oceanView.updateOceanView();
                        gameWindow.controller.showCurrentPlayer(true);
                    });
                }
                isTurnAvailable.set(true);
                fromServer.initialCondition.set(false);
                fromServer.notify();
            }
        }
    }


}
