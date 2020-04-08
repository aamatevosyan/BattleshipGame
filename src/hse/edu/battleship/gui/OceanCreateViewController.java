package hse.edu.battleship.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class OceanCreateViewController implements Initializable {

    @FXML
    GridPane oceanPane;

    @FXML
    Button verticalButton;

    @FXML
    Button horizontalButton;

    @FXML
    Button revertButton;

    @FXML
    Button clearButton;

    @FXML
    Button okButton;

    @FXML
    Button cancelButton;

    OceanView oceanView;

    boolean isCorrect = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        oceanView = new OceanView(oceanPane);
    }
}
