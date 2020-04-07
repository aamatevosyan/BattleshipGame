package hse.edu.battleship.gui;

import hse.edu.battleship.core.Ocean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class GameWindowController implements Initializable {
    @FXML
    TextArea statsTextArea;

    @FXML
    TextField coordinatesTextField;

    @FXML
    Button shootButton;

    @FXML
    TextArea detailsTextArea;

    @FXML
    GridPane oceanGridPane;

    OceanView oceanView;

    PrintStreamCapturer printStreamCapturer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        shootButton.setDefaultButton(true);
        oceanView = new OceanView(oceanGridPane);
        printStreamCapturer = new PrintStreamCapturer(detailsTextArea, System.out);
        System.setOut(printStreamCapturer);
        setStats(0, 10, 0 , 0);
    }

    public void setStats(int totalShoots, int aliveShipsCount, int damagedShipsCount, int sunkShipsCount) {
        statsTextArea.clear();
        statsTextArea.appendText("Total shoots: " + Integer.toString(totalShoots) + "\n");
        statsTextArea.appendText("Alive: " + Integer.toString(aliveShipsCount) + "\n");
        statsTextArea.appendText("Damaged: " + Integer.toString(damagedShipsCount) + "\n");
        statsTextArea.appendText("Sunk: " + Integer.toString(sunkShipsCount));
    }

    public void showError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    public void setCellAdapter(CellAdapter cellAdapter) {
        oceanView.setCellAdapter(cellAdapter);

        shootButton.setOnAction(actionEvent -> {
            String str = coordinatesTextField.getText();
            try {
                int i = Integer.parseInt(str.substring(0, 1));
                int j = Integer.parseInt(str.substring(2));

                if (str.charAt(1) != ' ')
                    throw new IllegalArgumentException("Invalid arguments were passed.");

                cellAdapter.onCellClicked(i, j);
            }
            catch (Exception ex) {
                showError("Invalid coordinates were typed. ", "Please type two numbers from [0, 9] separated by space.");
            }
            finally {
                coordinatesTextField.clear();
            }
        });
    }
}
