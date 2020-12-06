package controller;

/**
 * represents question message (event) sent from View to Model
 */

public class QuestionMessage extends BaseMessage {

    public QuestionMessage(int row, int col){
        super(row, col);
    }

}

