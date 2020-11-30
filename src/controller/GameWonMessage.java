package controller;

public class GameWonMessage implements Message {
    int row, col;
    static final int GAME_WON = 1;

    public GameWonMessage(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public Pair<Integer, Pair<Integer, Integer>> getEvent() {
        return new Pair<>(GAME_WON, new Pair<>(row, col));
    }
}
