package hse.edu.battleship.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

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
    OceanView oceanView;

    PrintStreamCapturer printStreamCapturer;

    static final String[] COLORS = {"#ccf0fe", "#56cdfc", "#cbcbd2"};

    Button[][] oceanCells = new Button[10][10];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        printStreamCapturer = new PrintStreamCapturer(statsTextArea, System.out);

        setStats(0, 10, 0 , 0);
    }

    void setStats(int totalShoots, int aliveShipsCount, int damagedShipsCount, int sunkShipsCount) {
        statsTextArea.clear();
        statsTextArea.appendText("Total shoots: " + Integer.toString(totalShoots) + "\n");
        statsTextArea.appendText("Alive ships: " + Integer.toString(aliveShipsCount) + "\n");
        statsTextArea.appendText("Damaged ships: " + Integer.toString(damagedShipsCount) + "\n");
        statsTextArea.appendText("Sunk ships: " + Integer.toString(sunkShipsCount) + "\n");
    }

    void setCellAdapter(CellAdapter cellAdapter) {
        oceanView.setCellAdapter(cellAdapter);

        shootButton.setOnAction(actionEvent -> {
            String str = coordinatesTextField.getText();
            try {
                int i = Integer.parseInt(str.substring(0, 1));
                int j = Integer.parseInt(str.substring(2));

                if (str.charAt(1) != ' ')
                    throw new Exception();

                cellAdapter.onCellClicked(i, j);
            }
            catch (Exception ex) {
                System.out.println("Error");
            }
        });
    }


}
