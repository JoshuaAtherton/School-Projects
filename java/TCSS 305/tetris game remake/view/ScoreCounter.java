/*
 * TCSS 305 - Assignment 6: Tetris
 */
package view;

import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import model.Board;
import model.MovableTetrisPiece;

/** 
 * This creates a label that keeps track of the score.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class ScoreCounter extends JLabel implements Observer {
    
    /** A generated Serialization ID. */
    private static final long serialVersionUID = 7894353531959406819L;
    
    /** The amount to increment the score by when adding points. */
    private static final int INCREMENT_SCORE = 4;
    
    /** theName of the label. */
    private final String myName;
    
    /** Hold the value of the current game score. */
    private int myScore;

    /**
     * Create a label with the name and font predefined.
     * 
     * @param theTetrisBoard the board to observe
     * @param theName the initial name for the label
     * @param theFont the font to set this label to
     */
    public ScoreCounter(final Board theTetrisBoard, final String theName, final Font theFont) {
        super();
        myName = theName;
        setFont(theFont);
        theTetrisBoard.addObserver(this);
        myScore = -INCREMENT_SCORE;
        setText(myName + myScore);
    }
    
    /**
     * Reset the values back to original state.
     */
    public void resetScore() {
        myScore = 0;
        setText(myName + myScore);
    }

    @Override
    public void update(final Observable theObserve, final Object theArg) {
                
        // increment the score by 4 every time a tetris piece drops
        if (theArg instanceof MovableTetrisPiece) {
            myScore += INCREMENT_SCORE;
            this.setText(myName + myScore);
        }
                
    }

}
