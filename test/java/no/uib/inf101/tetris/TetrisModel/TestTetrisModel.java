package no.uib.inf101.tetris.TetrisModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;

import no.uib.inf101.tetris.TetrisModel.tetromino.PatternedTetrominoFactory;
import no.uib.inf101.tetris.TetrisModel.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TestTetrisModel {
    @Test
    public void initialPositionOfO() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("O");
        ViewableTetrisModel model = new TetrisModel(board, factory);

        List<GridCell<Character>> tetroCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getAccessibleCellsOfFallingPiece()) {
            tetroCells.add(gc);
            System.out.println(gc);
        }
        assertEquals(4, tetroCells.size());
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'O')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'O')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 4), 'O')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 5), 'O')));
    }

    @Test //egen test for å se lovlig posisjon
    public void positionIsOutOfBound(){
        TetrisBoard board = new TetrisBoard(5, 5);
        TetrisModel model = new TetrisModel(board, new PatternedTetrominoFactory("T"));
        //når den er utenfor
        assertFalse(model.moveTetromino(10, 0));
        assertFalse(model.moveTetromino(-2, 0));
        assertFalse(model.moveTetromino(0, 5));
        //når den er innenfor
        assertTrue(model.moveTetromino(1, 0));
        assertTrue(model.moveTetromino(1, 1));
    }

    @Test
    public void CannotMoveToOccupiedCell() {
        TetrisBoard board = new TetrisBoard(5, 5);
        TetrisModel model = new TetrisModel(board, new PatternedTetrominoFactory("T"));
        board.set(new CellPosition(4, 2), 'Z');
        board.set(new CellPosition(0, 4), 'Z');
        board.set(new CellPosition(3, 2), 'Z');

        assertFalse(model.moveTetromino(3, 0));
        assertFalse(model.moveTetromino(0, 1));
        assertFalse(model.moveTetromino(2, 0));
        assertTrue(model.moveTetromino(0, -1));
    }

    @Test
    public void testClockTick() {
        TetrisBoard board = new TetrisBoard(7, 5);
        TetrominoFactory factory = new PatternedTetrominoFactory("T");
        TetrisModel model = new TetrisModel(board, factory);
        model.fallingTetromino.shiftedToTopCenterOf(board);
        assertEquals(new CellPosition(-1, 1), model.fallingTetromino.tetrominoUpperLeftCornerPosOnGrid);
        //kaller på funksjonen
        model.clockTick();
        // sjekker hvis det har gått ned
        assertEquals(new CellPosition(0, 1), model.fallingTetromino.tetrominoUpperLeftCornerPosOnGrid);
        //kaller på funksjonen
        model.clockTick();
        // sjekker hvis det har gått ned
        assertEquals(new CellPosition(1, 1), model.fallingTetromino.tetrominoUpperLeftCornerPosOnGrid);
    }

    @Test
    public void testDrop() {
        TetrisBoard tetrisBoard = new TetrisBoard(5, 3);
        TetrominoFactory tetrominoFactory = new PatternedTetrominoFactory("S");
        TetrisModel tetrisModel = new TetrisModel(tetrisBoard, tetrominoFactory);
        tetrisModel.fallingTetromino.shiftedToTopCenterOf(tetrisBoard);

        // Call the drop method
        tetrisModel.drop();
        String expectedAfterDrop = String.join("\n", new String[] {
            "---",
            "---",
            "---",
            "-SS",
            "SS-"
        });  
        assertEquals(expectedAfterDrop, tetrisBoard.prettyString());
    }
}




    


