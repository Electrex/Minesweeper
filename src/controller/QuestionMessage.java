package controller;

public class QuestionMessage implements Message {
    int row, col;
    public QuestionMessage(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public Pair<Integer, Integer> getEvent() {
        return new Pair<>(row, col);
    }
}

