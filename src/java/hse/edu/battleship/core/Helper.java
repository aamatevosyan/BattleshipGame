package hse.edu.battleship.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper class for color output
 */
public class Helper {

    /**
     * Defined settings for type of string to output
     */
    public static final String NUM = "num"; // numbers of ocean
    public static final String INFO = "inf"; // information
    public static final String EMPTY = "."; // empty ships
    public static final String MINUS = "-"; // hited empty ships
    public static final String HIT = "X"; // hited ships
    public static final String SHIP = "S"; // ships
    /**
     * Reset color
     */
    private static final String RESET = "\033[0m";  // Text Reset
    /**
     * Regular colors
     */
    private static final String BLACK = "\033[0;30m";   // BLACK
    private static final String RED = "\033[0;31m";     // RED
    private static final String GREEN = "\033[0;32m";   // GREEN
    private static final String YELLOW = "\033[0;33m";  // YELLOW
    private static final String BLUE = "\033[0;34m";    // BLUE
    private static final String PURPLE = "\033[0;35m";  // PURPLE
    private static final String CYAN = "\033[0;36m";    // CYAN
    private static final String WHITE = "\033[0;37m";   // WHITE
    /**
     * Colors map
     */
    private static final Map<String, String> colors = new HashMap<>();

    /*
      Defining default settings
     */
    static {
        colors.put(NUM, CYAN);
        colors.put(INFO, GREEN);
        colors.put(EMPTY, RESET);
        colors.put(MINUS, RED);
        colors.put(HIT, YELLOW);
        colors.put(SHIP, PURPLE);
    }


    /**
     * @param type the type of string
     * @param str  the string sequence
     * @return the colored string for given sequence
     */
    public static String getColoredString(String type, String str) {
        return colors.get(type) + str + RESET;
    }

}
