/*
 * TCSS 305 - Assignment 6: Tetris
 */
package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import model.Board;

/**
 * Create the GUI for the tetris game.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class TetrisGUI {
    
    /** Large width for the tetris game. */
    private static final int LARGE_WIDTH = 25;
    
    /** Large height for the tetris game. */
    private static final int LARGE_HEIGHT = 15;
    
    /** Top level window for for game. */
    private final JFrame myFrame;
    
    /** A Board object to display the tetris game on. */
    private Board myTetrisBoard;
    
    /** The branding for this program. */
    private final ImageIcon myLogo;
    
    /** The background music for the game. */
    private Clip myBackgroundSoundClip;
    
    /** 
     * Construct the GUI. 
     */
    public TetrisGUI() {
        myFrame = new JFrame("Tetris");
        myLogo = new ImageIcon(this.getClass().getResource("/resources/Tetris_Logo.png"));
        chooseSize();
        createBackgroundMusic();
    }
    
    /** Choose the size of the tetris Board. */
    private void chooseSize() {
        final Object[] options = {"Normal", "Large"};
        final int result = JOptionPane.showOptionDialog(myFrame,
                      "Normal is a 10 X 20 tetris game. Large is 20 X 15",
                       "Choose a tetris game size",
                       JOptionPane.YES_NO_OPTION,
                       JOptionPane.QUESTION_MESSAGE,
                       null, options, options[0]); 
        if (result == 0) {
            myTetrisBoard = new Board();
        } else {
            myTetrisBoard = new Board(LARGE_WIDTH, LARGE_HEIGHT);
        }
        
    }
    
    /**
     * Setup the GUI. 
     */
    protected void setup() {
        //register a custom font to this graphics environment
        createTetrisFont();
        
        //loop the background music
        myBackgroundSoundClip.loop(Clip.LOOP_CONTINUOUSLY);
        
        // create the tetris timer
        final TetrisTimer timer = new TetrisTimer(1000, myTetrisBoard);
        //add timer to board subscriber list
        myTetrisBoard.addObserver(timer);
        
        //create the tetris drawing board
        final BoardPaintPanel boardPaintPanel = new BoardPaintPanel(myFrame, myTetrisBoard);
        //create a JPanel
        final JPanel tetrisPaint = new JPanel();
        //add tetrisBoad to that panel
        tetrisPaint.add(boardPaintPanel);
        //create lowered bevel and set paint panel to lowered bevel border
        final Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        tetrisPaint.setBorder(loweredbevel);

        // add key listener
        final KeyListenerControls keyListener = new KeyListenerControls(myTetrisBoard, 
                                                                        boardPaintPanel);
        myFrame.addKeyListener(keyListener);
        
        //Create the left panel
        final ScoreLevelStart left = new ScoreLevelStart(myTetrisBoard, timer, 
                                                         boardPaintPanel, keyListener);
        //create the menu bar
        final GUIMenuBar menuBar = new GUIMenuBar(myFrame, boardPaintPanel, timer, 
                                                  keyListener, myTetrisBoard, left);
        //add the menuBar to the top level window
        myFrame.setJMenuBar(menuBar);
        
        //add the tetris board JPanel to top level window and position in the center
        myFrame.add(tetrisPaint, BorderLayout.CENTER);

        //add board paint panel to observer subscriber list
        myTetrisBoard.addObserver(boardPaintPanel);
            
        //set this frame to be focusable
        myFrame.setFocusable(true);
        
        // add left panel to subscribers
        myTetrisBoard.addObserver(left);
        // add to the frame
        myFrame.add(left, BorderLayout.WEST);
        
        // create and add the right panel to myFrame
        final PreviewPieceControls right = new PreviewPieceControls(myTetrisBoard);
        myFrame.add(right, BorderLayout.EAST);
        
        //start the first game
        myTetrisBoard.newGame(); 
        
        myFrame.setIconImage(myLogo.getImage());
        
        //setup the frame size, positioning and exit action
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //could do a pop-up here
        myFrame.pack();
        myFrame.setMinimumSize(myFrame.getSize());
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    }
    
    /**
     * Create the background music for the tetris game.
     * 
     * original tetris theme: https://www.youtube.com/watch?v=NmCCQxVBfyM
     */
    private void createBackgroundMusic() {
        try {
            myBackgroundSoundClip = AudioSystem.getClip();
            final AudioInputStream audioInput  = AudioSystem.getAudioInputStream(
                                                 this.getClass().getResource
                                                 ("/resources/tetris_background_music.wav"));
            myBackgroundSoundClip.open(audioInput);
        } catch (final LineUnavailableException | IOException 
                       | UnsupportedAudioFileException e) {
            System.out.println("Music not found: " + e);
            e.printStackTrace(); 
        }
 
    }
    
    /**
     * Add a custom font from this program to the Graphics Environment.
     * 
     * This font was found at: http://www.1001fonts.com/press-start-font.html
     * created by CodeMan38. The creators website: http://www.zone38.net/font/
     */
    private void createTetrisFont() {
        try {
            final URL url = getClass().getResource("/resources/press-start.ttf");
            final File file = new File(url.getPath());
            final Font font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(file));
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (final FontFormatException | IOException e) {
            System.out.println("Font not found: " + e);
            e.printStackTrace();
        }
        
    }
}
