package hse.edu.battleship.core;

import java.util.Scanner;
import static hse.edu.battleship.core.Helper.*;

/**
 * This is the "main" class, containing the main method and a variable of type Ocean.
 */
public class BattleshipGame {

    /**
     * The ocean
     */
    private static Ocean ocean;

    /**
     * The Scanner to work with IO
     */
    private static final Scanner in = new Scanner(System.in);


    /**
     * Starts new game
     */
    private static void startGame() {
        ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        //ocean.printDebug(); // Prints location of ships (used for testing)
        do {
            doTurn();
        } while(!ocean.isGameOver());
    }


    /**
     * Print's info for user
     */
    private static void print() {

        System.out.printf("Shots fired: %s%n",
                getColoredString(INFO, Integer.toString(ocean.getShotsFired())));
        System.out.printf("  Hit count: %s%n",
                getColoredString(INFO, Integer.toString(ocean.getHitCount())));
        System.out.printf(" Ships sunk: %s%n",
                getColoredString(INFO, (Integer.toString(ocean.getShipsSunk()))));

        System.out.println();
        ocean.print();
        System.out.println();
    }


    /**
     * @param message the message to be shown to user
     * @param errorMessage the message to be shown if input is not valid
     * @return the inputted number
     */
    private static int getCoordinate(String message, String errorMessage) {
        int res;
        while (true) {
            try {
                System.out.print(message);
                res = Integer.parseInt(in.nextLine());
                if (res < 0 || res > 9)
                    throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
        return res;
    }


    /**
     * Does a turn
     */
    private static void doTurn() {
        print();

        int x, y;
        x = getCoordinate("Enter row to shoot at: ",
                "Invalid row!!!");
        y = getCoordinate("Enter column to shoot at: ",
                "Invalid column!!!");

        if (ocean.shootAt(x, y)) {
            if (ocean.getAt(x, y).isSunk())
                System.out.println(getColoredString(INFO, getSinkMessage(x, y)));
            else
                System.out.println(getColoredString(INFO, "You just hit a ship :)"));
        } else {
            System.out.println(getColoredString(INFO, "Oops, you missed :("));
        }

        System.out.println();
    }

//    /**
//     * @return true if user wants to play again and returns false otherwise.
//     */
//    private static boolean isNewGame() {
//        System.out.print("Press any key to restart the game or enter \"quit\" to exit: ");
//
//        return !in.nextLine().equals("quit");
//    }

    /**
     * Prints results when user wins
     */
    private static void onWin() {
        System.out.println("You win!!!");
        System.out.printf("Game is over. Your score is : %s%n", Integer.toString(ocean.getShotsFired()));
    }

    /**
     * @param row the row to look at
     * @param column the column to look at
     * @return sink message of the ship
     */
    public static String getSinkMessage(int row, int column) {
        return "You just sank a " + ocean.getAt(row, column).getShipType();
    }

//    /**
//     * The main entrance of the app.
//     * @param args the passed arguments
//     */
//    public static void main(String[] args) {
//        do {
//            startGame();
//            onWin();
//        } while(isNewGame());
//    }
}


