package hse.edu.battleship.ui;

import hse.edu.battleship.core.Ocean;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * OceanView
 */
public class OceanView {
    /**
     * Ocean cells
     */
    final Button[][] oceanCells = new Button[10][10];

    /**
     * Ocean
     */
    Ocean ocean;

    /**
     * Default constructor
     *
     * @param gridPane ocean's GridPane
     */
    public OceanView(GridPane gridPane) {
        gridPane.getStylesheets().add(getClass().getResource("resources/css/OceanView.css").toExternalForm());

        /*
         * Sets numbers from left side
         */
        for (int i = 1; i <= 10; i++) {
            Label label = new Label();
            label.setText(Integer.toString(i - 1));
            gridPane.add(label, 0, i);
        }

        /*
         * Sets number from top
         */
        for (int i = 1; i <= 10; i++) {
            Label label = new Label();
            label.setText(Integer.toString(i - 1));
            gridPane.add(label, i, 0);
        }

        /*
         * Sets ocean cells
         */
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                Button button = new Button();

                int finalI = i - 1;
                int finalJ = j - 1;
                oceanCells[finalI][finalJ] = button;

                /*
                 * Connect on hover with focused event
                 */
                button.hoverProperty().addListener((ov, oldValue, newValue) -> {
                    if (newValue)
                        oceanCells[finalI][finalJ].requestFocus();
                });

                setCellNeutral(finalI, finalJ);
                gridPane.add(button, j, i);
            }
        }
    }

    /**
     * Sets cells color
     *
     * @param i cell's row
     * @param j cell's column
     */
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

    /**
     * Updates view cells
     */
    public void updateOceanView() {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                setCellColor(i, j);
    }

    /**
     * Resets view cells
     */
    void resetOceanView() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                setCellNeutral(i, j);
            }
        }
    }

    /**
     * Reset cell's class
     *
     * @param i cell's row
     * @param j cell's column
     */
    private void resetCellClass(int i, int j) {
        oceanCells[i][j].getStyleClass().clear();
        setCellClass(i, j, "button");
    }

    /**
     * Sets cell's class
     *
     * @param i         cell's row
     * @param j         cell's column
     * @param className class name
     */
    private void setCellClass(int i, int j, String className) {
        oceanCells[i][j].getStyleClass().add(className);
    }

    /**
     * Sets cell class to neutral
     *
     * @param i cell's row
     * @param j cell's column
     */
    private void setCellNeutral(int i, int j) {
        resetCellClass(i, j);
        setCellClass(i, j, "button-neutral");
    }

    /**
     * Sets cell class to empty
     *
     * @param i cell's row
     * @param j cell's column
     */
    private void setCellEmpty(int i, int j) {
        setCellClass(i, j, "button-empty");
    }

    /**
     * Sets cell class to damaged
     *
     * @param i cell's row
     * @param j cell's column
     */
    private void setCellDamaged(int i, int j) {
        setCellClass(i, j, "button-damaged");
    }

    /**
     * Sets cell class to sunk
     *
     * @param i cell's row
     * @param j cell's column
     */
    private void setCellSunk(int i, int j) {
        setCellClass(i, j, "button-sunk");
    }

    /**
     * Sets cell's adapter
     *
     * @param cellAdapter CellAdapter
     */
    public void setCellAdapter(CellAdapter cellAdapter) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button button = oceanCells[i][j];

                int finalI = i;
                int finalJ = j;
                button.setOnAction(actionEvent -> cellAdapter.onCellClicked(finalI, finalJ));
            }
        }
    }
}
