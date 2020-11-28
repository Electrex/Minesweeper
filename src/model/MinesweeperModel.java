package model;

import java.util.Random;

public class MinesweeperModel implements MSModel<Tile> {

    private final int WIDTH;
    private final int HEIGHT;
    private final int numMines;

    private Tile[][] grid;
    public int numMinesLeft;
    public int numMinesMarked;
    private boolean gameOver;

    public MinesweeperModel(int gridWidth, int gridHeight, int numMines) {
        this.WIDTH = gridWidth;
        this.HEIGHT = gridHeight;
        this.numMines = numMines;
        numMinesLeft = numMines;
        numMinesMarked = 0;
        gameOver = false;
        initializeBoard();
    }

    @Override
    public void initializeBoard() {

        grid = new Tile[HEIGHT][WIDTH];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = new Tile(Tile.BLANK, row, col);
            }
        }

        Random random = new Random();
        for (int i = 0; i < numMines; i++) {
            int row = random.nextInt(HEIGHT);
            int col = random.nextInt(WIDTH);
            if (grid[row][col].getType() == Tile.MINE || grid[row][col].getType() == Tile.NUMBER) {
                i--;
            } else {
                grid[row][col] = new Tile(Tile.MINE, row, col);
            }
        }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (getNumNeighboringMines(row, col) > 0 && grid[row][col].getType() != Tile.MINE) {
                    Tile tile = new Tile(Tile.NUMBER, getNumNeighboringMines(row, col), row, col);
                    grid[row][col] = tile;
                }
            }
        }
    }

    @Override
    public void recursiveReveal(int row, int col){
        Tile currentTile = grid[row][col];
        if (currentTile.getState() != Tile.REVEALED){
            currentTile.setState(Tile.REVEALED);
        }
        if (currentTile.getType() == Tile.MINE){
            endGame();
        } else if (currentTile.getType() == Tile.BLANK){
            if (isInBounds(row - 1, col) && grid[row - 1][col].getState() != Tile.REVEALED && grid[row - 1][col].getType() != Tile.MINE && grid[row - 1][col].getState() != Tile.FLAGGED && grid[row - 1][col].getState() != Tile.QUESTION) recursiveReveal(row - 1, col);
            if (isInBounds(row - 1, col + 1) && grid[row - 1][col + 1].getState() != Tile.REVEALED && grid[row - 1][col + 1].getType() != Tile.MINE && grid[row - 1][col + 1].getState() != Tile.FLAGGED && grid[row - 1][col + 1].getState() != Tile.QUESTION) recursiveReveal(row - 1, col + 1);
            if (isInBounds(row, col + 1) && grid[row][col + 1].getState() != Tile.REVEALED && grid[row][col + 1].getType() != Tile.MINE && grid[row][col + 1].getState() != Tile.FLAGGED && grid[row][col + 1].getState() != Tile.QUESTION) recursiveReveal(row, col + 1);
            if (isInBounds(row + 1, col + 1) && grid[row + 1][col + 1].getState() != Tile.REVEALED && grid[row + 1][col + 1].getType() != Tile.MINE && grid[row + 1][col + 1].getState() != Tile.FLAGGED && grid[row + 1][col + 1].getState() != Tile.QUESTION) recursiveReveal(row + 1, col + 1);
            if (isInBounds(row + 1, col) && grid[row + 1][col].getState() != Tile.REVEALED && grid[row + 1][col].getType() != Tile.MINE && grid[row + 1][col].getState() != Tile.FLAGGED && grid[row + 1][col].getState() != Tile.QUESTION) recursiveReveal(row + 1, col);
            if (isInBounds(row + 1, col - 1) && grid[row + 1][col - 1].getState() != Tile.REVEALED && grid[row + 1][col - 1].getType() != Tile.MINE && grid[row + 1][col - 1].getState() != Tile.FLAGGED && grid[row + 1][col - 1].getState() != Tile.QUESTION) recursiveReveal(row + 1, col - 1);
            if (isInBounds(row, col - 1) && grid[row][col - 1].getState() != Tile.REVEALED && grid[row][col - 1].getType() != Tile.MINE && grid[row][col - 1].getState() != Tile.FLAGGED && grid[row][col - 1].getState() != Tile.QUESTION) recursiveReveal(row, col - 1);
            if (isInBounds(row - 1, col - 1) && grid[row - 1][col - 1].getState() != Tile.REVEALED && grid[row - 1][col - 1].getType() != Tile.MINE && grid[row - 1][col - 1].getState() != Tile.FLAGGED && grid[row - 1][col - 1].getState() != Tile.QUESTION) recursiveReveal(row - 1, col - 1);
        }
    }

    @Override
    public void endGame(){
        gameOver = true;
    }

    @Override
    public int getNumNeighboringMines(int row, int col) {
        int count = 0, rowStart = Math.max(row - 1, 0), rowFinish = Math.min(row + 1, grid.length - 1), colStart = Math.max(col - 1, 0), colFinish = Math.min(col + 1, grid.length - 1);

        for (int curRow = rowStart; curRow <= rowFinish; curRow++ ) {
            for (int curCol = colStart; curCol <= colFinish; curCol++ ) {
                if (grid[curRow][curCol].getType() == Tile.MINE) count++;
            }
        }
        return count;
    }

    @Override
    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    @Override
    public int getNumRows(){
        return HEIGHT;
    }

    @Override
    public int getNumCols(){ return WIDTH; }

    @Override
    public Tile[][] getGrid() { return grid; }

    public int getNumMines() {
        return numMines;
    }

    public boolean isGameOver() {
        return gameOver;
    }
    public int getNumMinesLeft() {
        return numMinesLeft;
    }
}