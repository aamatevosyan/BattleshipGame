package hse.edu.battleship.core;

import hse.edu.battleship.gui.GameWindow;
import hse.edu.battleship.gui.NetworkGameWindow;
import hse.edu.battleship.gui.SoloGameWindow;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Player {
    public Ocean ocean;
    public Ocean enemyOcean;
    public String name;

    public Playable playable;

    public Player(Playable playable) {
        this.playable = playable;
    }

    public void doTurn(int i, int j) {
        playable.doTurn(this, i, j);
    }
}
