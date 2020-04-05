package hse.edu.battleship.gui;

public interface CellAdapter {
    void onCellClicked(int i, int j);
    void onCellHoverStarted(int i, int j);
    void onCellHoverEnded(int i, int j);
}
