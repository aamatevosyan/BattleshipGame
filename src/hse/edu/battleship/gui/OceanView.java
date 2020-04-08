package hse.edu.battleship.gui;

import hse.edu.battleship.core.Ocean;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

public class OceanView {

    static final String[] COLORS = {"#ccf0fe", "#56cdfc", "#cbcbd2"};

    static final String NEUTRAL_COLOR = "#ccf0fe";
    static final String EMPTY_COLOR = "#56cdfc";
    static final String DAMAGED_COLOR = "#e54044";
    static final String SUNK_COLOR = "#cbcbd2";

    final Button[][] oceanCells = new Button[10][10];

    Ocean ocean;

    public OceanView(GridPane gridPane) {
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
                setCellNeutral(i - 1, j - 1);

                gridPane.add(button, i, j);
            }
        }

//        ocean = new Ocean();
//        ocean.placeAllShipsRandomly();
    }

    void setCellColor(int i, int j) {
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

    void setCellColor(int i, int j, String color) {
        oceanCells[i][j].setStyle("-fx-background-color:" + color + ";");
    }

    void setCellNeutral(int i, int j) {
        setCellColor(i, j, NEUTRAL_COLOR);
    }

    void setCellEmpty(int i, int j) {
        setCellColor(i, j, EMPTY_COLOR);
    }

    void setCellDamaged(int i, int j) {
        setCellColor(i, j, DAMAGED_COLOR);
    }

    void setCellSunk(int i, int j) {
        setCellColor(i, j, SUNK_COLOR);
    }

    /**
     * @param row    the row to look at
     * @param column the column to look at
     * @return sink message of the ship
     */
    public String getSinkMessage(int row, int column) {
        return "You just sank a " + ocean.getAt(row, column).getShipType();
    }

    void shootAt(int i, int j) {
        if (ocean.shootAt(i, j)) {
            if (ocean.getAt(i, j).isSunk())
                System.out.println(getSinkMessage(i, j));
            else
                System.out.println("You just hit a ship :)");
        } else {
            System.out.println("Oops, you missed :(");
        }
        System.out.println();

        if (ocean.isGameOver())
            onWin();
    }

    void onWin() {
        System.out.println("You win!!!");
        System.out.printf("Game is over. Your score is : %s%n", ocean.getShotsFired());
    }

    void setCellAdapter(CellAdapter cellAdapter) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button button = oceanCells[i][j];
                int finalI = i;
                int finalJ = j;
                button.setOnMouseClicked(mouseEvent -> cellAdapter.onCellClicked(finalI, finalJ));
            }
        }
    }
}
