/*
 * TCSS 305 - Assignment 6: Tetris
 */
package view;

import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import model.Board;

/** 
 * This creates a label that keeps track of the score.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class LevelCounter extends JLabel implements Observer {
    
    /** A generated Serialization ID. */
    private static final long serialVersionUID = 7894353531959406819L;
    
    /** How many lines cleared needed before advancing to next level. */
    private static final int LINES_BEFORE_NEXT_LEVEL = 4;
    
    /** Hold the value of the current game level. */
    private int myLevel;
    
    /** Hold the value of how many lines have been cleared. */
    private int myLinesCleared;
    
    /** The timer that is advancing game play. */
    private final TetrisTimer myTimer;
    
    /** theName of the label. */
    private final String myName;

    /**
     * Create a label with the name and font predefined.
     * 
     * @param theTetrisBoard the board to observe
     * @param theName the initial name for the label
     * @param theFont the font to set this label to
     * @param theTimer a timer for the game
     */
    public LevelCounter(final Board theTetrisBoard, final String theName, final Font theFont, 
                        final TetrisTimer theTimer) {
        super();
        myName = theName;
        setFont(theFont);
        theTetrisBoard.addObserver(this);
        myLevel = 1;
        myLinesCleared = 0;
        myTimer = theTimer;
        setText(myName + myLevel);
    }
    
    /**
     * Reset the values back to original state.
     */
    public void resetLevel() {
        myLevel = 1;
        setText(myName + myLevel);
    }

    @Override
    public void update(final Observable theObserve, final Object theArg) {
        
        // if a line is cleared increment the line counter
        if (theArg.getClass().isArray()) {
            final Integer[] rowArray = (Integer[]) theArg;
            // increment the lines cleared counter
            myLinesCleared += rowArray.length;
            //line counter is > 4: increment level, reset myLinesCleared to 0, speed up timer.
            if (myLinesCleared > LINES_BEFORE_NEXT_LEVEL) {
                myLevel += 1;
                myLinesCleared = 0;
                myTimer.speedUpTimer();
                setText(myName + myLevel);
            }
        }
        
    }

}
