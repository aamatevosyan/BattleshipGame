package hse.edu.battleship.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Controller for GameWindow
 */
public class NetworkGameWindowController {
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

    @FXML
    Button quitButton;

    /**
     * Ocean's GirdPane
     */
    @FXML
    GridPane oceanGridPane;

    @FXML
    GridPane enemyOceanGridPane;

    @FXML
    Label youLabel;

    @FXML
    Label enemyLabel;

    /**
     * PrintStreamCapturer
     */
    PrintStreamCapturer printStreamCapturer;

    /**
     * OceanView
     */
    OceanView oceanView;

    OceanView enemyOceanView;

    /**
     * Initializes view
     */
    public void initialize() {
        oceanView = new OceanView(oceanGridPane);
        enemyOceanView = new OceanView(enemyOceanGridPane);
        printStreamCapturer = new PrintStreamCapturer(detailsTextArea, System.out);
    }

    /**
     * Sets stats
     */
    void setStats() {
        statsTextArea.setText(enemyOceanView.ocean.getStats());
    }

    /**
     * Sets CellAdapter
     *
     * @param cellAdapter CellAdapter
     */
    void setCellAdapter(CellAdapter cellAdapter) {
        enemyOceanView.setCellAdapter(cellAdapter);

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

                Platform.runLater(() -> {
                    cellAdapter.onCellClicked(i, j);
                });
            } catch (Exception ex) {
                Tools.showError("Invalid coordinates were typed. ", "Please type two numbers from [0, 9] separated by space.");
            } finally {
                coordinatesTextField.clear();
            }
        });
    }

    public void showCurrentPlayer(boolean isTurnAvailable) {
        if (isTurnAvailable) {
            youLabel.setTextFill(Color.web("#baf1a1"));
            enemyLabel.setTextFill(Color.web("#6b7478"));
        } else {
            enemyLabel.setTextFill(Color.web("#baf1a1"));
            youLabel.setTextFill(Color.web("#6b7478"));
        }
    }
}
