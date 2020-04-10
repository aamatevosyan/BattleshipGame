package hse.edu.battleship.ui;

import hse.edu.battleship.core.Ocean;

/**
 * SoloGameWindow
 */
public class SoloGameWindow extends GameWindow {
    /**
     * SoloGameManager
     */
    private SoloGameManager soloGameManager;

    /**
     * Default constructor
     *
     * @param ocean ocean
     */
    public SoloGameWindow(Ocean ocean) {
        super(ocean);
    }

    /**
     * Starts new game
     */
    @Override
    public void startNewGame() {
        super.startNewGame();
        soloGameManager = new SoloGameManager(this);
    }
}
