package hse.edu.battleship.gui;

import hse.edu.battleship.core.Ocean;
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

            if ( result.isPresent() )
            {
                GameWindow gameWindow = null;
                if (result.get().equals("Solo")) {

//                    SoloGameManager soloGameManager = new SoloGameManager(gameWindow);

//                    Ocean humanOcean = new Ocean();
//                    humanOcean.setUpOcean();
//
//                    Ocean robotOcean = new Ocean();
//                    robotOcean.placeAllShipsRandomly();
//
//                    Robot human = new Robot(soloGameManager);
//                    human.name = "Captain Jack Sparrow";
//                    human.enemyOcean = robotOcean;
//                    soloGameManager.registerPlayer(human);
//
//                    Robot robot = new Robot(soloGameManager);
//                    robot.name = "WALL-E";
//                    robot.enemyOcean = humanOcean;
//                    soloGameManager.registerPlayer(robot);



                    Ocean ocean = new Ocean();
                    ocean.placeAllShipsRandomly();
                    gameWindow = new SoloGameWindow(ocean);
                    gameWindow.startNewGame();

//                    human.doTurn();
                    //robot.doTurn();
                } else {
                    //TODO Network based game
                }
            }
        });

        exitApplicationButton.setOnAction(actionEvent -> System.exit(0));
    }
}
