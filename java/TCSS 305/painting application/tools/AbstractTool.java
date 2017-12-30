/*
 * TCSS - 305: Assignment 5 - PowerPaint
 */
package tools;

import java.awt.Point;

/**
 * This is an abstract class that implements the tool interface.
 * 
 * @author Josh Atherton
 * @version 1
 */
public abstract class AbstractTool implements Tool {
    
    /** A point outside the drawing area. */
    public static final Point NO_POINT = new Point(-50, -50);

    /** The initial anchor point for the shape drawn by this tool. */
    private Point myStartPoint;  // mutable state
    
    /** The end point for the shape drawn by this tool. */
    private Point myEndPoint;    // mutable state

    /** Constructs a paint tool. */
    protected AbstractTool() {
        myStartPoint = NO_POINT;
        myEndPoint = NO_POINT;
    }

    /** {@inheritDoc} */
    @Override
    public void setStartPoint(final Point thePoint) {      
        myStartPoint = (Point) thePoint.clone();
        myEndPoint = (Point) thePoint.clone();
    }

    /**
     * This is a getter method to get the start point.
     * 
     * @return the start point for this PaintTool.
     */
    protected Point getStartPoint() {
        return myStartPoint;
    }
    
    /** {@inheritDoc} */
    @Override
    public void setEndPoint(final Point thePoint) {      
        myEndPoint = (Point) thePoint.clone();
    }

    /**
     * This is a getter method to get the endPoint.
     * 
     * @return the end point for this PaintTool.
     */
    protected Point getEndPoint() {
        return myEndPoint;
    }

}
