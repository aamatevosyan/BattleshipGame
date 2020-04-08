package hse.edu.battleship.core;

/**
 * Describes a part of the ocean that doesn't have a ship in it.
 */
public class EmptySea extends Ship {

    /**
     * Creates an EmptySea
     */
    public EmptySea() {
        length = 1;
    }

    /**
     * @return a string representation of the ship.
     */
    @Override
    public String toString() {
        return (isSunk()) ? "-" : ".";
    }

    /**
     * @return the length of this particular ship
     */
    @Override
    public int getLength() {
        return length;
    }

    /**
     * @return the type of this ship
     */
    @Override
    public String getShipType() {
        return "empty";
    }

    /**
     * @return true if every part of the ship has been hit, false otherwise.
     */
    @Override
    public boolean isSunk() {
        return false;
    }

    /**
     * @param row    the row where we are shooting
     * @param column the column where we are shooting
     * @return If a part of the ship occupies the given row and column, and the ship hasn't been sunk,
     * mark that part of the ship as "hit" (in the hit array, 0 indicates the bow)
     * and return true, otherwise return false.
     */
    @Override
    public boolean shootAt(int row, int column) {
        super.shootAt(row, column);
        // no need to return false, because shootAt always returns false for EmptySea,
        // but it is required by HW-1.pdf
        return false;
    }

    /**
     * @param row    the row of ship to look at
     * @param column the column of ship to look at
     * @return the code that defines coordinate state
     */
    @Override
    public String codeAt(int row, int column) {
        return super.codeAt(row, column).replace('S', '-');
    }
}
