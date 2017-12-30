/*
 * TCSS 305 - Assignment 6: Tetris
 */
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import model.Board;

/**
 * Create the menu bar for the tetris game.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class GUIMenuBar extends JMenuBar implements Observer, PropertyChangeListener {

    /** a generated version Id for serialization. */
    private static final long serialVersionUID = 7424826357471921516L; 
    
    /** Square corners on tetris pieces. */
    private static final int SQUARE_CORNERS = 25;
    
    /** Rounded Rectangle corners on tetris pieces. */
    private static final int ROUNDED_RECTANGEL_CORNERS = 3;
    
    /** A background color for tetris game. */
    private static final Color BLACK_BACKGROUND = new Color(0, 0, 0);
    
    /** A background color for tetris game. */
    private static final Color GREEN_BACKGROUND = new Color(113, 130, 83);
    
    /** A background color for tetris game. */
    private static final Color CREAM_BACKGROUND = new Color(225, 237, 203);
    
    /** The frame to attach this menu to. */
    private final JFrame myFrame;
    
    /** A JPanel that paints the tetris game. */
    private final BoardPaintPanel myPaintPanel;
    
    /** Tetris timer for the game. */
    private final TetrisTimer myTimer;
    
    /** Key listener for this game. */
    private final KeyListenerControls myKeyListener;
    
    /** The pause menu item. */
    private final JMenuItem myPause;
    
    /** The resume meny item. */
    private final JMenuItem myResume;
    
    /** The left panel of the tetris game. */
    private final ScoreLevelStart myLeftPanel;
    
    /**
     * Construct the menu bar.
     * 
     * @param theFrame the JFrame which will contain this JMenuBar
     * @param thePaintPanel that paints the tetris game
     * @param theTimer a timer to advance the game
     * @param theKeyListener key listener for the game
     * @param theTetrisBoard board this game uses for tetris
     * @param theLeftPanel left panel on the gui
     */
    public GUIMenuBar(final JFrame theFrame, final BoardPaintPanel thePaintPanel,
                      final TetrisTimer theTimer, final KeyListenerControls theKeyListener, 
                      final Board theTetrisBoard, final ScoreLevelStart theLeftPanel) {
        super();
        myFrame = theFrame;
        myPaintPanel = thePaintPanel;
        myTimer = theTimer;
        myKeyListener = theKeyListener;
        myPause = new JMenuItem("Pause");
        myResume = new JMenuItem("Resume");
        myLeftPanel = theLeftPanel;
        theTetrisBoard.addObserver(this);
        setup();
    }
    
    /**
     * Setup the menu category items.
     */
    private void setup() {
        myLeftPanel.addPropertyChangeListener(this);
        createModeMenu();
        createSizeMenu();
        aboutMenu();
    }

    /**
     * Create the mode menu.
     */
    private void createModeMenu() {
        final JMenu game = new JMenu("Game");
        
//        myPause.setEnabled(myPaintPanel.isGameOver());
        myPause.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_P));
        myPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myTimer.pauseTimer(); 
                myKeyListener.setKeysStatus(false);
            }
        });

//        myResume.setEnabled(myPaintPanel.isGameOver());
        myResume.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_O));
        myResume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myTimer.startTimer(); 
                myKeyListener.setKeysStatus(true);
            }
        });

        add(game);
        game.add(myPause);
        game.add(myResume);
        
        myFrame.add(this); 
    }
    
    
    
    /**
     * Create the size menu.
     */
    private void createSizeMenu() {
        final JMenu size = new JMenu("Options");
        final JMenu chooseSize = new JMenu("Display Size");
        final ButtonGroup sizeButtons = new ButtonGroup();
        
        final JRadioButtonMenuItem small = createSizeButton("Small", 18);
        final JRadioButtonMenuItem medium = createSizeButton("Shmedium", 23);
        final JRadioButtonMenuItem large = createSizeButton("Large", 30);
        final JRadioButtonMenuItem bigBoy = createSizeButton("Big Daddy Size", 40);
        
        chooseSize.add(small);
        chooseSize.add(medium);
        chooseSize.add(large);
        chooseSize.add(bigBoy);
        
        sizeButtons.add(small);
        sizeButtons.add(medium);
        sizeButtons.add(large);
        sizeButtons.add(bigBoy);
        
        add(size);
        size.add(chooseSize);
        
        // add the tetris board size selector
        addPieceCornerStyleMenu(size);
        
        //add a background color chooser
        addBackgroudColorMenu(size);
                
        myFrame.add(this); 
    }
    
    /**
    * Create a menuitem group that changes the tetris background color. 
    * 
    * @param theMenu menu to add this menu items to 
    */
    private void addPieceCornerStyleMenu(final JMenu theMenu) {
        theMenu.addSeparator();
        final JLabel tetrisBoard = new JLabel("Tetris Piece Style");
        theMenu.add(tetrisBoard);
       
        final ButtonGroup boardSize = new ButtonGroup();
        final JRadioButtonMenuItem smallButton = new JRadioButtonMenuItem("Square"); 
        smallButton.addActionListener(event -> {
            myPaintPanel.setTetrisPieceCorners(SQUARE_CORNERS);
        });
       
        final JRadioButtonMenuItem normalButton = new JRadioButtonMenuItem("Circle"); 
        normalButton.addActionListener(event -> {
            myPaintPanel.setTetrisPieceCorners(1);
        });
       
        final JRadioButtonMenuItem largeButton = new JRadioButtonMenuItem("Round Rectangle"); 
        largeButton.addActionListener(event -> {
            myPaintPanel.setTetrisPieceCorners(ROUNDED_RECTANGEL_CORNERS);
        });
       
        boardSize.add(smallButton);
        boardSize.add(normalButton);
        boardSize.add(largeButton);
       
        theMenu.add(smallButton);
        theMenu.add(normalButton);
        theMenu.add(largeButton);
    }
    
    /**
     * Create a menuitem group that changes the tetris background color. 
     * 
     * @param theMenu menu to add this menu items to 
     */
    private void addBackgroudColorMenu(final JMenu theMenu) {
        theMenu.addSeparator();
        final JLabel tetrisColor = new JLabel("Tetris Background Color");
        theMenu.add(tetrisColor);
        
        final ButtonGroup boardColor = new ButtonGroup();
        final JRadioButtonMenuItem blackButton = new JRadioButtonMenuItem("Black"); 
        blackButton.addActionListener(event -> {
            myPaintPanel.setTetrisBackgroundColor(BLACK_BACKGROUND);
        });
        
        final JRadioButtonMenuItem greenButton = new JRadioButtonMenuItem("Green"); 
        greenButton.addActionListener(event -> {
            myPaintPanel.setTetrisBackgroundColor(GREEN_BACKGROUND);
        });
        
        final JRadioButtonMenuItem creamButton = new JRadioButtonMenuItem("Cream"); 
        creamButton.addActionListener(event -> {
            myPaintPanel.setTetrisBackgroundColor(CREAM_BACKGROUND);
        });
        
        boardColor.add(blackButton);
        boardColor.add(greenButton);
        boardColor.add(creamButton);
        
        theMenu.add(blackButton);
        theMenu.add(greenButton);
        theMenu.add(creamButton);
    }

    /**
     * Create a JRadioMenuButton to control the size of the tetris Board.
     * Note: the tetris board is drawn relative to a tetris piece block size.
     * 
     * @param theName name of the JRadioMenu button returned
     * @param theSize the size to resize the tetris piece to
     * @return a JRadioMenu button with included action
     */
    private JRadioButtonMenuItem createSizeButton(final String theName, final int theSize) {
        final JRadioButtonMenuItem aButton = new JRadioButtonMenuItem(theName);
        aButton.addActionListener(event -> { 
            myPaintPanel.setTetrisGameSize(theSize);
        });
        return aButton;
    }
        
    /**
     * Create the about menu.
     */
    private void aboutMenu() {
        final JMenu about = new JMenu("about");
        final JMenuItem aboutPane = new JMenuItem("about...");
        aboutPane.addActionListener(event -> createAboutPane());
        final JMenuItem rulesPane = new JMenuItem("rules..."); 
        rulesPane.addActionListener(event -> createRulesPane());
        
        add(about);
        about.add(aboutPane);
        about.add(rulesPane);
        myFrame.add(this);
   
    }
    
    /**
     * The about pane pop-up.
     */
    private void createAboutPane() {
        final String aboutMessage = 
            "TCSS 305 Tetris\nAutumn 2017\nJosh Atherton\n"
            + "\nBackend written by TCSS instructors\n" 
            + "\nTheme by JTatto http://www.jtattoo.net/"
            + "\nfont by CodeMan38 https://www.zone38.net/font/\n"
            + "\nMusic: original tetris theme https://www.youtube.com/watch?v=NmCCQxVBfyM";
        JOptionPane.showMessageDialog(null, 
                     aboutMessage, "About", JOptionPane.INFORMATION_MESSAGE);  
    }
    
    /**
     * The rules/how to play pane pop-up.
     */
    private void createRulesPane() {
        final String rulesMessage = 
                         "How to play:\n\n"
                        + "Try to fit the tetris pieces as tight as possible.\n"
                        + "Every new piece adds 4 points to your score.\n"
                        + "Try to stay alive as long as possible, Keeping up\n"
                        + "is harder than it seems as the drop speed increases!\n\n"
                        + "Controls:\n\n"
                        + "Rotate piece: W key or up arrow key\n"
                        + "Move left: A key or left arrow key\n"
                        + "Move right: D key or right arrow key\n"
                        + "Move down: S key or down arrow key\n"
                        + "Drop: spacebar\n\n"
                        + "Keyboard Commands:\n\n"
                        + "Pause: shift + P\n"
                        + "Resume: shift + O\n";
                        
        JOptionPane.showMessageDialog(null, 
                     rulesMessage, "How To Play", JOptionPane.INFORMATION_MESSAGE);  
    }
        
    /**
     * This changes the value of the pause and resume button to enabled or disabled.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("endGame".equals(theEvent.getPropertyName())) {
            //set the buttons to be disabled
            myPause.setEnabled(false);
            myResume.setEnabled(false);      
        }
    }
    
    @Override
    public void update(final Observable theObservable, final Object theArg) { 

        //if my endGameButton.isEnabled(false): set accelerators to false
        if (!(myLeftPanel.gameEndedButtonEnabled())) {
            myPause.setEnabled(false);
            myResume.setEnabled(false);  
        //else myGameEndedButton enabled: set Accelerators to true
        } 
        if (myLeftPanel.gameEndedButtonEnabled()) {
            myPause.setEnabled(true);
            myResume.setEnabled(true); 
        }
        
        if (theArg instanceof Boolean && ((Boolean) theArg).booleanValue()) {                
            myKeyListener.setKeysStatus(false);
            myPause.setEnabled(false);
            myResume.setEnabled(false);
        }

    } 
}
