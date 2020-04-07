package hse.edu.battleship.gui;

import hse.edu.battleship.core.Human;
import hse.edu.battleship.core.Ocean;
import hse.edu.battleship.core.Playable;
import hse.edu.battleship.core.Player;
import hse.edu.battleship.gui.GameWindow;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Optional;

public class SoloGameManager implements Playable {
    Player player;
    GameWindow gameWindow;

    public SoloGameManager(GameWindow gameWindow) {
        gameWindow.controller.setCellAdapter(new CellAdapter() {
            @Override
            public void onCellClicked(int i, int j) {
                Ocean ocean = gameWindow.controller.oceanView.ocean;
                System.out.printf("Trying to shoot at (%d, %d).%n", i, j);
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
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Game is over.");
                        alert.setHeaderText(String.format("Game is over. Your score is : %s%n", Integer.toString(ocean.getShotsFired())));
                        alert.setContentText("Start new game?");
                        alert.initStyle(StageStyle.UNDECORATED);

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                            Ocean o = new Ocean();
                            o.placeAllShipsRandomly();
                            gameWindow.controller.oceanView.ocean = o;
                            gameWindow.controller.oceanView.updateOceanView();

                            gameWindow.controller.setStats(0, 10, 0, 0);
                            gameWindow.controller.detailsTextArea.clear();
                            gameWindow.controller.coordinatesTextField.clear();
                        } else {
                            gameWindow.close();
                        }
                    } else {
                        gameWindow.controller.oceanView.updateOceanView();
                        gameWindow.controller.setStats(ocean.getShotsFired(), 10 - ocean.getShipsSunk(), ocean.getShipsDamaged(), ocean.getShipsSunk());
                    }
                }
            }
        });
    }

    /**
     * @param row the row to look at
     * @param column the column to look at
     * @return sink message of the ship
     */
    public String getSinkMessage(int row, int column, Ocean ocean) {
        return "You just sunk a " + ocean.getAt(row, column).getShipType();
    }

    @Override
    public void doTurn(Player player, int i, int j) {
        gameWindow.controller.oceanView.shootAt(i, j);
    }

    @Override
    public void registerPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean isGameStarted() {
        return true;
    }
}
