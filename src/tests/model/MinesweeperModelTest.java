package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinesweeperModelTest {

    MinesweeperModel testModel = new MinesweeperModel(10,10,12);

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

//    @Test
//    public void initializeBoard() {
//    }

//    @Test
//    public void recursiveReveal() {
//    }

//    @Test
//    public void endGame() {
//    }

//    @Test
//    public void getNumNeighboringMines() {
//        Maybe test by manually setting Mine tiles around an open tile
//        and testing open tile for x amount of mines
//          [*][ ][*]
//          [ ][ ][*]
//          [*][ ][ ]
//        assertEquals(4, testModel.getNumNeighboringMines(2,2));
//    }

    @Test
    public void isInBounds() {
        assertEquals(true, testModel.isInBounds(9,9));
        assertEquals(false, testModel.isInBounds(11,11));
    }


}