/*
 * TCSS - 305: Assignment 5 - PowerPaint
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This sets up the GUI.
 * 
 * @author Josh Atherton
 * @version 1
 */
public final class PowerPaintGUI implements PropertyChangeListener {
    /** Minimum thickness of a line. */
    private static final int LINE_MINIMUM = 0;
    
    /** Maximum thickness of a line. */
    private static final int LINE_MAXIMUM = 25;
    
    /** Default thickness of a line. */
    private static final int LINE_DEFAULT = 5;
    
    /** JSslider minimum tick spacing. */
    private static final int MINIMUM_TICK_SPACING = 1;
    
    /** JSslider maximum tick spacing. */
    private static final int MAXIMUM_TICK_SPACING = 5;
    
    /** The default line color. */
    private static final Color DEFAULT_COLOR = new Color(51, 0, 111);
    
    /** The default fill color. */
    private static final Color DEFAULT_FILL_COLOR = new Color(232, 211, 162);
    
    /** The line color. */
    private Color myDrawingColor; 
    
    /** The fill color. */
    private Color myFillColor;
    
    /** Value of fill color check box. */
    private boolean myFillCheckBox;    
    
    /** The branding for this program. */
    private final ImageIcon myLogo;
    
    /** Top level window for this application. */
    private final JFrame myFrame;
    
    /** Top level window for this application. */
    private final JMenuBar myMenuBar;
    
    /** A JSlider for the top menu. */
    private final JSlider myThicknessSlider;
    
    /** Hold the line thickness. */
    private int myStrokeThickness;   
    
    /** A drawing panel. */
    private final DrawingPanel myDrawingPanel;
    
    /** a JMenuItem that holds a clear button for the drawing GUI. */
    private final JMenuItem myClear;
    
    
    /** Constructor initializes the GUI. */
    public PowerPaintGUI() {
        myFrame = new JFrame("PowerPaint"); 
        myMenuBar = new JMenuBar();
        myLogo = new ImageIcon(this.getClass().getResource("/resources/W_Logo.png"));
        
        myThicknessSlider = new JSlider(JSlider.HORIZONTAL, LINE_MINIMUM, LINE_MAXIMUM, 
                                        LINE_DEFAULT); 
        
        constructorHelper();
        myDrawingPanel = new DrawingPanel(myDrawingColor, myFillColor, myStrokeThickness);
        myClear = new JMenuItem("Clear");
    }
    
    /**
     * This helps the constructor to initialize all of the fields.
     */
    private void constructorHelper() {
      //default starting drawing colors
        myDrawingColor = DEFAULT_COLOR;
        myFillColor = DEFAULT_FILL_COLOR;
        myStrokeThickness = LINE_DEFAULT;
        myFillCheckBox = false;
    }
    
    /**
     * This kick starts the GUI building the user interface.
     */
    public void start() {
        
        //add a property change listener to this class using changes from the drawing panel
        myDrawingPanel.addPropertyChangeListener(this);
        
        // the options part of the menu
        final JMenu option = new JMenu("Options");
        myMenuBar.add(option);
        addOptionMenuItems(option, myClear);
        setupMenuSlider();

        //create the tools for the gui
        final SelectableGUITools tools = new SelectableGUITools("Tools", myDrawingPanel);
        myMenuBar.add(tools.getMenu());
        
        //the help part of the menu
        final JMenu help = new JMenu("Help");
        myMenuBar.add(help);
        addHelpMenuItems(help);
        
        // put the tool bar on the south and add to the frame   
        myFrame.add(tools.getToolBar(), BorderLayout.SOUTH);
        
        // add menu bar to the frame
        myFrame.setJMenuBar(myMenuBar);
        
        //add the drawing panel to the frame position in the center
        myFrame.add(myDrawingPanel, BorderLayout.CENTER);
        
        myFrame.setIconImage(myLogo.getImage());
        
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setMinimumSize(myFrame.getSize());
        myFrame.setVisible(true);
    }
    
    /**
     * Create the Menu items for the options menu.
     * 
     * @param theMenu a menu to add menu items to
     *  @param theClear the clear button to add an action listener to
     */
    private void addOptionMenuItems(final JMenu theMenu, final JMenuItem theClear) {
        final JMenu thickness = new JMenu("Thickness");
        
        final JMenuItem drawColor = new JMenuItem("Draw Color...");
        drawColor.addActionListener(event -> { 
            myDrawingColor = pickAColor("Pick a Drawing color", myDrawingColor);
            myDrawingPanel.setDrawColor(myDrawingColor); 
        });
        
        final JMenuItem fillColor = new JMenuItem("Fill Color...");
        fillColor.addActionListener(event -> { 
            myFillColor = pickAColor("Pick a fill color", myFillColor);
            myDrawingPanel.setFillColor(myFillColor);        
        });
        
        final JCheckBoxMenuItem fill = new JCheckBoxMenuItem("Fill");
        fill.addActionListener(event -> { 
            myFillCheckBox = fill.isSelected();
            myDrawingPanel.setFillSelected(myFillCheckBox);
        });
        
        theClear.addActionListener(event -> {
            myDrawingPanel.clear();
            theClear.setEnabled(false);
        });
        theClear.setEnabled(false); 
        
        theMenu.add(thickness);
        thickness.add(myThicknessSlider);
        theMenu.addSeparator();
        theMenu.add(drawColor);
        theMenu.add(fillColor);
        theMenu.addSeparator();
        theMenu.add(fill);
        theMenu.addSeparator();
        theMenu.add(theClear);       
    }
    
    /**
     * Pop up a color chooser menu and reassign color to new color chosen.
     * 
     * @param theTitle for the color chooser
     * @param theColor the the current color
     * @return a new color 
     */
    private Color pickAColor(final String theTitle, final Color theColor) {
        Color newColor = theColor;
        final Color result = JColorChooser.showDialog(null, theTitle, theColor);
        if (result != null) {
            newColor = result;
        }
        return newColor;
    }
    
    /**
     * This sets up the slider bar that sets the stroke thickness of drawn shapes.   
     */
    private void setupMenuSlider() {

        myThicknessSlider.setMajorTickSpacing(MAXIMUM_TICK_SPACING);
        myThicknessSlider.setMinorTickSpacing(MINIMUM_TICK_SPACING);
        myThicknessSlider.setPaintTicks(true);
        myThicknessSlider.setPaintLabels(true);
        
        myThicknessSlider.addChangeListener(new ChangeListener() {
            /** Called in response to slider events in this window. */
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final int value = myThicknessSlider.getValue();
                myStrokeThickness = value;
                myDrawingPanel.setStrokeWidth(myStrokeThickness);                  
                }
            }
        );       
    }
     
    /**
     * This adds the help menu to a given menu.
     * 
     * @param theMenu the menu to add the created menu item to
     */
    private void addHelpMenuItems(final JMenu theMenu) {
        final JMenuItem about = new JMenuItem("About...");
        about.addActionListener(event -> helpPane());
        theMenu.add(about);  
    }
    
    /**
     *  This pops us a panel showing program information such as the author and date.
     */
    private void helpPane() {
        final String helpMessage = 
            "TCSS 305 PowerPaint\nAutumn 2017\nJosh Atherton";
        JOptionPane.showMessageDialog(null, 
                     helpMessage, "About", JOptionPane.INFORMATION_MESSAGE, 
                     myLogo);  
    } 
    
    /**
     * This changes the value of the clear button to enabled or disabled.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("Clear Enable".equals(theEvent.getPropertyName())) {
            //set the clear button to enabled if a shape has been drawn
            myClear.setEnabled(((Boolean) theEvent.getNewValue()).booleanValue());
             
        }
    }
}