/*
 * TCSS - 305: Assignment 5 - PowerPaint
 */
package tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.RectangularShape;

/**
 * This class holds all of the information necessary to draw an individual shape.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class IndividualShape {
    
    /** Field holds the shape. */
    private final Shape myShape;
    
    /** Field holds shape color. */
    private final Color myColor;
    
    /** Field holds shape fill color. */
    private final Color myFillColor;
    
    /** Field holds if the shape should be filled or not. */
    private boolean myIsFillChecked;     
    
    /** Field holds the stroke thickness. */
    private final BasicStroke myStrokeThickness;

    /**
     * This constructs an individual shape setting up all of the fields to their 
     * respective values.
     * 
     * @param theShape the shape of the object
     * @param theColor the shape color
     * @param theFillColor the shape fill color
     * @param theIsFillChecked if the shape should be filled
     * @param theStrokeThickness the stroke thickness of the shape
     */
    public IndividualShape(final Shape theShape, final Color theColor, 
                           final Color theFillColor, final boolean theIsFillChecked,
                           final BasicStroke theStrokeThickness) {
        myShape = theShape;
        myColor = theColor;
        myFillColor = theFillColor;
        myStrokeThickness = theStrokeThickness;
        //only allow shapes of RectangularShape to be filled if the check box is selected
        if (myShape instanceof RectangularShape) {
            myIsFillChecked = theIsFillChecked;
        } else {
            myIsFillChecked = false;
        }
                
    }
        
    /**
     * A getter method for the shape.
     * 
     * @return the shape
     */
    public Shape getShape() {
        return myShape;
    }
    
    /**
     * A getter method for the shape color.
     * 
     * @return the shape color
     */
    public Color getcolor() {
        return myColor;
    }
    
    /**
     * A getter method for the shape fill color.
     * 
     * @return the shape fill color
     */
    public Color getFillColor() {
        return myFillColor;
    }
    
    /**
     * A getter method for the shape fill color.
     * 
     * @return the shape fill color
     */
    public boolean isFillChecked() {
        return myIsFillChecked;
    }
    
    /**
     * A getter method for the stroke thickness.
     * 
     * @return the stroke thickness
     */
    public BasicStroke getStroke() {
        return myStrokeThickness;
    }
     
}