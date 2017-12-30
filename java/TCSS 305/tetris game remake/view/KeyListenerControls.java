/*
 * TCSS 305 - Assignment 6: Tetris
 */
package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import model.Board;

/**
 * A key listener with the control keys to listen for with the tetris board.
 * 
 * @author Josh Atherton
 * @version 1
 */ 
public class KeyListenerControls extends KeyAdapter {
    
    /** Up key controls. */
    private static final int[] UP_KEY = {KeyEvent.VK_UP, KeyEvent.VK_W};  
    
    /** Left key controls. */
    private static final int[] LEFT_KEY = {KeyEvent.VK_LEFT, KeyEvent.VK_A};
    
    /** Right key controls. */
    private static final int[] RIGHT_KEY = {KeyEvent.VK_RIGHT, KeyEvent.VK_D};
    
    /** Down key controls. */
    private static final int[] DOWN_KEY = {KeyEvent.VK_DOWN, KeyEvent.VK_S};
    
    /** Drop key controls. */
    private static final int DROP_KEY = KeyEvent.VK_SPACE;

    /** Board to perform methods on. */
    private final Board myTetrisBoard;
    
    /** Paint panel representing a Tetris board game. */
    private final BoardPaintPanel myBoardPaintPanel;

    /** If the keys should be enabled or disabled. */
    private boolean myKeysEnabled;
    
    /**
     * Create the keyboard commands to control the tetris board.
     * 
     * @param theTetrisBoard board to draw tetris board on
     * @param theBoardPaintPanel panel that tetris game is painted on
     */
    public KeyListenerControls(final Board theTetrisBoard, 
                               final BoardPaintPanel theBoardPaintPanel) {
        super();
        myTetrisBoard = theTetrisBoard;
        myBoardPaintPanel = theBoardPaintPanel;
        myKeysEnabled = false;
    }
    
    /**
     * Helper method of keyPressed to search and find if the key event 
     * was an event we care about.
     * 
     * @param theKeys an array of keys to search through
     * @param theSearchKey key to look for in int form
     * @return if the key was found or not
     */
    private boolean getKey(final int[] theKeys, final int theSearchKey) {
        boolean hasKey = false;
        for (final int key : theKeys) {
            if (key == theSearchKey) {
                hasKey = true;
            }
        }
        return hasKey;
    }
    
    /** Set if the keys should be enabled or disabled. 
     * 
     * @param theKeysState a boolean passed in to specify new state of the keys
     */
    public void setKeysStatus(final Boolean theKeysState) {
        myKeysEnabled = theKeysState;
    }
    //if board not paused, game not over then enable key listeners
    
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        final int keys = theEvent.getKeyCode();
        //if game over is true disable the key listeners
        if (!(myBoardPaintPanel.isGameOver()) && myKeysEnabled) {            
            if (getKey(UP_KEY, keys)) {
                myTetrisBoard.rotate();
            } else if (getKey(LEFT_KEY, keys)) {
                myTetrisBoard.left();
            } else if (getKey(RIGHT_KEY, keys)) {
                myTetrisBoard.right();
            } else if (getKey(DOWN_KEY, keys)) {
                myTetrisBoard.down();
            } else if (DROP_KEY == keys) {
                myTetrisBoard.drop();
            }
        }
    }

}
