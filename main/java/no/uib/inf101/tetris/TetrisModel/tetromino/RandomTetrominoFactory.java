package no.uib.inf101.tetris.TetrisModel.tetromino;
import java.util.Random;
import no.uib.inf101.tetris.TetrisModel.tetromino.*;

/**
 * klasse
 */
public class RandomTetrominoFactory implements TetrominoFactory{

    @Override
    public Tetromino getNext() {
        //lager random obj src: https://stackoverflow.com/questions/21726033/picking-a-random-item-from-an-array-of-strings-in-java
        Random random = new Random();
        Character[] charChoices = {'L','J','S','Z','T','I','O'};
        int index = random.nextInt(charChoices.length);
        char randomLetter = charChoices[index];
        Tetromino nexTetromino = Tetromino.newTetromino(randomLetter);
        return nexTetromino;
    }
    
}
