/*
 * TCSS 305 - Assignment 6: Tetris
 */
package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Board;

/**
 * The left panel of the game has a title, level, score and buttons to control the game.
 * 
 * @author Josh Atherton
 * @version 1
 */ 
public class ScoreLevelStart extends JPanel implements Observer {
    
    /** A generated version Id for serialization. */
    private static final long serialVersionUID = -7912806076632042277L;
    
    /** Font selected for this panel represented as a string. */
    private static final String PANEL_FONT = "Press Start";
    
    /** Font family and size for TetrisTitle in this class. */
    private static final Font TETRIS_TITLE_FONT = 
                    new Font(PANEL_FONT, Font.PLAIN, 22);

    /** Font family and size for JLabels in this class. */
    private static final Font TETRIS_LABEL_FONT = 
                    new Font(PANEL_FONT, Font.PLAIN, 18);
    
    /** Font family and size for JLabels in this class. */
    private static final Font TETRIS_BUTTON_FONT = 
                    new Font(PANEL_FONT, Font.PLAIN, 14);
    
    /** Preferred width of this component. */
    private static final int PREFERRED_WIDTH = 225;
    
    /** A Board that we are playing tetris on. */
    private final Board myTetrisBoard;
    
    /** A timer that is advancing game play. */
    private final TetrisTimer myTimer;

    /** A paint panel that a tetris board is painted on. */
    private final BoardPaintPanel myBoardPaintPanel;

    /** A key listener for this frame. */
    private final KeyListenerControls myKeyListener;
   
    /** The resume button. */
    private JButton myResume;
    
    /** The pause button. */
    private JButton myPause;
    
    /** The new game button. */
    private JButton myNewGame;
    
    /** The end game button. */
    private JButton myEndGame;
    
    /**
     * construct the panel initializing the two fields and setting defaults.
     * 
     * @param theBoard the Board that we are playing tetris on
     * @param theTimer the timer that is advancing game play.
     * @param theBoardPaintPanel panel that a tetris board is painted on
     * @param theKeyListener a keyListener for frame this panel attaches to
     */
    public ScoreLevelStart(final Board theBoard, final TetrisTimer theTimer, 
                           final BoardPaintPanel theBoardPaintPanel, 
                           final KeyListenerControls theKeyListener) {
        super();
        myTetrisBoard = theBoard;
        myTimer = theTimer;
        myBoardPaintPanel = theBoardPaintPanel;
        myKeyListener = theKeyListener;
        setup();
        setPreferredSize(new Dimension(PREFERRED_WIDTH, 0));
    }

    /** Help the constructor setup the left panel. */
    private void setup() {                     
        final GridLayout grid = new GridLayout(0, 1); 
        setLayout(grid);
        
        //create the tetris title
        final JLabel tetris = new JLabel("TETRIS");
        tetris.setFont(TETRIS_TITLE_FONT);
        add(tetris);
        
        // create level label
        final JLabel level = new LevelCounter(myTetrisBoard, "Level: ", TETRIS_LABEL_FONT, 
                                              myTimer);
        add(level);
        
        // create score label
        final JLabel score = new ScoreCounter(myTetrisBoard, "Score: ", TETRIS_LABEL_FONT);
        add(score);
        
        // next level in counter
        final JLabel nextLevel = new NextLevelCountDown(myTetrisBoard, "Next lvl: ", 
                                                  TETRIS_LABEL_FONT);
        add(nextLevel);
                        
        // create lines cleared label
        final JLabel linesCleared = new TotalLinesCleared(myTetrisBoard, "Cleared: ", 
                                              TETRIS_LABEL_FONT);
        add(linesCleared);
                
        setFocusable(false);
        
        // set the key listeners to not be responsive at start
        myKeyListener.setKeysStatus(false);
        
        //add start / resume button
        myResume = panelButton("Resume");
        myResume.addActionListener(event -> {
            myKeyListener.setKeysStatus(true);
            myTimer.startTimer();
        });
        add(myResume);
        
        //add a pause button
        myPause = panelButton("Pause");
        myPause.addActionListener(event -> {
            myKeyListener.setKeysStatus(false);
            myTimer.pauseTimer();
        });
        add(myPause);
                
        //add a new game button
        myNewGame = panelButton("New Game");          
        myNewGame.addActionListener(event -> {
            gameInProgress();
            myTetrisBoard.newGame();
            myBoardPaintPanel.setGameOver(false);
            myKeyListener.setKeysStatus(true);
            myTimer.startTimer();
            myTimer.restartTimer();
            //reset the score panels etc...
            ((LevelCounter) level).resetLevel();
            ((ScoreCounter) score).resetScore();
            ((NextLevelCountDown) nextLevel).resetLevelCountDown();
            ((TotalLinesCleared) linesCleared).resetLinesCleared();
            
        });
        add(myNewGame);
                 
        //add an end game button
        myEndGame = panelButton("End Game");   
        myEndGame.addActionListener(event -> {
            gameEnded();
            myBoardPaintPanel.setGameOver(true);
            myBoardPaintPanel.repaint();
            myKeyListener.setKeysStatus(false);
            myTimer.restartTimer();
            myTimer.pauseTimer();
            firePropertyChange("endGame", false, true);
        });
        add(myEndGame);
        
      //set initial button selection state
        gameEnded();
    }

    /** 
     * Create a JButton for this panel.
     * 
     * @param theName name of the button
     * @return a JButton 
     */
    private JButton panelButton(final String theName) {
        final JButton aButton = new JButton(theName);
        aButton.setSelected(false);
        aButton.setFocusable(false);
        aButton.setFont(TETRIS_BUTTON_FONT);
        return aButton;
    }
    
    /**
     * Set buttons to game in progress state.
     */
    private void gameInProgress() {
        myResume.setEnabled(true);
        myPause.setEnabled(true);
        myNewGame.setEnabled(false);
        myEndGame.setEnabled(true);
    }
    
    /**
     * Set buttons to game ended state.
     */
    private void gameEnded() {
        myResume.setEnabled(false);
        myPause.setEnabled(false);
        myNewGame.setEnabled(true);
        myEndGame.setEnabled(false);
    }
    
    /** 
     * Get the value of the game enabled button. 
     * 
     * @return if the endGameButton is enabled
     */
    public boolean gameEndedButtonEnabled() {
        return myEndGame.isEnabled();
    }
       
    @Override
    public void update(final Observable theObservable, final Object theArg) { 
        
        if (theArg instanceof Boolean && ((Boolean) theArg).booleanValue()) {                
            gameEnded();
        }
    }
        
}

