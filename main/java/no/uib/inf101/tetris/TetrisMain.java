package no.uib.inf101.tetris;

import javax.swing.JFrame;


import no.uib.inf101.tetris.TetrisModel.TetrisBoard;
import no.uib.inf101.tetris.TetrisModel.TetrisModel;
import no.uib.inf101.tetris.TetrisModel.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.TetrisModel.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.controller.TetrisController;
import no.uib.inf101.tetris.view.TetrisView;


public class TetrisMain {
  public static final String WINDOW_TITLE = "INF101 Tetris";

  public static void main(String[] args) {
    //lager brett obj
    TetrisBoard board = new TetrisBoard(20, 10);
    TetrominoFactory factory = new RandomTetrominoFactory();
    //lager tetris model ved å bruke brett-obj
    TetrisModel tetrisModel = new TetrisModel(board, factory);
    //visualiserer tetris modellet
    TetrisView view = new TetrisView(tetrisModel);

    //lager kontrolleren
    TetrisController controller = new TetrisController(tetrisModel, view);
    
    
    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Here we set which component to view in our window
    frame.setContentPane(view);
    // Call these methods to actually display the window
    frame.pack();
    frame.setVisible(true);
    //view.setVisible(true);
  }
  
}
