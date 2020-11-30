package controller;

public class NewGameMessage implements Message {
    int row, col;
    static final int NEW_GAME = 2;

    @Override
    public Pair<Integer, Pair<Integer, Integer>> getEvent() {
        return new Pair<>(NEW_GAME, new Pair<>(row, col));
    }
}