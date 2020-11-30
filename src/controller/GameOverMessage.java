package controller;

public class GameOverMessage implements Message {
    int row, col;
    static final int GAME_OVER = 1;

    public GameOverMessage(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public Pair<Integer, Pair<Integer, Integer>> getEvent() {
        return new Pair<>(GAME_OVER, new Pair<>(row, col));
    }
}
