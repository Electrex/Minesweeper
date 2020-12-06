package model;

/**
 * this is the Minesweeper Model Interface that contains the methods needed in order to initialize and work with the data in the model
 * @param <T>
 */

public interface MSModel<T> {
    int getNumRows();
    int getNumCols();
    T[][] getGrid();
    int getNumNeighboringMines(int row, int col);
    boolean isInBounds(int row, int col);
    void initializeBoard();
    void initializeBoard(int r, int c);
    void recursiveReveal(int row, int col);
    void endGame();
    void revealAllMines();
}
