package no.uib.inf101.tetris.TetrisModel.tetromino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import no.uib.inf101.tetris.TetrisModel.tetromino.PatternedTetrominoFactory;
import no.uib.inf101.tetris.TetrisModel.tetromino.Tetromino;
import no.uib.inf101.tetris.TetrisModel.tetromino.TetrominoFactory;


public class TestPatternedTetrominoFactory {

    @Test
    public void sanityTestPatternedTetrominoFactory() {
    TetrominoFactory factory = new PatternedTetrominoFactory("TSZ");
        assertEquals(Tetromino.newTetromino('T'), factory.getNext());
        assertEquals(Tetromino.newTetromino('S'), factory.getNext());
        assertEquals(Tetromino.newTetromino('Z'), factory.getNext());
        assertEquals(Tetromino.newTetromino('T'), factory.getNext());
        assertEquals(Tetromino.newTetromino('S'), factory.getNext());
    }

}
