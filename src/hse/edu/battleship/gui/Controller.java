package hse.edu.battleship.gui;

import hse.edu.battleship.core.Ocean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {

    @FXML
    private void onNewGame(ActionEvent actionEvent) {
        actionEvent.consume();

        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("images/battleship_logo.png")));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        List<String> choices = new ArrayList<>();
        choices.add("Solo");
        choices.add("Network");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Solo", choices);

        dialog.setGraphic(imageView);
        dialog.setHeaderText("The battle is on the way, take a wise decision.");
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setContentText("Choose game mode:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            GameWindow gameWindow;
            if (result.get().equals("Solo")) {
                Ocean ocean = new Ocean();
                ocean.placeAllShipsRandomly();
                gameWindow = new SoloGameWindow(ocean);
                gameWindow.startNewGame();
            } else {
                Ocean ocean;

                choices = new ArrayList<>();
                choices.add("Random");
                choices.add("Custom");

                dialog = new ChoiceDialog<>("Random", choices);
                dialog.setGraphic(imageView);

                dialog.setHeaderText("Hurry up, choose your ships coordinates.");
                dialog.initStyle(StageStyle.UNDECORATED);
                dialog.setContentText("Choose selection mode:");

                result = dialog.showAndWait();

                if (result.isPresent()) {
                    if (result.get().equals("Random")) {
                        ocean = new Ocean();
                        ocean.placeAllShipsRandomly();
                    } else {
                        OceanCreateView oceanCreateView = new OceanCreateView();
                        //TODO Ships selection - OceanViewCreate
                    }

                    //TODO Network based game
                }
            }
        }
    }

    @FXML
    private void onExitApplication(ActionEvent actionEvent) {
        System.exit(0);
    }
}
