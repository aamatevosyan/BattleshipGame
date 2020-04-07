package hse.edu.battleship.gui;

import hse.edu.battleship.core.Ocean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class GameWindow extends Stage {
    public GameWindowController controller;

    public GameWindow(Ocean ocean) {
        super();

        getIcons().setAll(new Image(getClass().getResourceAsStream("images/battleship_logo.png")));
        setTitle("BattleShip Game - Arrr!!!");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));

        Parent part = null;
        try {
            part = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(part);
        controller = fxmlLoader.getController();

        setScene(scene);
        setMinWidth(600);
        setMinHeight(600);
    }

    public void startNewGame() {
        show();
    }
}
