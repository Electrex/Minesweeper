package controller;

public class QuestionMessage implements Message {
    int row, col;
    static final int HIT_EVENT = 1;
    public QuestionMessage(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public Pair<Integer, Pair<Integer, Integer>> getEvent() {
        return new Pair<>(HIT_EVENT, new Pair<>(row, col));
    }
}
