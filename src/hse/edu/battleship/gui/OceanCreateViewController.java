package hse.edu.battleship.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class OceanCreateViewController implements Initializable {
    @FXML
    OceanView oceanView;

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

    boolean isOk = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
