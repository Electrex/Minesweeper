package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinesweeperModelTest {

    MinesweeperModel testModel = new MinesweeperModel(10,10,12);
    Tile[][] t = new Tile[10][10];

    @Test
    public void getNumRows() {
        assertEquals(10,testModel.getNumRows());
    }

    @Test
    public void getNumCols() {
        assertEquals(10,testModel.getNumCols());
    }

    @Test
    public void getNumMines(){
        assertEquals(12,testModel.getNumMines());
    }

    @Test
    public void getNumMinesLeft() {
        assertEquals(12, testModel.getNumMinesLeft());
    }

    @Test
    public void isInBounds() {
        assertEquals(true, testModel.isInBounds(9,9));
        assertEquals(false, testModel.isInBounds(11,11));
    }

}