package hse.edu.battleship.gui;

import hse.edu.battleship.core.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class OceanCreateViewController implements Initializable {

    @FXML
    GridPane oceanPane;

    final Ship[] shipArray = {new Battleship(), new Cruiser(), new Cruiser(),
            new Destroyer(), new Destroyer(), new Destroyer(), new Submarine(),
            new Submarine(), new Submarine(), new Submarine()};
    @FXML
    public Button verticalButton;
    @FXML
    public Button horizontalButton;
    @FXML
    public Button revertButton;

    @FXML
    Button okButton;

    @FXML
    Button cancelButton;

    OceanView oceanView;
    @FXML
    public Button clearButton;
    int currentIndex = 0;
    boolean horizontal = true;

    boolean isCorrect = false;

    Ship getNextShip() {
        if (currentIndex >= 10)
            return null;
        final Ship ship = shipArray[currentIndex];
        ship.reset();
        return ship;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        oceanView = new OceanView(oceanPane);
        oceanView.ocean = new Ocean();
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

    @FXML
    void onCancel(ActionEvent actionEvent) {
        isCorrect = false;
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void onVertical(ActionEvent actionEvent) {
        horizontal = false;
    }

    @FXML
    void onHorizontal(ActionEvent actionEvent) {
        horizontal = true;
    }

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

    @FXML
    void onClear(ActionEvent actionEvent) {
        int count = currentIndex;
        for (int i = 0; i < count; i++)
            onRevert(actionEvent);
    }

}
