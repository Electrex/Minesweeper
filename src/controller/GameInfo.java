package controller;

public class GameInfo {
    public static int GAME_OVER = 0;
    public static int GAME_WON = 1;
    public static int GAME_IN_PROGRESS = 2;
    private int state;
    private int r;
    private int c;

    public GameInfo(){
        state = 2;
        r = -1;
        c = -1;
    }

    public void setRow(int r){
        this.r = r;
    }

    public void setCol(int c){
        this.c = c;
    }

    public int getRow(){
        return r;
    }

    public int getCol(){
        return c;
    }

    public void setState(int state){
        this.state = state;
    }

    public int getState(){
        return state;
    }
    // the state of the Game/Application
    // information that is needed to repaint the View
}
