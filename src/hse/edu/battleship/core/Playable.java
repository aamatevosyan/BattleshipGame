package hse.edu.battleship.core;

public interface Playable {
    void doTurn(Player player, int i, int j);
    void registerPlayer(Player player);
    boolean isGameStarted();
}
