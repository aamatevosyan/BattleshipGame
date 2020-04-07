package hse.edu.battleship.core;

import java.util.Random;

public class Robot extends Player {
    static Random random = new Random();

    public Robot(Playable playable) {
        super(playable);
    }

    private int getRandomIndex() {
        return random.nextInt(10);
    }

    public void doTurn() {
        doTurn(getRandomIndex(), getRandomIndex());
    }
}
