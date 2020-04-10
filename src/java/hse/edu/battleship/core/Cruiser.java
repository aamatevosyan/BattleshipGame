package hse.edu.battleship.core;

/**
 * Describes a ship of length 3.
 */
public class Cruiser extends Ship {

    /**
     * Creates an Cruiser
     */
    public Cruiser() {
        length = 3;
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
        return "cruiser";
    }
}
