package controller;

public class GameWonMessage implements Message {
    int row, col;

    public GameWonMessage(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public Pair<Integer, Integer> getEvent() {
        return new Pair<>(row, col);
    }
}
