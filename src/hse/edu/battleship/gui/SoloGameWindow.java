package hse.edu.battleship.gui;

import hse.edu.battleship.core.Ocean;

public class SoloGameWindow extends GameWindow {
    SoloGameManager soloGameManager;
    public SoloGameWindow(Ocean ocean) {
        super(ocean);
    }

    @Override
    public void startNewGame() {
        super.startNewGame();
        soloGameManager = new SoloGameManager(this);
    }
}
