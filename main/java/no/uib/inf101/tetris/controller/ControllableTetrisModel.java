package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.TetrisModel.GameState;
import no.uib.inf101.tetris.TetrisModel.tetromino.Tetromino;


public interface ControllableTetrisModel {

    /**
     * Metoden skal returnere en boolean som forteller hvorvidt flyttingen faktisk ble gjennomført eller ikke.
     * @param deltaRow
     * @param deltaCol
     * @return boolean
     */
    boolean moveTetromino(int deltaRow, int deltaCol);
    
    /**
     * @return rotated tetromino 
     */
    Tetromino rotate();

    /**
     * @return droped tetromino 
     */
    void drop();

    /**
     * metode som viser spill status
     */
    GameState getGameState();

    GameState startGame();
    /**
     * metode som henter ut hvor mange millisekunder (som integer) det skal være mellom hvert klokkeslag 
     * @return millisekunder (som integer)
     */
    int getMilliSec();
    
    /**
     * er  metoden som kalles hver gang klokken slår.
     */
    void clockTick();



    
} 
