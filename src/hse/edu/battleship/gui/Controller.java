package hse.edu.battleship.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class Controller implements Initializable {

    @FXML
    Button newGameButton;

    @FXML
    Button exitApplicationButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newGameButton.setOnAction(actionEvent -> {
            actionEvent.consume();

            List<String> choices = new ArrayList<>();
            choices.add("Solo");
            choices.add("Network");

            ChoiceDialog<String> dialog = new ChoiceDialog<>("Solo", choices);
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("images/battleship_logo.png")));
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);

            dialog.setGraphic(imageView);
            dialog.setTitle("Choice Dialog");
            dialog.setHeaderText("The battle is on the way, take a wise decision.");
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.setContentText("Choose game mode:");

            Optional<String> result = dialog.showAndWait();

            result.ifPresent(s -> {
                GameWindow gameWindow = null;
                if (s.equals("Solo")) {
                    gameWindow = new SoloGameWindow();
                } else {
                    gameWindow = new NetworkGameWindow();
                }
                gameWindow.show();
            });
        });

        exitApplicationButton.setOnAction(actionEvent -> System.exit(0));
    }
}
