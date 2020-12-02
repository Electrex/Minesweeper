package controller;

public class GameOverMessage implements Message {
    int row, col;

    public GameOverMessage(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public Pair<Integer, Integer> getEvent() {
        return new Pair<>(row, col);
    }
}
