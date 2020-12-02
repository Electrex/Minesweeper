package controller;

public class FlagMessage implements Message {
    int row, col;
    public FlagMessage(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public Pair<Integer, Integer> getEvent() {
        return new Pair<>(row, col);
    }
}
