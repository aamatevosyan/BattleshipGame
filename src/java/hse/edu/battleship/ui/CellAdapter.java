package hse.edu.battleship.ui;

/**
 * Interface for cells events
 */
public interface CellAdapter {
    /**
     * Action on cell click
     *
     * @param i cell's row
     * @param j cell's column
     */
    void onCellClicked(int i, int j);
}
