/*
 * TCSS - 305: Assignment 5 - PowerPaint
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * This draws a rectangle shape.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class RectangleTool extends AbstractTool {
    
    /**
     * Draws a rectangle.
     * {@inheritDoc}
     */
    @Override
    public Shape getShape() {
        final Rectangle2D aRectangle = new Rectangle2D.Double();
        aRectangle.setFrameFromDiagonal(getStartPoint(), getEndPoint());
        return aRectangle;
    }

}
