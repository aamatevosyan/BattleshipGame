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
public class SoloGameWindow {
    /**
     * GameWindow controller
     */
    final SoloGameWindowController controller;
    /**
     * Primary stage
     */
    final Stage primaryStage;
    /**
     * SoloGameManager
     */
    private SoloGameManager soloGameManager;

    /**
     * Default constructor
     *
     * @param ocean ocean
     */
    public SoloGameWindow(Ocean ocean) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/fxml/SoloGameWindow.fxml"));

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
    }

    /**
     * Starts new game
     */
    public void startNewGame() {
        controller.setStats();
        soloGameManager = new SoloGameManager(this);
    }
}
