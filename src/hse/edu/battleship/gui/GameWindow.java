package hse.edu.battleship.gui;

import hse.edu.battleship.core.Ocean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameWindow {
    public final GameWindowController controller;
    public final Stage primaryStage;

    public GameWindow(Ocean ocean) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));

        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().setAll(new Image(getClass().getResourceAsStream("images/battleship_logo.png")));
        primaryStage.setTitle("BattleShip Game - Arrr!!!");
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);
        primaryStage.show();

        controller = fxmlLoader.getController();
        controller.oceanView.ocean = ocean;
    }

    public void startNewGame() {
        controller.setStats();
    }
}
