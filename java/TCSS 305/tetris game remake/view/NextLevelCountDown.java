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
public class NextLevelCountDown extends JLabel implements Observer {
    
    /** A generated Serialization ID. */
    private static final long serialVersionUID = 7894353531959406819L;
    
    /** How many levels must be passed before next level reached. */
    private static final int NEXT_LVL_COUNTER = 5;
    
    /** Hold the value of rows to clear before reaching the next level. */
    private int myNextLevelCountDown;
    
    /** theName of the label. */
    private final String myName;

    /**
     * Create a label with the name and font predefined.
     * 
     * @param theTetrisBoard the board to observe
     * @param theName the initial name for the label
     * @param theFont the font to set this label to
     */
    public NextLevelCountDown(final Board theTetrisBoard, final String theName, 
                              final Font theFont) {
        super();
        myName = theName;
        setFont(theFont);
        theTetrisBoard.addObserver(this);
        myNextLevelCountDown = NEXT_LVL_COUNTER;
        setText(myName + myNextLevelCountDown);
    }
    
    /**
     * Reset the values back to original state.
     */
    public void resetLevelCountDown() {
        myNextLevelCountDown = NEXT_LVL_COUNTER;
        setText(myName + myNextLevelCountDown);
    }

    @Override
    public void update(final Observable theObserve, final Object theArg) {
        
        // decrement the next level counter: if counter < 0 reset counter
        if (theArg.getClass().isArray()) {
            final Integer[] rowArray = (Integer[]) theArg;
            myNextLevelCountDown -= rowArray.length;
            // decrement line counter if can
            if (myNextLevelCountDown >= 0) {
                this.setText(myName + myNextLevelCountDown);
            } else { 
                myNextLevelCountDown += NEXT_LVL_COUNTER;
            }
            setText(myName + myNextLevelCountDown);
        }   
    }

}
