package hse.edu.battleship.ui;

import hse.edu.battleship.core.Ocean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * SoloGameWindow
 */
public class NetworkGameWindow {
    /**
     * GameWindow controller
     */
    final NetworkGameWindowController controller;
    /**
     * Primary stage
     */
    final Stage primaryStage;
    /**
     * SoloGameManager
     */
    private NetworkGameManager networkGameManager;

    /**
     * Default constructor
     *
     * @param ocean ocean
     */
    public NetworkGameWindow(Ocean ocean, Ocean enemyOcean) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/fxml/NetworkGameWindow.fxml"));

        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().setAll(new Image(getClass().getResourceAsStream("resources/images/battleship_logo.png")));
        primaryStage.setTitle("Battleship Game - Arrr!!!");
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);
        primaryStage.show();

        controller = fxmlLoader.getController();
        controller.oceanView.ocean = ocean;
        controller.enemyOceanView.ocean = enemyOcean;
    }

    /**
     * Starts new game
     */
    public void startNewGame() {
        controller.setStats();
    }
}
