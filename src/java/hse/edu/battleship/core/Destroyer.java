package hse.edu.battleship.core;

/**
 * Describes a ship of length 2.
 */
public class Destroyer extends Ship {

    /**
     * Creates a Destroyer
     */
    public Destroyer() {
        length = 2;
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
        return "destroyer";
    }
}
