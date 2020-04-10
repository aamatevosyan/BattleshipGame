package hse.edu.battleship.core;

/**
 * Describes a ship of length 4.
 */
public class Battleship extends Ship {

    /**
     * Creates an Battleship
     */
    public Battleship() {
        length = 4;
    }

    /**
     * @return a string representation of the ship.
     */
    @Override
    public String toString() {
        return super.toString();
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
        return "battleship/core";
    }
}
