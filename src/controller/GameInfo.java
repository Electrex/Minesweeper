package controller;

public class GameInfo {
    public static int GAME_OVER = 0;
    public static int GAME_WON = 1;
    public static int GAME_IN_PROGRESS = 2;
    private int state;

    public GameInfo(){
        state = 0;
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
