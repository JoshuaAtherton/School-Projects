/*
 * TCSS - 305: Assignment 4 - SnapShop
 */

package gui;

import filters.EdgeDetectFilter;
import filters.EdgeHighlightFilter;
import filters.Filter;
import filters.FlipHorizontalFilter;
import filters.FlipVerticalFilter;
import filters.GrayscaleFilter;
import filters.SharpenFilter;
import filters.SoftenFilter;
import image.PixelImage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 * A program that create a GUI for a user to pull up images and do simple manipulations.
 * 
 * @author Josh Atherton
 * @version version 1
 */
public final class SnapShopGUI {
        
    /** Hold the number of columns for the left menu. */
    private static final int COL = 1;

    /** Hold the number of rows for the left menu. */
    private static final int ROW = 7;
    
    /** Top level window for this application. */
    private final JFrame myFrame;
    
    /** The open button. */
    private final JButton myOpen;
    
    /** The save button. */
    private final JButton mySave;

    /** The close button. */
    private final JButton myClose;
    
    /** Store the image being edited. */
    private PixelImage myImage;
    
    /** Store the previous place a file was stored. */         
    private File myPreviousSave;

    /** A list of buttons that are displayed in this GUI. */
    private final List<JButton> myLeftPanelButtons;
    
    /** Constructor initializes a JFrame. */
    public SnapShopGUI() {
        myFrame = new JFrame("TCSS 305 SnapShop");
        myOpen = new JButton("Open...");
        mySave = new JButton("Save As...");
        myClose = new JButton("Close As...");

        myLeftPanelButtons = new ArrayList<>();
        myPreviousSave = new File(".");

    }
     
    /** Displays the JFrame. */
    public void start() {
        
        //Set the behavior for when the exit button is clicked 
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
        //center container for the picture 
        final JLabel centerPanelPicture = new JLabel();
        centerPanelPicture.setHorizontalAlignment(SwingConstants.CENTER);
        
        //align center panel to be in the center
        myFrame.add(centerPanelPicture, BorderLayout.CENTER);
        
        /* The Bottom Panel container */
        final JPanel bottomPanel = new JPanel();
        
        //contain bottomPanel elements on bottom
        myFrame.add(bottomPanel, BorderLayout.SOUTH);
        
        final JFileChooser fileChooser = new JFileChooser(myPreviousSave); //"./sample_images"
        
        //add the buttons to the bottom panel
        bottomPanel.add(myOpen);
        bottomPanel.add(mySave);
        bottomPanel.add(myClose);
        
        //creating button listener for open
        myOpen.addActionListener(event -> myImage = openFile(
                                          fileChooser, myFrame, centerPanelPicture));

        //creating button listener for save button 
        mySave.addActionListener(event -> saveFile(fileChooser));
        
        //myClose button action listener
        myClose.addActionListener(event -> closeFile(myFrame, centerPanelPicture));

        /* The Left Panel container */
        final JPanel leftPanel = new JPanel();
        
        //left panel container alignment to the left
        myFrame.add(leftPanel, BorderLayout.WEST);
        
        //use GridLayout to align elements in container vertically that stretch to fit screen
        leftPanel.setLayout(new GridLayout(ROW, COL));
        
        //add the buttons to the left Panel
        addLeftPanelButtons(centerPanelPicture, leftPanel);
        
        //disable the buttons
        buttonSwitchHelper(false);
                
        //contain all elements in the smallest window possible
        myFrame.pack();
        
        //get size and set minimum size frame can be sized to
        myFrame.setMinimumSize(myFrame.getSize());
        
        //Center the frame in the user window
        myFrame.setLocationRelativeTo(null);
        
        //make my GUI frame visible
        myFrame.setVisible(true);
            
    }
    
    /**
     * Method to add the buttons to the left panel dynamically.
     * 
     * @param theCenterPicture the action listener of a button affects this JLabel
     * @param theLeftPanel the panel to add the buttons to
     */
    private void addLeftPanelButtons(final JLabel theCenterPicture, 
                                    final JPanel theLeftPanel) {
        //create buttons dynamically
        final List<Filter> leftPanelObjects = new ArrayList<>();
        leftPanelObjects.add(new EdgeDetectFilter());
        leftPanelObjects.add(new EdgeHighlightFilter());
        leftPanelObjects.add(new FlipHorizontalFilter());
        leftPanelObjects.add(new FlipVerticalFilter());
        leftPanelObjects.add(new GrayscaleFilter());
        leftPanelObjects.add(new SharpenFilter());
        leftPanelObjects.add(new SoftenFilter());
        
        for (final Filter filterItem : leftPanelObjects) {
            final JButton aButton = new JButton(filterItem.getDescription());
            myLeftPanelButtons.add(aButton);
                                                                        
            aButton.addActionListener(event -> {
                filterItem.filter(myImage); 
                theCenterPicture.setIcon(new ImageIcon(myImage));
            }); 
            
        }
        
        //add the buttons 
        for (final JButton aButton : myLeftPanelButtons) {
            theLeftPanel.add(aButton);
        }
        
    }
    

/**
    * Actions to be performed when the open button is clicked.
    * Including resizing the frame, loading and image and enabling all buttons.
    * 
    * @param theFileChooser the file chooser object to use for method
    * @param theFrame the frame that contains all elements
    * @param theCenterPicture the JLabel the picture is added to
    * @return an image to load into the GUI
    */
    private PixelImage openFile(final JFileChooser theFileChooser, final JFrame theFrame,
                               final JLabel theCenterPicture) {
        PixelImage imageToLoad = null;
        final int result = theFileChooser.showOpenDialog(null); 
        if (result == JFileChooser.APPROVE_OPTION) {
            //if selected an image file open the file and add to GUI
            myPreviousSave = theFileChooser.getSelectedFile();
            try {
                // to add image to center frame
                final File selectedFile = theFileChooser.getSelectedFile();
                imageToLoad = PixelImage.load(selectedFile);
                theCenterPicture.setIcon(new ImageIcon(imageToLoad));
                //enable all of the buttons
                buttonSwitchHelper(true);
                //pack() the GUI to smallest size
                theFrame.setMinimumSize(new Dimension(0, 0));
                theFrame.pack();
                theFrame.setMinimumSize(myFrame.getSize());
                myFrame.setLocationRelativeTo(null);
            
            // is not an image file that was selected show warning 
            } catch (final IOException notImage) {
                JOptionPane.showMessageDialog(theFrame, 
                                              "The selected file did not contain an image!", 
                                              "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return imageToLoad;
    }
    
    /**
     * Method to save an image.
     * 
     * @param theFileChooser a JFileChooser object to get a location to save image to
     */
    private void saveFile(final JFileChooser theFileChooser) {
        //save the image to a file
        final int userSelection = theFileChooser.showSaveDialog(null); //?
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                if (checkForOverwrite(theFileChooser)) {
                    myImage.save(theFileChooser.getSelectedFile());
                } else {
                    myImage.save(theFileChooser.getSelectedFile());
                }
            } catch (final IOException noSave) {
                JOptionPane.showMessageDialog(null, "Could not save file", "Error",
                                              JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Check if the file name typed in already exists and ask if the user wants to overwrite. 
     * 
     * @param theFileChooser a JFileChooser to check if the file exists already
     * @return if either true of false if the file already exists
     */
    private boolean checkForOverwrite(final JFileChooser theFileChooser) {
        boolean toSave = false;
        final File possibleSaveFile = theFileChooser.getSelectedFile();
        if (possibleSaveFile.exists()) {
            final int choice = JOptionPane.showConfirmDialog(theFileChooser, 
                            "This file already exists. Do you wish to overwrite the file?");
            if (choice == JOptionPane.YES_OPTION) {
                toSave = true;
            }
        }
        return toSave;
    }
        
    /**
     * Close an image and reset buttons to starting state.
     * Also resize the frame to smallest size possible.
     * 
     * @param theFrame the frame to make to the smallest size possible
     * @param theCenterPicture the image to remove from the frame
     */
    private void closeFile(final JFrame theFrame, final JLabel theCenterPicture) {
        theCenterPicture.setIcon(null);
        buttonSwitchHelper(false);
        theFrame.setMinimumSize(new Dimension(0, 0));
        theFrame.pack();
        theFrame.setMinimumSize(myFrame.getSize());
        myFrame.setLocationRelativeTo(null);
        
    }

    
    /**
     * Disable/Enable buttons helper.
     * 
     * @param theButtonsSwitch value to set the buttons display to
     */
    private void buttonSwitchHelper(final boolean theButtonsSwitch) {
        mySave.setEnabled(theButtonsSwitch);
        myClose.setEnabled(theButtonsSwitch);
        
        for (final JButton aButton : myLeftPanelButtons) {
            aButton.setEnabled(theButtonsSwitch);
        }     
    }
    

}