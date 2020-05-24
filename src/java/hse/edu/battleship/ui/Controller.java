package hse.edu.battleship.ui;

import hse.edu.battleship.core.Ocean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller for Main
 */
public class Controller {

    /**
     * Game mode
     */
    GameMode gameMode;

    /**
     * New game button's action event
     *
     * @param actionEvent event
     */
    @FXML
    private void onNewGame(ActionEvent actionEvent) {
        actionEvent.consume();
        System.out.println(gameMode);

        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("resources/images/battleship_logo.png")));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        if (gameMode == GameMode.Solo) {
            Ocean ocean = new Ocean();
            ocean.placeAllShipsRandomly();
            SoloGameWindow gameWindow = new SoloGameWindow(ocean);
            gameWindow.startNewGame();
        } else {
            Ocean ocean;

            List<String> choices = new ArrayList<>();
            choices.add("Random");
            choices.add("Custom");

            ChoiceDialog<String> dialog = new ChoiceDialog<>("Random", choices);
            dialog.setGraphic(imageView);

            dialog.setHeaderText("Hurry up, choose your ships coordinates.");
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.setContentText("Choose selection mode:");

            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                ocean = new Ocean();

                if (result.get().equals("Random")) {
                    ocean.placeAllShipsRandomly();
                } else {
                    OceanCreateView oceanCreateView = new OceanCreateView();
                    if (oceanCreateView.controller.isCorrect)
                        ocean = oceanCreateView.controller.oceanView.ocean;
                    else
                        ocean.placeAllShipsRandomly();
                }
                ocean.printDebug();

                if (gameMode == GameMode.Client) {
                    ClientConnectDialog connectDialog = new ClientConnectDialog(ocean);
                } else {
                    ServerConnectDialog connectDialog = new ServerConnectDialog(ocean);
                }
            }
        }
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Exit button's action on event
     *
     * @param actionEvent event
     */
    @FXML
    private void onExitApplication(ActionEvent actionEvent) {
        System.exit(0);
    }
}
