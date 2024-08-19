package no.uib.inf101.tetris.controller;

import java.awt.event.KeyEvent;

import no.uib.inf101.tetris.TetrisModel.*;
import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.view.TetrisView;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class TetrisController implements java.awt.event.KeyListener{

    private ControllableTetrisModel controllableModel;
    private TetrisView tetrisView;
    private Timer timer;
    public TetrisSong song = new TetrisSong();

    //konstruktø
    public TetrisController(ControllableTetrisModel controlledM, TetrisView view){
        this.controllableModel =controlledM;
        this.tetrisView =view;
        this.timer = new Timer(controllableModel.getMilliSec(), this::clockTick);
        this.timer.start();//starter timer
        this.tetrisView.addKeyListener(this);
        tetrisView.setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
 
    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // Left arrow was pressed
            controllableModel.moveTetromino(0, -1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // Right arrow was pressed
            controllableModel.moveTetromino(0, 1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // Down arrow was pressed
            controllableModel.moveTetromino(1, 0);
            timer.restart();
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            // Up arrow was pressed rotates clockwise
            controllableModel.rotate();
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            //starter spillet
            if (controllableModel.getGameState()==GameState.WELCOME_SCREEN){
                    controllableModel.startGame();
                    song.run();
            }
            else if (controllableModel.getGameState()==GameState.ACTIVE_GAME){
                controllableModel.drop();
                timer.restart(); //restarter for hver nye spawn av tetromino
            }
        }
        if(controllableModel.getGameState()==GameState.GAME_OVER){
            song.doStopMidiSounds();//stopper sangen
            song.runEndSong();//starter en annen
        }
        tetrisView.repaint();
    }


    @Override
    public void keyReleased(KeyEvent e) {}

    private void clockTick(ActionEvent evt){
        if (controllableModel.getGameState() ==GameState.ACTIVE_GAME){
            controllableModel.clockTick();
            updateTimerDelay();
            tetrisView.repaint();
        }
    }
    /** Hjelpe metode som oppdaterer tidsdelay
     * Helper method to update timer delay */ 
    private void updateTimerDelay() {
        int delay = controllableModel.getMilliSec();
        timer.setDelay(delay);
        timer.setInitialDelay(delay);
    }
}
