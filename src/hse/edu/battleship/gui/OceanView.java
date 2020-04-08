package hse.edu.battleship.gui;

import hse.edu.battleship.core.Ocean;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class OceanView {
    final Button[][] oceanCells = new Button[10][10];

    Ocean ocean;

    public OceanView(GridPane gridPane) {
        gridPane.getStylesheets().add(getClass().getResource("OceanView.css").toExternalForm());

        for (int i = 1; i <= 10; i++) {
            Label label = new Label();
            label.setText(Integer.toString(i - 1));
            gridPane.add(label, 0, i);
        }

        for (int i = 1; i <= 10; i++) {
            Label label = new Label();
            label.setText(Integer.toString(i - 1));
            gridPane.add(label, i, 0);
        }

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                Button button = new Button();

                int finalI = i - 1;
                int finalJ = j - 1;
                oceanCells[finalI][finalJ] = button;

                button.hoverProperty().addListener((ov, oldValue, newValue) -> {
                    if (newValue)
                        oceanCells[finalI][finalJ].requestFocus();
                });

                setCellNeutral(finalI, finalJ);
                gridPane.add(button, j, i);
            }
        }
    }

    private void setCellColor(int i, int j) {
        String code = ocean.codeAt(i, j);

        switch (code) {
            case ".":
                setCellNeutral(i, j);
                break;
            case "-":
                setCellEmpty(i, j);
                break;
            case "S":
                setCellDamaged(i, j);
                break;
            case "X":
                setCellSunk(i, j);
                break;
        }
    }

    void updateOceanView() {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                setCellColor(i, j);
    }

    void resetOceanView() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                resetCellClass(i, j);
            }
        }
    }

    private void resetCellClass(int i, int j) {
        oceanCells[i][j].getStyleClass().clear();
        setCellClass(i, j, "button");
        setCellNeutral(i, j);
    }

    private void setCellClass(int i, int j, String className) {
        oceanCells[i][j].getStyleClass().add(className);
    }

    private void setCellNeutral(int i, int j) {
        setCellClass(i, j, "button-neutral");
    }

    private void setCellEmpty(int i, int j) {
        setCellClass(i, j, "button-empty");
    }

    private void setCellDamaged(int i, int j) {
        setCellClass(i, j, "button-damaged");
    }

    private void setCellSunk(int i, int j) {
        setCellClass(i, j, "button-sunk");
    }

    void setCellAdapter(CellAdapter cellAdapter) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button button = oceanCells[i][j];

                int finalI = i;
                int finalJ = j;
                button.setOnAction(actionEvent -> {
                    cellAdapter.onCellClicked(finalI, finalJ);
                });
            }
        }
    }
}
