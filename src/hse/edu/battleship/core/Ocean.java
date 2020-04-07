package hse.edu.battleship.core;

import hse.edu.battleship.gui.GameWindow;
import hse.edu.battleship.gui.NetworkGameWindow;
import hse.edu.battleship.gui.SoloGameWindow;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

import java.util.*;

/**
 * This contains a 10x10 array of Ships, representing the "ocean," and some methods to manipulate it.
 */
public class Ocean {

    /**
     * Used to quickly determine which ship is in any given location.
     */
    private Ship[][] ships = new Ship[10][10];

    /**
     * The total number of shots fired by the user.
     */
    private int shotsFired;

    /**
     * The number of times a shot hit a ship. If the user shoots the same part of a ship more than once,
     * every hit is counted, even though the additional "hits" don't do the user any good.
     */
    private int hitCount;

    /**
     * The number of ships sunk (10 ships in all).
     */
    private int shipsSunk;

    /**
     * The number of ships damaged.
     */
    private int shipsDamaged;

    /**
     * Used for randomizing
     */
    private static Random random = new Random();

    /**
     * Creates an "empty" ocean (fills the ships array with EmptySeas). Also initializes any game variables,
     * such as how many shots have been fired.
     */
    public Ocean() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ships[i][j] = new EmptySea();
                ships[i][j].placeShipAt(i, j, true, this);
            }
        }

        hitCount = shotsFired = shipsSunk = 0;
    }


    /**
     * Place all ten ships randomly on the (initially empty) ocean. Place larger ships before smaller ones,
     * or you may end up with no legal place to put a large ship.
     */
    public void placeAllShipsRandomly() {
        Ship[] shipArray = {new Battleship(), new Cruiser(), new Cruiser(),
                new Destroyer(), new Destroyer(), new Destroyer(), new Submarine(),
                new Submarine(), new Submarine(), new Submarine()};

        for (Ship ship : shipArray) {
            placeShipRandomly(ship);
        }
    }

    /**
     * @return random coordinate.
     */
    private int getRandomCoordinate() {
        return random.nextInt(10);
    }


    /**
     * @return random orientation
     */
    private boolean getRandomOrientation() {
        return random.nextBoolean();
    }

    /**
     * Place all ship randomly on the ocean.
     *
     * @param ship the ship to place
     */
    private void placeShipRandomly(Ship ship) {
        int x, y;
        boolean orientation;
        do {
            x = getRandomCoordinate();
            y = getRandomCoordinate();
            orientation = getRandomOrientation();
        } while (!ship.okToPlaceShipAt(x, y, orientation, this));
        ship.placeShipAt(x, y, orientation, this);
    }

    public void setUpOcean() {
        List<String> choices = new ArrayList<>();
        choices.add("Random");
        choices.add("Custom");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Random", choices);

        dialog.setTitle("Choice Dialog");
        dialog.setHeaderText("Hurry up, choose your ships selection mode.");
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setContentText("Choose ocean type:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(s -> {
            if (s.equals("Random")) {
                placeAllShipsRandomly();
            } else {
                System.out.println("Custom");
            }
        });
    }


    /**
     * @param row    the row to look at
     * @param column the column to look at
     * @return true if the given location contains a ship, false if it does not.
     */
    public boolean isOccupied(int row, int column) {
        return !(ships[row][column] instanceof EmptySea);
    }


    /**
     * @param row    the row to shoot at
     * @param column the column to shoot at
     * @return true if the given location contains a "real" ship, still afloat, (not an EmptySea), false if it does not.
     * In addition, this method updates the number of shots that have been fired, and the number of hits.
     */
    public boolean shootAt(int row, int column) {
        shotsFired++;

        if (getAt(row, column).shootAt(row, column)) {
            hitCount++;
            if (ships[row][column].isSunk())
                shipsSunk++;
            return true;
        } else
            return false;
    }


    /**
     * @return The total number of shots fired by the user.
     */
    public int getShotsFired() {
        return shotsFired;
    }

    /**
     * @return The number of times a shot hit a ship.
     */
    public int getHitCount() {
        return hitCount;
    }

    /**
     * @return The number of ships sunk (10 ships in all).
     */
    public int getShipsSunk() {
        return shipsSunk;
    }


    /**
     * @return true if all ships have been sunk, otherwise false.
     */
    public boolean isGameOver() {
        return shipsSunk == 10;
    }

    /**
     * @return Returns the 10x10 array of ships.
     */
    public Ship[][] getShipArray() {
        return ships;
    }

    /**
     * Prints the ocean.
     */
    public void print() {
        System.out.println(Helper.getColoredString(Helper.NUM, "  0 1 2 3 4 5 6 7 8 9"));
        for (int i = 0; i < 10; i++) {
            System.out.print(Helper.getColoredString(Helper.NUM, i + " "));
            for (int j = 0; j < 10; j++) {
                System.out.print(coloredCodeAt(i, j) + " ");
            }
            System.out.println();
        }
    }

    /**
     * Prints debug information of the ocean.
     */
    public void printDebug() {
        System.out.println(Helper.getColoredString(Helper.NUM, "  0 1 2 3 4 5 6 7 8 9"));
        for (int i = 0; i < 10; i++) {
            System.out.print(Helper.getColoredString(Helper.NUM, i + " "));
            for (int j = 0; j < 10; j++) {
                System.out.print(getAt(i, j) + " ");
            }
            System.out.println();
        }
    }


    /**
     * @param x coordinate
     * @return true if coordinate is in bound, otherwise false.
     */
    public boolean checkBound(int x) {
        return x >= 0 && x <= 9;
    }

    /**
     * @param row    row
     * @param column column
     * @return true if row and column are in bound, otherwise false.
     */
    public boolean checkBound(int row, int column) {
        return checkBound(row) && checkBound(column);
    }

    /**
     * @param row    the row of ship to look at
     * @param column the column of ship to look at
     * @return the code that defines coordinate state
     */
    public String codeAt(int row, int column) {
        Ship ship = ships[row][column];
        if (ship.isSunk())
            return ship.toString();
        else
            return ship.codeAt(row, column);
    }


    /**
     * @param row    the row of ship to look at
     * @param column the column of ship to look at
     * @return the colored code that defines coordinate state
     */
    public String coloredCodeAt(int row, int column) {
        String code = codeAt(row, column);
        return Helper.getColoredString(code, code);
    }


    /**
     * Sets the ship at given location
     *
     * @param row    the row of ship to look at
     * @param column the column of ship to look at
     * @param ship   the ship
     */
    public void setAt(int row, int column, Ship ship) {
        ships[row][column] = ship;
    }

    /**
     * @param row    the row of ship to look at
     * @param column the column of ship to look at
     * @return the ship from given location
     */
    public Ship getAt(int row, int column) {
        return ships[row][column];
    }

    public int getShipsDamaged() {
        shipsDamaged = 0;
        Set<Ship> ships = new HashSet<>();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10;j++)
                if (isOccupied(i, j))
                    ships.add(getAt(i, j));
        for (Ship ship :
                ships) {
            if (ship.isDamaged())
                shipsDamaged++;
        }
        return shipsDamaged;
    }
}
