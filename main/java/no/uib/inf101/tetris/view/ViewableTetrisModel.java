package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.TetrisModel.GameState;

/**
 * Det er en interface som hjelper
 * TetrisView hente ut  nødvendige informasjonen.
 */
public interface ViewableTetrisModel {

    /**
     * 
     * @return GridDimension -objekt,
     */
    GridDimension getDimension();

    /**
     *  en metode som returnerer et objekt som, når det itereres over,
     *  gir alle posisjonene på brettet med tilhørende symbol.
     * @return Iterable obj
     */
    Iterable<GridCell<Character>> getTilesOnBoard();

    /**
     * tilgjengeliggjør rutene til den fallende brikken 
     * @return Iterable obj
     */
    Iterable<GridCell<Character>> getAccessibleCellsOfFallingPiece();

    /**
     * metode som viser spill status
     */
    GameState getGameState();

    /**
     * viser score til spilleren
     * 
     */
    int getScore();

    /**
     * viser level til spilleren
     * 
     */
    int getLevel();

    
}  
