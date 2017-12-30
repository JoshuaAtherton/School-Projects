/*
 * TCSS 305 - Assignment 6: Tetris
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Timer;
import model.Board;

/**
 * Create a timer with the specified delay.
 * 
 * @author Josh Atherton
 * @version 1
 */ 
public class TetrisTimer implements Observer { 
    
    /** Speed up the timer value. */
    private static final Double SPEED_UP_TIMER = 0.55; //.8 I like best. Sped up for sub.. 
    
    /** A timer field. */
    private final Timer myTimer;
    
    /** A Board that will be used to play tetris on. */
    private final Board myBoard;
    
    /** Delay between fire events. */
    private int myDelay;
    
    /** Original delay this timer set to. */
    private final int myOriginalDelay;

    /**
     * Construct a timer to advance game play.
     * 
     * @param theDelay time between timer events
     * @param theBoard a board to play tetris on
     */
    public TetrisTimer(final int theDelay, final Board theBoard) {
        myBoard = theBoard;
        myDelay = theDelay;
        myOriginalDelay = theDelay;
        myTimer = new Timer(myDelay, new TaskPerformer());
    }
    
    
    /**
     * Used to re-set the delay of the timer.
     * 
     * @param theDelay new delay value for timer
     */
    public void setTimerDelay(final int theDelay) {
        myTimer.setDelay(theDelay);
    }

    /**
     * Used to start the timer.
     */
    public void startTimer() {
        myTimer.start();
    }
    
    /**
     * Used to pause timer.
     */
    public void pauseTimer() {
        myTimer.stop();
    }
    
    /**
     * Restart the timer.
     */
    public void restartTimer() {
        myTimer.restart();
        myTimer.setDelay(myOriginalDelay);
    }
    
    /**
     * Speed up the timer with less delay between fire events.
     */
    public void speedUpTimer() {
        if ((int) (myDelay * SPEED_UP_TIMER) > 0) {
            myDelay = (int) (myDelay * SPEED_UP_TIMER);
        } else {
            myDelay = 0;
        }
        myTimer.setDelay(myDelay);
    }
    
    @Override
    public void update(final Observable theObserve, final Object theArg) {
        
        if (theArg instanceof Boolean && ((Boolean) theArg).booleanValue()) {
            myTimer.stop();
        } 
    }
    
    /**
     *  An inner class to implement the methods of ActionListner.
     *  Specifying what to do when timer calls.
     */
    private class TaskPerformer implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myBoard.down();
            
        }
        
    }
    
}

