package no.uib.inf101.tetris.TetrisModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;

public class TestTetrisBoard {


    @Test
    public void testRemoveFullRows() {
    

        TetrisBoard board = new TetrisBoard(5, 2);
        board.set(new CellPosition(0, 1), 'T');
        board.set(new CellPosition(1, 0), 'T');
        board.set(new CellPosition(1, 1), 'T');
        board.set(new CellPosition(2, 1), 'T');
        board.set(new CellPosition(4, 0), 'L');
        board.set(new CellPosition(4, 1), 'L');
        board.set(new CellPosition(3, 0), 'L');
        board.set(new CellPosition(2, 0), 'L');

        assertTrue(board.isRowFull(1));
        assertTrue(board.isRowFull(2));
        assertTrue(board.isRowFull(4));
        assertFalse(board.isRowFull(0));
        assertFalse(board.isRowFull(3));
        assertEquals(3, board.removeFullRows());



        String expected = String.join("\n", new String[] {
            "--",
            "--",
            "--",
            "-T",
            "L-"
        });
          // Tester at fulle rader fjernes i brettet:
            // -T
            // TT
            // LT
            // L-
            // LL

        // Tester at fulle rader fjernes i brettet:
        assertEquals(expected, board.prettyString());
    }

}
