package model;

/**
 * this is the Tile class that contains different tile types, states, and other tile info
 */

public class Tile {

    //Possible tile states
    public static final int COVERED = 0;
    public static final int REVEALED = 1;
    public static final int FLAGGED = 2;
    public static final int QUESTION = 3;
    
    private int state;
    
    //Possible tile types
    public static final int BLANK = 0;
    public static final int NUMBER = 1;
    public static final int MINE = 2;

    private int type;
    
    //Number of mines surrounding the tile
    private final int numMinesSurrounding;
    
    private final int row;
    private final int col;

    /**
     * setting tile type, row, column, and the number of mines surrounding a certain tile that may get clicked
     *
     * @param type
     * @param row
     * @param col
     */
    
    public Tile(int type, int row, int col) {
        this.type = type;
        this.state = COVERED;
        this.row = row;
        this.col = col;
        this.numMinesSurrounding = -1;
    }

    /**
     *
     * @param type
     * @param numMinesSurrounding
     * @param row
     * @param col
     */

    public Tile(int type, int numMinesSurrounding, int row, int col) {
        this.type = type;
        this.numMinesSurrounding = numMinesSurrounding;
        this.state = COVERED;
        this.row = row;
        this.col = col;
    }




    /**
     * gets tile state
     * @return state
     */
    public int getState() {
        return state;
    }

    /**
     * gets tile type
     * @return type
     */
    
    public int getType() {
        return type;
    }

    /**
     * gets the number of surrounding mines of the selected tile
     * @return numMinesSurrounding
     */

    public int getNumSurroundingMines() {
        return numMinesSurrounding;
    }

    /**
     * setting state of tile
     */
    
    public void setState(int state) {
        this.state = state;
    }

    // For debugging
    @Override
    public String toString() {
        if (getState() == REVEALED) {
            if (type == BLANK) return " ";
            else if (type == NUMBER) return numMinesSurrounding + "";
            else if (type == MINE) return "*";
        }

        if (state == COVERED) return "_";
        else if (state == REVEALED) return numMinesSurrounding + "";
        else if (state == FLAGGED) return "!";

        // Default placeholder return statement
        return "X";
    }

}
