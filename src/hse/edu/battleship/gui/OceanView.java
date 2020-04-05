package hse.edu.battleship.gui;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.io.IOException;

public class OceanView extends HBox {

    static final String[] COLORS = {"#ccf0fe", "#56cdfc", "#cbcbd2"};

    Button[][] oceanCells = new Button[10][10];

    GridPane gridPane;

    public OceanView() {
        super();

        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent part = null;
        try {
            part = fxmlLoader.load(getClass().getResource("OceanView.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        gridPane = (GridPane)part;

        for (int i = 1; i <= 10; i++) {
            Label label = new Label();
            label.setAlignment(Pos.CENTER);
            label.setTextAlignment(TextAlignment.CENTER);
            label.setText(Integer.toString(i - 1));
            label.setStyle("-fx-font-family: Lobster, cursive; -fx-font-weight: bold; -fx-font-size: 16px");

            label.setMaxWidth(Double.MAX_VALUE);
            label.setMaxHeight(Double.MAX_VALUE);

            gridPane.add(label, 0, i);
        }

        for (int i = 1; i <= 10; i++) {
            Label label = new Label();
            label.setAlignment(Pos.CENTER);
            label.setTextAlignment(TextAlignment.CENTER);
            label.setText(Integer.toString(i - 1));
            label.setStyle("-fx-font-family: Lobster, cursive; -fx-font-weight: bold; -fx-font-size: 16px");

            label.setMaxWidth(Double.MAX_VALUE);
            label.setMaxHeight(Double.MAX_VALUE);

            gridPane.add(label, i, 0);
        }

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                Button button = new Button();
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

                oceanCells[i - 1][j - 1] = button;
                setCellColor(i - 1, j - 1, 0);

                gridPane.add(button, i, j);
            }
        }

        getChildren().add(part);
    }

    void setCellColor(int i, int j, int state) {
        oceanCells[i][j].setStyle("-fx-background-color:" + COLORS[state] +";");
    }

    void setCellAdapter(CellAdapter cellAdapter) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button button = oceanCells[i][j];
                int finalI = i;
                int finalJ = j;
                button.setOnMouseClicked(mouseEvent -> cellAdapter.onCellClicked(finalI, finalJ));
                button.setOnMouseEntered(mouseEvent -> cellAdapter.onCellHoverStarted(finalI, finalJ));
                button.setOnMouseExited(mouseEvent -> cellAdapter.onCellHoverEnded(finalI, finalJ));
            }
        }
    }
}
