package no.uib.inf101.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.tetris.TetrisModel.TetrisBoard;

public class TestTetrisBoard {
    @Test
    //test for å se om vi får til å hente strengene fra griden
    public void prettyStringTest() {
        TetrisBoard board = new TetrisBoard(3, 4);
        board.set(new CellPosition(0, 0), 'g');
        board.set(new CellPosition(0, 3), 'y');
        board.set(new CellPosition(2, 0), 'r');
        board.set(new CellPosition(2, 3), 'b');
        String expected = String.join("\n", new String[] {
            "g--y",
            "----",
            "r--b"
    });
    
    assertEquals(expected, board.prettyString());
}

}
