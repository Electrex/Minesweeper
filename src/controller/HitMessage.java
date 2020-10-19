package controller;

public class HitMessage implements Message {
    int row, col;
    static final int HIT_EVENT = 0;
    public HitMessage(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public Pair<Integer, Pair<Integer, Integer>> getEvent() {
        return new Pair<>(HIT_EVENT, new Pair<>(row, col));
    }
}
