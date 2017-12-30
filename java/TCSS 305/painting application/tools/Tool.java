/*
 * TCSS - 305: Assignment 5 - PowerPaint
 */
package tools;

import java.awt.Point;
import java.awt.Shape;

/**
 * This is an interface for tools. 
 * All tools must implement getShape, setStartPoint & setEndPoint.
 * 
 * @author Josh atherton
 * @version 1
 */
public interface Tool {
    
    /**
     * Returns the Shape that this tools draws.
     * 
     * @return the Shape to draw
     */
    Shape getShape();

    /**
     * Sets the initial point for the Shape drawn by this tool.
     * 
     * @param thePoint the start point to set
     */
    void setStartPoint(Point thePoint);
    
    /**
     * Sets the end point for the Shape drawn by this tool.
     * 
     * @param thePoint the end point to set
     */
    void setEndPoint(Point thePoint);
    
}
