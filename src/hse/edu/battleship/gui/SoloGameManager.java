package hse.edu.battleship.gui;

import hse.edu.battleship.core.Ocean;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

import java.util.Optional;

public class SoloGameManager {

    GameWindow gameWindow;

    public SoloGameManager(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        gameWindow.controller.setCellAdapter((i, j) -> {
            Ocean ocean = gameWindow.controller.oceanView.ocean;
            System.out.printf("Trying to shoot at (%d, %d)%n", i, j);

            if (ocean.getAt(i, j).isHit(i, j)) {
                gameWindow.controller.showError("Oops, selected ship had been damaged.", "Please select another cell.");
            } else {
                if (ocean.shootAt(i, j)) {
                    if (ocean.getAt(i, j).isSunk())
                        System.out.println(getSinkMessage(i, j, ocean));
                    else
                        System.out.println("You just hit a ship :)");
                } else {
                    System.out.println("Oops, you missed :(");
                }

                System.out.println();

                if (ocean.isGameOver()) {
                    onWin();
                } else {
                    gameWindow.controller.oceanView.updateOceanView();
                    gameWindow.controller.setStats();
                }
            }
        });
    }

    /**
     * @param row    the row to look at
     * @param column the column to look at
     * @return sink message of the ship
     */
    public String getSinkMessage(int row, int column, Ocean ocean) {
        return "You just sunk a " + ocean.getAt(row, column).getShipType();
    }

    public void onWin() {
        Ocean ocean = gameWindow.controller.oceanView.ocean;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game is over.");
        alert.setHeaderText(String.format("Game is over. Your score is : %s%n", ocean.getShotsFired()));
        alert.setContentText("Start new game?");
        alert.initStyle(StageStyle.UNDECORATED);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                Ocean o = new Ocean();
                o.placeAllShipsRandomly();
                gameWindow.controller.oceanView.ocean = o;
                gameWindow.controller.oceanView.resetOceanView();

                gameWindow.controller.setStats();
                gameWindow.controller.detailsTextArea.clear();
                gameWindow.controller.coordinatesTextField.clear();
            } else {
                gameWindow.primaryStage.close();
            }
        }
    }
}
