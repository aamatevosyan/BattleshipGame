package hse.edu.battleship.ui;

import hse.edu.battleship.core.Ocean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * GameWindow class
 */
public class GameWindow {
    /**
     * GameWindow controller
     */
    final GameWindowController controller;

    /**
     * Primary stage
     */
    final Stage primaryStage;

    /**
     * Default constructor
     *
     * @param ocean ocean
     */
    public GameWindow(Ocean ocean) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/fxml/GameWindow.fxml"));

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
    void startNewGame() {
        controller.setStats();
    }
}
