/*
 * TCSS 305 - Assignment 6: Tetris
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Board;

/**
 * The drawing area for the tetris game.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class BoardPaintPanel extends JPanel implements Observer {
    
    /** A generated Serialization ID. */  
    private static final long serialVersionUID = -2115338657453269515L;
    
    /** For reseting the frame size. */
    private static final int RESIZE_WINDOW_MIN = -50;
    
    /** String To display when game is over.*/
    private static final String GAME_OVER = "Game Over";
    
    /** Default initial starting piece size. */
    private static final int DEFAULT_PIECE_SIZE = 30;
    
    /** A constant representing the integer 3. */
    private static final int THREE_CONSTANT = 3;
    
    /** A constant representing the integer 4. */
    private static final int FOUR_CONSTANT = 4;
    
    /** A constant representing the integer 9. */
    private static final int NINE_CONSTANT = 9;
    
    /** Represents the preferred width of this component. */
    private final int myBoardWidth;
    
    /** Represents the preferred height of this component. */
    private final int myBoardHeight;
    
    /** Represents the corner rounding of preview pieces. */
    private int myRoundCorners;
    
    /** Represents the width and height of a tetris preview piece. */
    private int myPieceSize;
    
    /** A stroke style to apply to tetris piece outlines. */
    private final BasicStroke myPieceStroke;
    
    /** Integer representation of the stroke thickness of tetris piece outline. */
    private final int myPieceStrokeSize;
     
    /** A string representation of the 2d board. */
    private String myBoardString;  // remember to initialize somewhere

    /** To hold if the game is over or not, true/false. */
    private boolean myGameOver;
    
    /** The frame that this panel is displayed on. */
    private final JFrame myFrame;
    
    /** The tetris Board this class draws. */
    private final Board myTetrisBoard;
    
    /** The background color of this panel. */
    private Color myBackgroundColor;
    
    /**
     * Construct the paint panel for main tetris game play.
     * 
     * @param theFrame frame that this panel is displayed on
     * @param theBoard the board representing the tetris game
     */
    public BoardPaintPanel(final JFrame theFrame, final Board theBoard) {
        super();
        myFrame = theFrame;
        myTetrisBoard = theBoard;
        myPieceSize = DEFAULT_PIECE_SIZE;
        myPieceStrokeSize = (myPieceSize % NINE_CONSTANT) - 1;
        myPieceStroke = new BasicStroke(myPieceStrokeSize);
        myBoardWidth = (myPieceSize * myTetrisBoard.getWidth())  + myPieceStrokeSize;
        myBoardHeight = (myPieceSize * myTetrisBoard.getHeight()) + myPieceStrokeSize;
        myRoundCorners = myPieceSize / THREE_CONSTANT;
                                                                       
        constructorHelper();
    }
    
    /**
     * Help the constructor initialize the this objects initial fields. 
     */
    private void constructorHelper() {
        myBackgroundColor = Color.BLACK;
        setBackground(myBackgroundColor);
        setPreferredSize(new Dimension(myBoardWidth, myBoardHeight));
        myGameOver = false;
    }
    
    /**
     * {@inheritDoc}
     * This is called to repaint the panel.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2D = (Graphics2D) theGraphics;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2D.setPaint(getForeground());  // dont't know if needed
        
        int x = 0;
        int y = -((myPieceSize * FOUR_CONSTANT) + myPieceSize);
                                
        for (final char character : myBoardString.toCharArray()) {
            if (character == ' ') {
                x += myPieceSize;
            } else if (character == '\n') {
                y += myPieceSize;
                x = 0;
            } else if (isTetrisPiece(character)) {
                g2D.setPaint(setPieceColor(character));
                g2D.fill(new RoundRectangle2D.Double(x, y, myPieceSize, myPieceSize, 
                                                     myRoundCorners, myRoundCorners));
                g2D.setPaint(Color.WHITE);
                g2D.setStroke(myPieceStroke);
                g2D.draw(new RoundRectangle2D.Double(x, y, myPieceSize, myPieceSize, 
                                                     myRoundCorners, myRoundCorners));
                x += myPieceSize;
            }
        }
                  
        //paint "Game Over" if the game is over
        if (myGameOver) {
            
            g2D.setPaint(Color.WHITE);
            paintGameOver(GAME_OVER, myPieceSize, (myPieceSize / 2) - 1, myBoardHeight / 2, 
                          g2D);
            
            g2D.setPaint(Color.RED.darker());
            paintGameOver(GAME_OVER, myPieceSize, (myPieceSize / 2) + 2, myBoardHeight / 2, 
                          g2D);  
        }
    }
    
    /**
     * Paint a string on the JPanel.
     * 
     * @param theName the string title to paint
     * @param theFontSize sized of the font to paint
     * @param theX the x coordinate
     * @param theY the y coordinate
     * @param theGraphics the graphics to paint with
     */
    private void paintGameOver(final String theName, final int theFontSize, 
                               final int theX, final int theY, 
                               final Graphics2D theGraphics) {
        theGraphics.setFont(new Font("Press Start", Font.PLAIN, theFontSize));
        theGraphics.drawString(theName, theX, theY);
    }
    
    /**  
     * Set the value of game over value.
     * 
     * @param theValue value to set game over to boolean to
     */
    public void setGameOver(final boolean theValue) {
        myGameOver = theValue;
    }
    
    /** Get the value of if a Tetris game is over or not. 
     * 
     * @return the value of myGameOver
     */
    public boolean isGameOver() {
        return myGameOver;
    }
    
    /**
     * Method to paint the tetris pieces different colors.
     * 
     * @param theCharacter character identifying tetris piece type
     * @return the color to paint piece
     */
    private Color setPieceColor(final char theCharacter) {
        Color tetrisPieceColor = Color.BLUE;
        if (theCharacter == 'I') {
            tetrisPieceColor = Color.MAGENTA;
        } else if (theCharacter == 'J') {
            tetrisPieceColor = Color.BLUE;
        } else if (theCharacter == 'L') {
            tetrisPieceColor = Color.GREEN.darker();
        } else if (theCharacter == 'O') {
            tetrisPieceColor = Color.YELLOW;
        } else if (theCharacter == 'S') {
            tetrisPieceColor = Color.PINK.darker();
        } else if (theCharacter == 'T') {
            tetrisPieceColor = Color.ORANGE;
            // else if (theCharacter == 'Z') change to just an else statement   
        } else if (theCharacter == 'Z') {
            tetrisPieceColor = Color.RED;
        }
        return tetrisPieceColor;
    }


    /**
     * Check if the current character is a tetris piece.
     * 
     * @param theChar the character to examine
     * @return if theChar is what we are after
     */
    private boolean isTetrisPiece(final char theChar) {                         
        boolean tetrisBlock = false;
        final char[] tetrisPieces = {'I', 'J', 'L', 'O', 'S', 'T', 'Z'}; 
        for (final char piece : tetrisPieces) {
            if (theChar == piece) { 
                tetrisBlock = true;
                setPieceColor(piece);
            }
        }
        return tetrisBlock;
    }
    
    /**  
     *  Used to reset the board window size.
     *  
     *  @param thePieceSize new size for the tetris pieces to be
     */
    public void setTetrisGameSize(final int thePieceSize) {
        myPieceSize = thePieceSize;
        //resize the tetris board
        setPreferredSize(new Dimension((myPieceSize * myTetrisBoard.getWidth())  
                                           + myPieceStrokeSize, 
                                       (myPieceSize * myTetrisBoard.getHeight())  
                                           + myPieceStrokeSize));
        setSize(getPreferredSize());
        //resize the top level window frame
        myFrame.setMinimumSize(new Dimension(RESIZE_WINDOW_MIN, RESIZE_WINDOW_MIN));
        myFrame.pack();
        myFrame.setMinimumSize(myFrame.getSize());
        myFrame.setLocationRelativeTo(null);
        repaint();
    }
    
    /**
     * This method is to reset the tetris piece corner size / style.
     * 
     * @param theCornerRounding rounding of the tetris pieces
     */
    public void setTetrisPieceCorners(final int theCornerRounding) {      
        myRoundCorners = myPieceSize / theCornerRounding;
    }
    
    /**
     * Change the background color of this panel.
     * 
     * @param theColor color to set background to
     */
    public void setTetrisBackgroundColor(final Color theColor) {
        myBackgroundColor = theColor;
        setBackground(myBackgroundColor);
    }
    
    @Override
    public void update(final Observable theObserve, final Object theArg) {
        
        if (theArg instanceof String) {
            myBoardString = theArg.toString();
        }
        
        if (theArg instanceof Boolean) {                
            if (((Boolean) theArg).booleanValue()) {
                myGameOver = ((Boolean) theArg).booleanValue();
            } else {                                    
                myGameOver = false;
            }
        }
        repaint();

    }
    
}
