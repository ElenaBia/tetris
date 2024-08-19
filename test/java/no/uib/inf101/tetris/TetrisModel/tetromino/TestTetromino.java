package no.uib.inf101.tetris.TetrisModel.tetromino;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


import no.uib.inf101.tetris.TetrisModel.tetromino.Tetromino;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

import java.util.List;
import java.util.ArrayList;



public class TestTetromino {
    @Test
    public void testHashCodeAndEquals() {
    Tetromino t1 = Tetromino.newTetromino('T');
    Tetromino t2 = Tetromino.newTetromino('T');
    Tetromino t3 = Tetromino.newTetromino('T').shiftedBy(1, 0);
    Tetromino s1 = Tetromino.newTetromino('S');
    Tetromino s2 = Tetromino.newTetromino('S').shiftedBy(0, 0);

    assertEquals(t1, t2);
    assertEquals(s1, s2);
    assertEquals(t1.hashCode(), t2.hashCode());
    assertEquals(s1.hashCode(), s2.hashCode());
    assertNotEquals(t1, t3);
    assertNotEquals(t1, s1);
    }

    @Test
    public void tetrominoIterationOfT() {
        // Create a standard 'T' tetromino placed at (10, 100) to test
        Tetromino tetro = Tetromino.newTetromino('T');
        tetro = tetro.shiftedBy(10, 100);

        // Collect which objects are iterated through
        List<GridCell<Character>> objs = new ArrayList<>();
        for (GridCell<Character> gc : tetro) {
            objs.add(gc);
        }

        // Check that we got the expected GridCell objects
        assertEquals(4, objs.size());
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 100), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'T')));
    }

    //Testing S shape
    @Test
    public void tetrominoIterationOfS() {
        // Create an 'S' Tetromino placed at (10, 100) to test
        Tetromino tetromino = Tetromino.newTetromino('S').shiftedBy(10, 100);
        // Collect which objects are iterated through
        List<GridCell<Character>> objs = new ArrayList<>();
        for (GridCell<Character> gc : tetromino) {
            objs.add(gc);
        }
        // Check that we got the expected GridCell objects
        assertEquals(4, objs.size());
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 100), 'S')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'S')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'S')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'S')));

    }

    @Test
    public void testDoubleDisplacement() {
        Tetromino tetromino = Tetromino.newTetromino('T');
        Tetromino tetrominoAfterSingleMove = tetromino.shiftedBy(1, 0);
        Tetromino tetrominoAfterDoubleMove = tetromino.shiftedBy(2, 0);
        int singleMoveDisplacement = tetrominoAfterSingleMove.tetrominoUpperLeftCornerPosOnGrid.col() - tetromino.tetrominoUpperLeftCornerPosOnGrid.col();
        int doubleMoveDisplacement = tetrominoAfterDoubleMove.tetrominoUpperLeftCornerPosOnGrid.col() - tetromino.tetrominoUpperLeftCornerPosOnGrid.col();
        assertEquals(singleMoveDisplacement * 2, doubleMoveDisplacement);
    }

    @Test
    public void testShiftedToTopCenterOf() {
        Tetromino tetrominoEvenColumns = Tetromino.newTetromino('T').shiftedToTopCenterOf(new Grid(4, 8));
        assertEquals(-1, tetrominoEvenColumns.tetrominoUpperLeftCornerPosOnGrid.row());
        assertEquals(3, tetrominoEvenColumns.tetrominoUpperLeftCornerPosOnGrid.col());
        Tetromino tetrominoOddColumns = Tetromino.newTetromino('T').shiftedToTopCenterOf(new Grid(5, 8));
        assertEquals(-1, tetrominoOddColumns.tetrominoUpperLeftCornerPosOnGrid.row());
        assertEquals(3, tetrominoOddColumns.tetrominoUpperLeftCornerPosOnGrid.col());
    }

    @Test
    public void rotate(){
        Tetromino t1 = Tetromino.newTetromino('T').shiftedToTopCenterOf(new Grid(4, 3));

        t1 =t1.rotate();
        //tar det automatisk lengre ned
        t1 =t1.shiftedBy(1, 0);

        ArrayList<GridCell<Character>> rotatedT1 = new ArrayList<>();
        for(GridCell<Character> cell : t1){
            rotatedT1.add(cell);
        }  
        
        //bare for å visualisere
        for(GridCell<Character> cell : t1){
            System.out.println(cell.pos());           
        } 
        
        assertTrue(rotatedT1.contains(new GridCell<>(new CellPosition(1, 0), 'T')));
        assertTrue(rotatedT1.contains(new GridCell<>(new CellPosition(1, 1), 'T')));
        assertTrue(rotatedT1.contains(new GridCell<>(new CellPosition(2, 1), 'T')));
        assertTrue(rotatedT1.contains(new GridCell<>(new CellPosition(0, 1), 'T')));

    }

}
