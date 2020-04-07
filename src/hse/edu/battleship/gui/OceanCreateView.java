package hse.edu.battleship.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class OceanCreateView extends Stage {
    OceanCreateViewController controller;

    public OceanCreateView() {
        super();
        getIcons().setAll(new Image(getClass().getResourceAsStream("images/battleship_logo.png")));
        setTitle("BattleShip Game - Arrr!!!");

        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent part = null;
        try {
            part = fxmlLoader.load(getClass().getResource("OceanCreateView.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(part);
        controller = (OceanCreateViewController) fxmlLoader.getController();

        setScene(scene);
        setMinWidth(600);
        setMinHeight(600);
    }
}
