/*
 * TCSS - 305: Assignment 5 - PowerPaint
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import tools.EllipseTool;
import tools.LineTool;
import tools.PencilTool;
import tools.RectangleTool;
import tools.RoundedRectangleTool;
import tools.ToolsAction;

/**
 * Create the tools to be displayed on the GUI.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class SelectableGUITools {
    
    /** A field to hold the menu. */
    private final JMenu myMenu;
    
    /** A field to hold the tools added to the tool bar. A drag-able tool bar. */
    private final JToolBar myToolBar;
    
    /** A field to hold a DrawingPanel item that will be the surface the tools draw on. */
    private final DrawingPanel myDrawingPanel;
    
    /**
     * This constructs a Tool menu and a JToolbar that are linked together.
     * 
     * @param theMenu the string name for the created JMenu
     * @param theDrawingPanel a JPanel component that will be drawn on
     */
    public SelectableGUITools(final String theMenu, final DrawingPanel theDrawingPanel) {
        myMenu = new JMenu(theMenu);
        //myToolBar = theToolBar;
        myDrawingPanel = theDrawingPanel;
        myToolBar = new JToolBar();
        createTools();
        
    }
    
    /**
     * Create the tools to be included in the tool menu and JToolbar.
     */
    private void createTools() {
      //create tool objects and add to an arrayList
        final List<ToolsAction> toolbarTools = new ArrayList<>();
        toolbarTools.add(new ToolsAction("Line",
                         new ImageIcon(this.getClass().getResource("/resources/line.gif")),
                         myDrawingPanel, new LineTool()));
        toolbarTools.add(new ToolsAction("Pencil",
                         new ImageIcon(this.getClass().getResource("/resources/pencil.gif")),
                         myDrawingPanel, new PencilTool()));
        toolbarTools.add(new ToolsAction("Rectangle",
                         new ImageIcon(this.getClass().getResource(
                                                               "/resources/rectangle.gif")),
                         myDrawingPanel, new RectangleTool()));
        toolbarTools.add(new ToolsAction("Rounded Rectangle",
                         new ImageIcon(this.getClass().getResource(
                                                       "/resources/roundrectangle.gif")),
                         myDrawingPanel, new RoundedRectangleTool()));
        toolbarTools.add(new ToolsAction("Ellipse",
                         new ImageIcon(this.getClass().getResource("/resources/ellipse.gif")),
                         myDrawingPanel, new EllipseTool()));
        
        addTools(toolbarTools);
    }
    
    /**
     * This adds actions to the tools added and links them into mutually exclusive button 
     * groups.
     * 
     * @param theToolbarTools the list of tools to be added
     */
    private void addTools(final List<ToolsAction> theToolbarTools) {
     // add the tools to both tool bar menus
        final ButtonGroup toolbarMenuRadioButtonsGroup = new ButtonGroup();
        final ButtonGroup toolBarButtonsGroup = new ButtonGroup(); 
        for (final ToolsAction addToolAction : theToolbarTools) {
            final JToggleButton aButton = new JToggleButton(addToolAction);
            final JRadioButtonMenuItem aRadioButton = 
                            new JRadioButtonMenuItem(addToolAction);

            //add to the tool bar on bottom and tool menu on top
            //add to button group
            toolBarButtonsGroup.add(aButton);
            myToolBar.add(aButton);
            //add to button group
            toolbarMenuRadioButtonsGroup.add(aRadioButton);
            myMenu.add(aRadioButton);
        }
    }
    
    /**
     * This is a getter for the JMenu item created in this class.
     * 
     * @return the JMenu item, myMenu, for this class
     */
    public JMenu getMenu() {
        return myMenu;
    }
 
    /**
     * A getter method to get the toolbar for this class.
     * 
     * @return the JToolBar item, myToolBar, for this class 
     */
    public JToolBar getToolBar() {
        return myToolBar;
    }
}
