package controller;

public abstract class BaseMessage implements Message {

    public int row;
    public int col;

    BaseMessage(){ }

    BaseMessage(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public Pair<Integer, Integer> getEvent() {
        return new Pair<Integer, Integer>(row, col);
    }
}
