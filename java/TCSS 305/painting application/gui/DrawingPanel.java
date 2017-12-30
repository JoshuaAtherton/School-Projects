/*
 * TCSS - 305: Assignment 5 - PowerPaint
 */
package gui;

import java.awt.BasicStroke;
import java.awt.Color;     
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import tools.IndividualShape;
import tools.LineTool;
import tools.PencilTool;
import tools.Tool;


/**
 * This class holds a panel to be drawn on.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class DrawingPanel extends JPanel {

    /** A generated Serialization ID. */    
    private static final long serialVersionUID = 3308657385658777705L;

    /** The preferred width of the panel. */
    private static final int WIDTH = 200;      
    
    /** The preferred height of the panel. */
    private static final int HEIGHT = 200;   

    /** Hold the drawing color. */
    private Color myDrawColor;
    
    /** Hold the drawing fill color. */
    private Color myFillColor;
    
    /** Hold the stroke width. */
    private BasicStroke myStrokeWidth;
    
    /** Designate if the fill check box is selected. */
    private boolean myFillSelected;
    
    /** The PaintTool currently in use. */
    private Tool myCurrentTool;
    
    /** A collection of previously drawn shapes. */
    private final List<IndividualShape> myPreviousShapes;
    
    /**
     * Constructs a frame to hold the drawing panel.
     * 
     * @param theDrawColor the color to draw with
     * @param theFillColor the color to fill shapes with
     * @param theStrokeWidth the width of drawing paths
     */
    public DrawingPanel(final Color theDrawColor, final Color theFillColor, 
                        final int theStrokeWidth) { 
        super();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        setupMouseListeners();

        myDrawColor = theDrawColor;
        myFillColor = theFillColor;
        myStrokeWidth = new BasicStroke(theStrokeWidth, BasicStroke.CAP_ROUND, 
                                        BasicStroke.JOIN_ROUND); 
        myFillSelected = false;
        myPreviousShapes = new ArrayList<>();      
        myCurrentTool = new LineTool();  
    }
    
    /**
     * This is a helper method for the constructor to create mouse listeners for this class.
     */
    private void setupMouseListeners() {
        final MyMouseListener mouseListeners = new MyMouseListener();
        addMouseListener(mouseListeners);
        addMouseMotionListener(mouseListeners);
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
        
        //paint previous shapes
        for (final IndividualShape oneShape : myPreviousShapes) {
            g2D.setStroke(oneShape.getStroke());
            // if filled paint fill, else paint outline
            if (oneShape.isFillChecked()) { 
                g2D.setPaint(oneShape.getFillColor());
                g2D.fill(oneShape.getShape());  
                g2D.setPaint(oneShape.getcolor());
                g2D.draw(oneShape.getShape());
            } else {
                g2D.setPaint(oneShape.getcolor());
                g2D.draw(oneShape.getShape());

            }
        }
        
        //paint the currently drawn shape
        g2D.setPaint(myDrawColor); 
        g2D.setStroke(myStrokeWidth);
        //if line width is 0 do not paint the shape
        if (myStrokeWidth.getLineWidth() != 0) {
            if (myFillSelected && !(myCurrentTool instanceof PencilTool)) { 
                g2D.setPaint(myFillColor);
                g2D.fill(myCurrentTool.getShape());  
                g2D.setPaint(myDrawColor);
                g2D.draw(myCurrentTool.getShape());   
            } else {
                g2D.draw(myCurrentTool.getShape()); 
            }
        }
        //check if we can clear. Through firing a property change.
        canClear();                                 
    }
    
    /**
     * This sets the current drawing tool to use for drawing.
     * 
     * @param theTool a tool to draw with
     */
    public void setTool(final Tool theTool) {
        myCurrentTool = theTool;
    }
    
    /**
     * This sets the current drawing color.
     * 
     * @param theDrawingColor the color to draw with
     */
    public void setDrawColor(final Color theDrawingColor) {
        myDrawColor = theDrawingColor;
        
    }
    
    /**
     * This sets the current color to fill fillable shapes with.
     * 
     * @param theFillColor the color to fill the shape with
     */
    public void setFillColor(final Color theFillColor) {
        myFillColor = theFillColor;
        
    }
    
    /**
     * This sets the current stroke width of the drawing tool.
     * 
     * @param theStrokeThickness the thickness of the stroke
     */
    public void setStrokeWidth(final int theStrokeThickness) {
        myStrokeWidth = new BasicStroke(theStrokeThickness);  //do this better
        
    }
    
    /**
     * This designates weather the shape should be filled or not.
     * 
     * @param theFillSelected a boolean value representing weather to fill the shape or not
     */
    public void setFillSelected(final boolean theFillSelected) {
        myFillSelected = theFillSelected;
    }
    
    /**
     * This method will enable a clear button based on the state of the array list of shapes.
     * Fires a propertyChange to the listeners.
     */
    public void canClear() {
        firePropertyChange("Clear Enable", false, !myPreviousShapes.isEmpty());
    }
    
    /**
     * This clears all of the shapes from the drawing panel.
     */
    public void clear() {
        myPreviousShapes.clear();
        final Point noPoint = new Point(-50, -50);
        myCurrentTool.setStartPoint(noPoint);
        myCurrentTool.setEndPoint(noPoint);
        repaint();
    }
    
    /**
     * This class implements several elements of the mouseListener to read user interactions.
     */
    class MyMouseListener extends MouseAdapter {
  
        @Override
        public void mouseEntered(final MouseEvent theEvent) {
            setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));     
        }
          
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myCurrentTool.setStartPoint(theEvent.getPoint());
            repaint(); 
        }
        
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            myCurrentTool.setEndPoint(theEvent.getPoint());
            repaint();
        }
          
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            if (myStrokeWidth.getLineWidth() != 0) {
                final IndividualShape oneShape = new IndividualShape(
                                                        (Shape) myCurrentTool.getShape(),
                                                        myDrawColor, myFillColor, 
                                                        myFillSelected, 
                                                        myStrokeWidth);  
                myPreviousShapes.add(oneShape);
            }
            repaint();
        }           
    }      
}



