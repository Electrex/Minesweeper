package model;

public interface MSModel<T> {
    int getNumRows();
    int getNumCols();
    T[][] getGrid();
    int getNumNeighboringMines(int row, int col);
    boolean isInBounds(int row, int col);
    void initializeBoard();
    void recursiveReveal(int row, int col);
    void endGame(boolean winLose);
}
