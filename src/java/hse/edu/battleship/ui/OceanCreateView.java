package hse.edu.battleship.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * OceanCreateView
 */
public class OceanCreateView {
    /**
     * Controller for OceanCreateView
     */
    final OceanCreateViewController controller;

    /**
     * PrimaryStage
     */
    final Stage primaryStage;

    /**
     * Default constructor
     */
    public OceanCreateView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/fxml/OceanCreateView.fxml"));

        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().setAll(new Image(getClass().getResourceAsStream("resources/images/battleship_logo.png")));
        primaryStage.setTitle("BattleShip Game - Arrr!!!");
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(520);
        primaryStage.initStyle(StageStyle.UNDECORATED);


        controller = fxmlLoader.getController();

        /*
         * Key handling
         */
        root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.V) {
                controller.verticalButton.fire();
                ev.consume();
            } else if (ev.getCode() == KeyCode.H) {
                controller.horizontalButton.fire();
                ev.consume();
            }
            if (ev.getCode() == KeyCode.R) {
                controller.revertButton.fire();
                ev.consume();
            }
            if (ev.getCode() == KeyCode.C) {
                controller.clearButton.fire();
                ev.consume();
            }
        });

        primaryStage.showAndWait();
    }

}
