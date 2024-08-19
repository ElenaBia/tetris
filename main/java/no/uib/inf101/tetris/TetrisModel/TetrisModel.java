package no.uib.inf101.tetris.TetrisModel;


import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.view.ViewableTetrisModel;
import no.uib.inf101.tetris.TetrisModel.tetromino.Tetromino;
import no.uib.inf101.tetris.TetrisModel.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;

public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {
    //lager instans variabel
    TetrisBoard tertrisBoard;
    TetrominoFactory tetrominoFactory;
    //representerer fallende brikker
    Tetromino fallingTetromino;
    public GameState gameState;
    //for level og score
    private int currentLevel;
    private int score;


    //konstruktør
    public TetrisModel(TetrisBoard board, TetrominoFactory factory){
        this.tetrominoFactory =factory;
        this.tertrisBoard=board;
        this.fallingTetromino = tetrominoFactory.getNext();
        this.fallingTetromino = this.fallingTetromino.shiftedToTopCenterOf(tertrisBoard);
        this.currentLevel =1;
        this.score =0;
        this.gameState = GameState.WELCOME_SCREEN;
    }


    @Override
    public GridDimension getDimension() {
        return this.tertrisBoard;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return this.tertrisBoard;
    }

    @Override
    public Iterable<GridCell<Character>> getAccessibleCellsOfFallingPiece() {
        return this.fallingTetromino;
    }

    @Override
    public boolean moveTetromino(int deltaRow, int deltaCol) {
        
        Tetromino candidate = this.fallingTetromino.shiftedBy(deltaRow, deltaCol);
        if (isPositionAllowed(candidate)) {
            this.fallingTetromino = candidate;
            return true; // Lovlig posisjon , return true
        } else {
            return false; // ulovlig posisjon, return false
        }
    }
    /**
     * sjekker om en gitt Tetromino har en lovlig plass på brettet: det vil si, hvis hele brikken 
     * befinner seg innenfor brettets rammer, og ingen av rutene den okkuperer allerede er fargelagt 
     * av en flis på brettet, er posisjonen lovlig.
     * @param piece - Tetromino type
     * @return boolean
     */
    private boolean isPositionAllowed(Tetromino piece) {
        for (GridCell<Character> cell : piece) {           
            CellPosition pos = cell.pos();
            int row =pos.row();
            int col =pos.col();
            //sjekker 
            if (row < 0 || row >= tertrisBoard.rows()|| col < 0 || col >= tertrisBoard.cols()) {
                return false; // ulovlig posisjon, return false
            }
            //sjekker verdien
            if (tertrisBoard.get(pos) != '-') {
                return false; // ulovlig posisjon,  cellen er opptatt
            }
        }
        return true; // posisjon er lovlig
    }

    @Override
    public Tetromino rotate() {
        Tetromino rotatedTetromino = this.fallingTetromino.rotate();
        if (isPositionAllowed(rotatedTetromino)) {
            this.fallingTetromino = rotatedTetromino;
            return rotatedTetromino; // Lovlig posisjon , return brikken
        } else {
            throw new IllegalArgumentException("System Error");}
    }

    @Override
    public void drop() {
        //så lenge det er mulig å flytte den nedover så gjør vi det
        while (moveTetromino(1, 0)) {
            fallingTetromino.shiftedBy(1, 0);
        }
        //til slutt henter vi bare ny brikke
        lockAndSpawnNewTetromino();
    }
    /**
     * får ny tetromino til på komme opp
     */
    private void spawnNewTetromino() {
        Tetromino candidate = tetrominoFactory.getNext().shiftedToTopCenterOf(tertrisBoard);
        if (!isPositionAllowed(candidate)) {
            this.gameState = GameState.GAME_OVER;
        }   
        else{
            this.fallingTetromino = candidate;
        }     
    }
    /**
     * limer brikken og henter ny tetromino
     */
    private void lockAndSpawnNewTetromino() {
        for (GridCell<Character> cell : fallingTetromino) {
            CellPosition pos = cell.pos();
            tertrisBoard.set(pos,cell.value()); 
        }
        int linesCleared = tertrisBoard.removeFullRows();
        score = calculatedScore(linesCleared);
        calculateLevel();
        spawnNewTetromino();
    }

   /**
    * regner ut nivået i forhold til scoren
    */
    private void calculateLevel() {
        int scoreLevelChange =200; //etter 200 poeng så går man til neste nivå
        currentLevel = score/scoreLevelChange +1;
    }

 
    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    @Override
    public int getMilliSec() {
        int baseDelay = 1000;
        int delayDecreasePerLevel = 50; // minker delayen for hver level 
        int delay = baseDelay - (delayDecreasePerLevel * (currentLevel - 1)); 
        return delay;
    }
   
    

    @Override
    public void clockTick() {
        this.fallingTetromino.shiftedBy(0,1);//flytter nedover
        //sjekker hvis flyttningen skjedde 
        if(!moveTetromino(1, 0)){
            lockAndSpawnNewTetromino();
        }
    }


    @Override
    public int getScore() {
        return score;
    }
 
    @Override
    public int getLevel() {
        return this.currentLevel;
    }

    /**
     * Metode som regner ut scoren
     */
    private int calculatedScore(int linesCleared) {
        switch (linesCleared) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 300;
                break;
            case 3:
                score += 500;
                break;
            case 4:
                score += 800;
                break;
            default:
                break;
        }
       
        return score;
    }
    /**
     * hjelper med startscreen
     * @return welcome gamestate
     */
    public GameState startGame(){
        this.gameState =GameState.ACTIVE_GAME;
        return this.gameState;
    }

 
}
