package hse.edu.battleship.core;

/**
 * Describes a ship of length 1.
 */
public class Submarine extends Ship {

    /**
     * Creates an Submarine
     */
    public Submarine() {
        length = 1;
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
        return "submarine";
    }
}
