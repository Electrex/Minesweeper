package controller;

public class NewGameMessage implements Message {
    int row, col;
    static final int NEW_GAME = 2;
    public NewGameMessage(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public Pair<Integer, Pair<Integer, Integer>> getEvent() {
        return new Pair<>(NEW_GAME, new Pair<>(row, col));
    }
}


class RightClickMessage implements Message {
    int row, col;
    static final int RIGHT_CLICK = 3;
    public RightClickMessage(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public Pair<Integer, Pair<Integer, Integer>> getEvent() {
        return new Pair<>(RIGHT_CLICK, new Pair<>(row, col));
    }
}