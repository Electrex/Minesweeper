package controller;

public class NewGameMessage implements Message {
    int row, col;

    @Override
    public Pair<Integer, Integer> getEvent() {
        return new Pair<>(row, col);
    }
}