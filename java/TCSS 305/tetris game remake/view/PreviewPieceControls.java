/*
 * TCSS 305 - Assignment 6: Tetris
 */
package view;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Board;

/**
 * The right panel that has a preview panel and displays controls.
 * 
 * @author Josh Atherton
 * @version 1
 */ 
public class PreviewPieceControls extends JPanel {
    
    /** a generated version Id for serialization. */
    private static final long serialVersionUID = -3718068614474419976L;
    
    /** represents the preferred width of this component. */
    private static final int WIDTH = 220;
    
    /** represents the preferred height of this component. */
    private static final int HEIGHT = 0;
    
    /** A Font for this component. */
    private static final Font CONTROLS_FONT = new Font("Press Start", Font.PLAIN, 10);
    
    /** A Board Object to be connected to representing tetris game. */
    private final Board myTetrisBoard;
    
    
    /**
     * Constructor to initialize one field and setup a side panel.
     * 
     * @param theTetrisBoard a Board representing tetris game
     */
    public PreviewPieceControls(final Board theTetrisBoard) {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        myTetrisBoard = theTetrisBoard;
        setup();
    }

    /**
     * Constructor helper method. 
     */
    private void setup() {
        
        //top box to paint preview of next piece 
        final JPanel previewPanel = new JPanel();
        //add new panel to grid
        add(previewPanel);
        //add PreviewPaintPanel to the preview panel
        previewPanel.add(new PreviewPaintPanel(myTetrisBoard));
        
        //bottom grid box
        
        final JLabel controls = new JLabel("Controls");
        controls.setFont(CONTROLS_FONT);
        add(controls);
        
        final JLabel rotate = new JLabel("Rotate: W or Up Key");
        rotate.setFont(CONTROLS_FONT);
        add(rotate);

        final JLabel left = new JLabel("Left: A or Left Key");
        left.setFont(CONTROLS_FONT);
        add(left);
        
        final JLabel right = new JLabel("Right: D or Right Key");
        right.setFont(CONTROLS_FONT);
        add(right);
        
        final JLabel down = new JLabel("Down: S or Down Key");
        down.setFont(CONTROLS_FONT);
        add(down);
        
        final JLabel drop = new JLabel("Drop: Space Bar");
        drop.setFont(CONTROLS_FONT);
        add(drop);
    
    }
    
}
