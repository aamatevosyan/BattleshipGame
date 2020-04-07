package hse.edu.battleship.core;

/**
 * This describes characteristics common to all the ships.
 */
public abstract class Ship {

    /**
     * the row (0 to 9) which contains the bow (front) of the ship
     */
    private int bowRow;

    /**
     * the column (0 to 9) which contains the bow (front) of the ship
     */
    private int bowColumn;

    /**
     * the number of squares occupied by the ship. An "empty sea" location has length 1
     */
    protected int length;

    /**
     * true if the ship occupies a single row, false otherwise
     */
    private boolean horizontal;

    /**
     * an array of booleans telling whether that part of the ship has been hit.
     * Only battleships use all four locations;
     * cruisers use the first three;
     * destroyers 2; submarines 1; and "empty sea" either one or none.
     */
    private boolean [] hit = new boolean[4];


    /**
     * @return the length of this particular ship
     */
    public abstract int getLength();


    /**
     * @return horizontal
     */
    public boolean isHorizontal() {
        return horizontal;
    }


    /**
     * @return bowColumn
     */
    public int getBowColumn() {
        return bowColumn;
    }


    /**
     * @return bowRow
     */
    public int getBowRow() {
        return bowRow;
    }


    /**
     * @param bowRow the row (0 to 9) which contains the bow (front) of the ship
     */
    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }

    /**
     * @param horizontal  true if the ship occupies a single row, false otherwise.
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * @param bowColumn the column (0 to 9) which contains the bow (front) of the ship.
     */
    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }


    /**
     * @return the type of this ship
     */
    public abstract String getShipType();


    /**
     * @param row the row (0 to 9) which contains the bow (front) of the ship
     * @param column the column (0 to 9) which contains the bow (front) of the ship.
     * @param horizontal true if the ship occupies a single row, false otherwise.
     * @param ocean ocean where ship can be placed
     * @return true if it is okay to put a ship of this length with its bow in this location,
     * with the given orientation, and returns false otherwise.
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        int endRow = (horizontal) ? row : row + length - 1;
        int endColumn = (horizontal) ? column + length - 1: column;

        if (!ocean.checkBound(endRow, endColumn))
            return false;

        int startRow = row - 1;
        int startColumn = column - 1;

        for (int i = startRow; i <= endRow + 1; i++) {
            for (int j = startColumn; j <= endColumn + 1; j++) {
                try {
                    if (!ocean.getAt(i, j).getShipType().equals("empty"))
                        return false;
                } catch (ArrayIndexOutOfBoundsException ignored) {

                }
            }
        }
        return true;
    }


    /**
     * "Puts" the ship in the ocean. This involves giving values to the bowRow, bowColumn, and horizontal
     * instance variables in the ship, and it also involves putting a reference to the ship in each of 1
     * or more locations (up to 4) in the ships array in the Ocean object.
     * @param row the row (0 to 9) which contains the bow (front) of the ship
     * @param column the column (0 to 9) which contains the bow (front) of the ship.
     * @param horizontal true if the ship occupies a single row, false otherwise.
     * @param ocean ocean where ship can be placed
     */
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {

        setBowRow(row);
        setBowColumn(column);
        setHorizontal(horizontal);

        if (horizontal) {
            for (int i = 0; i < length; i++)
                ocean.setAt(row, column + i, this);
        } else {
            for (int i = 0; i < length; i++)
                ocean.setAt(row + i, column, this);
        }

    }


    /**
     * @param row the row where we are shooting
     * @param column the column where we are shooting
     * @return If a part of the ship occupies the given row and column, and the ship hasn't been sunk,
     * mark that part of the ship as "hit" (in the hit array, 0 indicates the bow)
     * and return true, otherwise return false.
     */
    public boolean shootAt(int row, int column) {
        int delta = (horizontal) ? column - bowColumn : row - bowRow;

        if (delta < 0 || delta >= length || isSunk())
            return false;
        else {
            hit[delta] = true;
            return true;
        }
    }

    public boolean isHit(int row, int column) {
        int delta = (horizontal) ? column - bowColumn : row - bowRow;

        if (delta < 0 || delta >= length || isSunk())
            return false;
        else
            return hit[delta];
    }

    /**
     * @param row the row of ship to look at
     * @param column the column of ship to look at
     * @return the code that defines coordinate state
     */
    public String codeAt(int row, int column) {
        int delta = (horizontal) ? column - bowColumn : row - bowRow;
        return hit[delta] ? "S" : ".";
    }


    /**
     * @return true if every part of the ship has been hit, false otherwise.
     */
    public boolean isSunk() {
        for (int i = 0; i < length; i++)
            if (!hit[i])
                return false;
        return true;
    }

    public boolean isDamaged() {
        int damagedCount = 0;
        for (int i = 0; i < length; i++)
            if (hit[i])
                damagedCount++;
        return damagedCount > 0 && damagedCount != length;
    }

    /**
     * @return a string representation of the ship.
     */
    @Override
    public String toString() {
        return (isSunk()) ? "X" : "S";
    }
}
