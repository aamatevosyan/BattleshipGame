package hse.edu.battleship.ui;

import hse.edu.battleship.core.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Controller for OceanCreateView
 */
public class OceanCreateViewController {
    /**
     * Ships array
     */
    final Ship[] shipArray = {new Battleship(), new Cruiser(), new Cruiser(),
            new Destroyer(), new Destroyer(), new Destroyer(), new Submarine(),
            new Submarine(), new Submarine(), new Submarine()};
    /**
     * OceanPane
     */
    @FXML
    GridPane oceanPane;
    /**
     * OceanView
     */
    OceanView oceanView;
    /**
     * Current index
     */
    int currentIndex = 0;

    /**
     * True if current ship is horizontal, otherwise false
     */
    boolean horizontal = true;

    /**
     * True if created ocean is correct, otherwise false
     */
    boolean isCorrect = false;

    /**
     * Gets next ship from ship array
     *
     * @return next ship
     */
    Ship getNextShip() {
        if (currentIndex >= 10)
            return null;
        final Ship ship = shipArray[currentIndex];
        ship.reset();
        return ship;
    }

    /**
     * Initializes view
     */
    public void initialize() {
        oceanView = new OceanView(oceanPane);
        oceanView.ocean = new Ocean();

        /*
         * Sets CellAdapter
         */
        oceanView.setCellAdapter((i, j) -> {
            Ship ship;
            if ((ship = getNextShip()) != null && ship.okToPlaceShipAt(i, j, horizontal, oceanView.ocean)) {
                currentIndex++;

                ship.placeShipAt(i, j, horizontal, oceanView.ocean);
                oceanView.ocean.forceSunkShip(i, j, horizontal, ship.getLength());
                oceanView.updateOceanView();
            } else {
                //TODO Add preview and error handling
            }
        });
    }

    /**
     * Action for Ok button
     *
     * @param actionEvent action event
     */
    @FXML
    void onOk(ActionEvent actionEvent) {
        isCorrect = currentIndex == 10;

        if (isCorrect) {
            for (Ship ship :
                    shipArray) {
                ship.reset();
            }
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setTitle("Error");
            alert.setHeaderText("The created ocean is not valid!!!");
            alert.setContentText("Please add missing ships.");

            alert.showAndWait();
        }
    }

    /**
     * Action for Cancel button
     *
     * @param actionEvent action event
     */
    @FXML
    void onCancel(ActionEvent actionEvent) {
        isCorrect = false;
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Action for Vertical button
     *
     * @param actionEvent action event
     */
    @FXML
    void onVertical(ActionEvent actionEvent) {
        horizontal = false;
    }

    /**
     * Action for Horizontal button
     *
     * @param actionEvent action event
     */
    @FXML
    void onHorizontal(ActionEvent actionEvent) {
        horizontal = true;
    }

    /**
     * Action for Revert button
     *
     * @param actionEvent action event
     */
    @FXML
    void onRevert(ActionEvent actionEvent) {
        if (currentIndex > 0) {
            currentIndex--;
            Ship ship = shipArray[currentIndex];
            ship.reset();
            oceanView.ocean.removeShip(ship.getBowRow(), ship.getBowColumn(), ship.isHorizontal(), ship.getLength());
            oceanView.updateOceanView();
        }
    }

    /**
     * Action for Clear button
     *
     * @param actionEvent action event
     */
    @FXML
    void onClear(ActionEvent actionEvent) {
        int count = currentIndex;
        for (int i = 0; i < count; i++)
            onRevert(actionEvent);
    }

}
