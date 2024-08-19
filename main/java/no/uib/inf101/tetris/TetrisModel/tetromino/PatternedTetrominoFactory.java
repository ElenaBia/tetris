package no.uib.inf101.tetris.TetrisModel.tetromino;

public class PatternedTetrominoFactory implements TetrominoFactory {

    private String pattern;
    private int currentIndex;
    //konstruktør
    public PatternedTetrominoFactory(String string){
        this.pattern = string;
        this.currentIndex = 0;
    }
    @Override
    public Tetromino getNext() {
        char nextSymbol = pattern.charAt(currentIndex);
        currentIndex = (currentIndex + 1) % pattern.length(); 
        return Tetromino.newTetromino(nextSymbol);
    }
    
    
}
r