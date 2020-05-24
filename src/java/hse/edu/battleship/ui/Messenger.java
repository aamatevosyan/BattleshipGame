package hse.edu.battleship.ui;

import hse.edu.battleship.core.Ocean;

/**
 * Data structure for working with messages
 */
public class Messenger {
    /*
    Message codes
     */

    final static String NOT_SUPPORTED_CODE = "Server supports only one client.";
    final static String GREET_MESSAGE_CODE = "GREET:";
    final static String SHOOT_MESSAGE_CODE = "SHOOT:";
    final static String WIN_MESSAGE_CODE = "WIN:";
    final static String SETUP_MESSAGE_CODE = "SETUP:";
    final static String QUIT_MESSAGE_CODE = "QUIT:";
    final static String DUMMY_MESSAGE_CODE = "DUMMY:";

    /*
    Message checkers
     */

    static boolean isGreetSucceeded(String message) {
        return !message.equals(NOT_SUPPORTED_CODE);
    }

    static boolean isQuitMessage(String message) {
        return message.startsWith(QUIT_MESSAGE_CODE);
    }

    static boolean isGreetMessage(String message) {
        return message.startsWith(GREET_MESSAGE_CODE);
    }

    static boolean isShootMessage(String message) {
        return message.startsWith(SHOOT_MESSAGE_CODE);
    }

    static boolean isWinMessage(String message) {
        return message.startsWith(WIN_MESSAGE_CODE);
    }

    static boolean isDummyMessage(String message) {
        return message.startsWith(DUMMY_MESSAGE_CODE);
    }

    /*
    Message makers
     */

    static String createGreetMessage(String name) {
        return GREET_MESSAGE_CODE + name;
    }

    static String createSetupMessage(Ocean ocean) {
        return SETUP_MESSAGE_CODE + ocean.decodeToString();
    }

    static String createQuitMessage(String name) {
        return QUIT_MESSAGE_CODE + name;
    }

    static String createShootMessage(String shootResult, int i, int j) {
        return SHOOT_MESSAGE_CODE + shootResult + i + j;
    }

    static String createWinMessage(int score) {
        return WIN_MESSAGE_CODE + score;
    }

    static String createDummyMessage() {
        return DUMMY_MESSAGE_CODE;
    }

    /*
    Message accessors
     */

    static String getNameFromQuit(String message) {
        return message.substring(QUIT_MESSAGE_CODE.length());
    }


    static Ocean getOcean(String message) {
        Ocean ocean = new Ocean();
        ocean.encodeFromString(message.substring(SETUP_MESSAGE_CODE.length()));
        return ocean;
    }

    static String getNameFromGreet(String message) {
        return message.substring(GREET_MESSAGE_CODE.length());
    }

    static ShootDetails getShootDetails(String message) {
        String shootResult = message.substring(SHOOT_MESSAGE_CODE.length(), message.length() - 2);
        int i = Integer.parseInt(message.substring(message.length() - 2, message.length() - 1));
        int j = Integer.parseInt(message.substring(message.length() - 1));
        return new ShootDetails(shootResult, i, j);
    }

    static int getScore(String message) {
        return Integer.parseInt(message.substring(WIN_MESSAGE_CODE.length()));
    }
}

