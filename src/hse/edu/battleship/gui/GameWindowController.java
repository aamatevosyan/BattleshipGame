package hse.edu.battleship.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

public class GameWindowController {
    @FXML
    TextArea statsTextArea;

    @FXML
    public TextField coordinatesTextField;

    @FXML
    Button shootButton;

    @FXML
    public TextArea detailsTextArea;

    @FXML
    GridPane oceanGridPane;

    public OceanView oceanView;

    PrintStreamCapturer printStreamCapturer;

    public void initialize() {
        oceanView = new OceanView(oceanGridPane);
        printStreamCapturer = new PrintStreamCapturer(detailsTextArea, System.out);
        System.setOut(printStreamCapturer);
    }

    public void setStats() {
        statsTextArea.setText(oceanView.ocean.getStats());
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
            } catch (Exception ex) {
                showError("Invalid coordinates were typed. ", "Please type two numbers from [0, 9] separated by space.");
            } finally {
                coordinatesTextField.clear();
            }
        });
    }
}
