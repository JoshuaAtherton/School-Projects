/*
 * TCSS - 305: Assignment 5 - PowerPaint
 */

package tools;

import gui.DrawingPanel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

/**
 * This is a class to create the actions for the tools.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class ToolsAction extends AbstractAction {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = -5991652776246212608L;
    
    /** A field to hold the type of tool. */
    private final Tool myTool;
    
    /** A field to hold a Drawing panel that the tool draws on. */
    private final DrawingPanel myDrawingPanel;
       
   /**
    * This is class to hold the actions that the tools perform.
    * 
    * @param theName the string representation name of the tool
    * @param theIcon the icon to associate with the tool
    * @param theDrawingPanel the panel this tool interacts with
    * @param theTool the tool to set action to
    */
    public ToolsAction(final String theName, final ImageIcon theIcon, 
                       final DrawingPanel theDrawingPanel, final Tool theTool) {
        super();
        putValue(Action.NAME, theName);
        putValue(Action.LARGE_ICON_KEY, theIcon);
        putValue(Action.SMALL_ICON, theIcon);
        putValue(Action.SELECTED_KEY, true);
        myDrawingPanel = theDrawingPanel;
        myTool = theTool;
    }
     
    /**
    * Add the action to perform to the action. In this case set current tool.
    */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myDrawingPanel.setTool(myTool);
    }
}
