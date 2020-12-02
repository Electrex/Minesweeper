package controller;

public class RevealMessage implements Message {
    int row, col;
    public RevealMessage(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public Pair<Integer, Integer> getEvent() {
        return new Pair<>(row, col);
    }
}
