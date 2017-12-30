/*
 * TCSS - 305: Assignment 5 - PowerPaint
 */
package tools;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * A tool to draw a line shape.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class LineTool extends AbstractTool {
    
    /**
     * Draws a strait line.
     * {@inheritDoc}
     */
    @Override
    public Shape getShape() {
        
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }  
}
