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
import javax.swing.JPanel;
import model.Board;
import model.MovableTetrisPiece;

/**
 * The drawing preview area for the tetris game.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class PreviewPaintPanel extends JPanel implements Observer {

    /** A generated Serialization ID. */ 
    private static final long serialVersionUID = -892615264917407272L;
    
    /** A Font for the preview string text panel. */
    private static final Font PREVIEW_FONT = new Font("Press Start", Font.PLAIN, 25);
    
    /** Represents the preferred width of this component. */
    private static final int WIDTH = 200;
    
    /** Represents the preferred height of this component. */
    private static final int HEIGHT = 200;
    
    /** Represents the corner rounding of preview pieces. */
    private static final int ROUND_CORNERS = 10;
    
    /** Represents the width and height of a tetris preview piece. */
    private static final int PIECE_SIZE = 40;
    
    /** Holds the tetris piece that is going to descend next. */
    private MovableTetrisPiece myPreviewPiece; 
    
    
    /**
     * Constructs a drawing panel with the correct colors and adds this to the observers.
     * 
     * @param theTetrisBoard represents the current tetris board
     */
    public PreviewPaintPanel(final Board theTetrisBoard) {
        super();
        setBackground(Color.DARK_GRAY);
        setForeground(Color.WHITE);                 // not needed
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        theTetrisBoard.addObserver(this);                      
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
        
        g2D.setFont(PREVIEW_FONT);
        g2D.setPaint(Color.WHITE);
        g2D.drawString("Preview", (PIECE_SIZE - ROUND_CORNERS) / 2, 
                                   PIECE_SIZE - ROUND_CORNERS);
  
        if (myPreviewPiece != null) {
            // x and y coordinate to draw rectangles at
            int x = WIDTH / 2;
            int y = HEIGHT / 2;
            
            // Iterate through the piece string to draw the piece
            for (final char character : myPreviewPiece.toString().toCharArray()) {
                g2D.setPaint(Color.CYAN.darker());
                if (character == ' ') {
                    x += PIECE_SIZE;
                } else if (character == '\n') {
                    y += PIECE_SIZE;
                    x = WIDTH / 2;
                } else if (isTetrisPiece(character)) {
                    //or can also just fill the shapes here without an arrayList
                    // if char a O piece
                    if (character == 'O') {
                        g2D.fill(new RoundRectangle2D.Double(x - (PIECE_SIZE * 2), 
                                                             y - (PIECE_SIZE * 2), 
                                                             PIECE_SIZE, PIECE_SIZE, 
                                                             ROUND_CORNERS, ROUND_CORNERS));
                        g2D.setPaint(Color.BLACK);
                        g2D.setStroke(new BasicStroke(2));
                        g2D.draw(new RoundRectangle2D.Double(x - (PIECE_SIZE * 2),
                                                             y - (PIECE_SIZE * 2), 
                                                             PIECE_SIZE, PIECE_SIZE, 
                                                             ROUND_CORNERS, ROUND_CORNERS));
                    // if char an I piece
                    } else if (character == 'I') {
                        g2D.fill(new RoundRectangle2D.Double(x - (PIECE_SIZE * 2), 
                                                         y - (PIECE_SIZE + (PIECE_SIZE / 2)), 
                                                             PIECE_SIZE, PIECE_SIZE, 
                                                             ROUND_CORNERS, ROUND_CORNERS)); 
                        g2D.setPaint(Color.BLACK);
                        g2D.setStroke(new BasicStroke(2));
                        g2D.draw(new RoundRectangle2D.Double(x - (PIECE_SIZE * 2), 
                                                         y - (PIECE_SIZE + (PIECE_SIZE / 2)), 
                                                             PIECE_SIZE, PIECE_SIZE,
                                                             ROUND_CORNERS, ROUND_CORNERS));
                    // all the other pieces
                    } else {
                        g2D.fill(new RoundRectangle2D.Double(
                                                         x - (PIECE_SIZE + (PIECE_SIZE / 2)), 
                                                             y - (PIECE_SIZE * 2), 
                                                             PIECE_SIZE, PIECE_SIZE, 
                                                             ROUND_CORNERS, ROUND_CORNERS));
                        g2D.setPaint(Color.BLACK);
                        g2D.setStroke(new BasicStroke(2));
                        g2D.draw(new RoundRectangle2D.Double(
                                                        x - (PIECE_SIZE + (PIECE_SIZE / 2)), 
                                                             y - (PIECE_SIZE * 2), 
                                                             PIECE_SIZE, PIECE_SIZE, 
                                                             ROUND_CORNERS, ROUND_CORNERS));
                    }
                    x += PIECE_SIZE;
                    g2D.setPaint(Color.CYAN.darker());
                }
            }   
        }          
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
            }
        }
        return tetrisBlock;
    }    

    @Override
    public void update(final Observable theObserve, final Object theArg) {

        if (theArg instanceof  MovableTetrisPiece) {
            myPreviewPiece = (MovableTetrisPiece) theArg;
            repaint();
        }
    }
    
}
