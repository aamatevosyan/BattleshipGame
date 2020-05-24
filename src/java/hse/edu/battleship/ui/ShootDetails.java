package hse.edu.battleship.ui;

/**
 * Structure for storing shoot details
 */
class ShootDetails {
    static final String DAMAGED_CODE = "DAMAGED";
    static final String SUNK_CODE = "SUNK";
    static final String EMPTY_CODE = "EMPTY";

    /**
     * Shoot's result
     */
    String shootResult;

    /**
     * Row
     */
    int i;

    /**
     * Column
     */
    int j;

    /**
     * Default constructor
     *
     * @param shootResult shoot's result
     * @param i           row
     * @param j           column
     */
    public ShootDetails(String shootResult, int i, int j) {
        this.shootResult = shootResult;
        this.i = i;
        this.j = j;
    }

    /**
     * @return true if shoot's result is Damaged
     */
    public boolean isDamaged() {
        return shootResult.equals(DAMAGED_CODE);
    }

    /**
     * @return true if shoot's result is Empty
     */
    public boolean isEmpty() {
        return shootResult.equals(EMPTY_CODE);
    }

    /**
     * @return true if shoot's result is Sunk
     */
    public boolean isSunk() {
        return shootResult.equals(SUNK_CODE);
    }

    /**
     * Prints ShootDetails
     *
     * @param name                player's name
     * @param printStreamCapturer PrintStreamCapturer
     */
    public void print(String name, PrintStreamCapturer printStreamCapturer) {
        if (name != null && printStreamCapturer != null)
            printStreamCapturer.println(name + ":(" + i + ", " + j + ") = " + shootResult);
    }
}
