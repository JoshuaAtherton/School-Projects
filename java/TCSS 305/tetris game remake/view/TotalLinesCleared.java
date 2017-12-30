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
public class TotalLinesCleared extends JLabel implements Observer {
    
    /** A generated Serialization ID. */
    private static final long serialVersionUID = 7894353531959406819L;
    
    /** Hold the value of the total rows cleared this game. */
    private int myTotalLinesCleared;
    
    /** theName of the label. */
    private final String myName;

    /**
     * Create a label with the name and font predefined.
     * 
     * @param theTetrisBoard the board to observe
     * @param theName the initial name for the label
     * @param theFont the font to set this label to
     */
    public TotalLinesCleared(final Board theTetrisBoard, final String theName, 
                             final Font theFont) {
        super();
        myName = theName;
        setFont(theFont);
        theTetrisBoard.addObserver(this);
        myTotalLinesCleared = 0;
        setText(myName + myTotalLinesCleared);
    }
    
    /**
     * Reset the values back to original state.
     */
    public void resetLinesCleared() {
        myTotalLinesCleared = 0;
        setText(myName + myTotalLinesCleared);
    }

    @Override
    public void update(final Observable theObserve, final Object theArg) {
        
        // increase line counter every time a line clears
        if (theArg.getClass().isArray()) {
            final Integer[] rowArray = (Integer[]) theArg;
            // increment the lines cleared counter
            myTotalLinesCleared += rowArray.length;
            this.setText(myName + myTotalLinesCleared);
        }
        
    }

}
