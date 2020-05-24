package hse.edu.battleship.ui;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class for broadcasting
 */
public class Broadcaster {
    /**
     * Initial condition
     */
    AtomicBoolean initialCondition;

    /**
     * Message
     */
    String message;

    /**
     * Default constructor
     *
     * @param initialCondition initial condition
     */
    public Broadcaster(boolean initialCondition) {
        this.initialCondition = new AtomicBoolean(initialCondition);
        message = "";
    }
}
