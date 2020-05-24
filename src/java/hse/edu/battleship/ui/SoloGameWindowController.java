package hse.edu.battleship.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Controller for GameWindow
 */
public class SoloGameWindowController {
    /**
     * Coordinates TextField
     */
    @FXML
    TextField coordinatesTextField;

    /**
     * Details TextArea
     */
    @FXML
    TextArea detailsTextArea;

    /**
     * Stats TextArea
     */
    @FXML
    TextArea statsTextArea;

    /**
     * Shoot Button
     */
    @FXML
    Button shootButton;

    /**
     * Ocean's GirdPane
     */
    @FXML
    GridPane oceanGridPane;

    /**
     * PrintStreamCapturer
     */
    PrintStreamCapturer printStreamCapturer;

    /**
     * OceanView
     */
    OceanView oceanView;

    /**
     * Initializes view
     */
    public void initialize() {
        oceanView = new OceanView(oceanGridPane);
        printStreamCapturer = new PrintStreamCapturer(detailsTextArea, System.out);
        System.setOut(printStreamCapturer);
    }

    /**
     * Sets stats
     */
    void setStats() {
        statsTextArea.setText(oceanView.ocean.getStats());
    }

    /**
     * Sets CellAdapter
     *
     * @param cellAdapter CellAdapter
     */
    void setCellAdapter(CellAdapter cellAdapter) {
        oceanView.setCellAdapter(cellAdapter);

        /*
         * Shoot Button's on action event
         */
        shootButton.setOnAction(actionEvent -> {
            String str = coordinatesTextField.getText();
            try {
                int i = Integer.parseInt(str.substring(0, 1));
                int j = Integer.parseInt(str.substring(2));

                if (str.charAt(1) != ' ')
                    throw new IllegalArgumentException("Invalid arguments were passed.");

                cellAdapter.onCellClicked(i, j);
            } catch (Exception ex) {
                Tools.showError("Invalid coordinates were typed. ", "Please type two numbers from [0, 9] separated by space.");
            } finally {
                coordinatesTextField.clear();
            }
        });
    }
}
