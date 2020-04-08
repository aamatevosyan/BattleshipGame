package hse.edu.battleship.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class OceanCreateView {
    public final OceanCreateViewController controller;
    public final Stage primaryStage;

    public OceanCreateView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OceanCreateView.fxml"));

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
        primaryStage.setMinWidth(350);
        primaryStage.setMinHeight(520);
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        controller = fxmlLoader.getController();
        primaryStage.showAndWait();
    }

}
