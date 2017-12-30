/*
 * TCSS - 305: Assignment 5 - PowerPaint
 */
package tools;

import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

/**
 * This class represents a Rounded Rectangle shape.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class RoundedRectangleTool extends AbstractTool {
    
    /** This specifies how round to make the corners when drawing rounded rectangles. */
    private static final double CORNER_ROUNDING = 10.0;
    
    /**
     * Draws a rectangle with rounded corners.
     * {@inheritDoc}
     */
    @Override
    public Shape getShape() {
        final Double zero = 0.0; 
        final RoundRectangle2D aRoundRectangle = 
                        new RoundRectangle2D.Double(zero, zero, zero, zero, 
                                                    CORNER_ROUNDING, CORNER_ROUNDING);
        aRoundRectangle.setFrameFromDiagonal(getStartPoint(), getEndPoint());
        return aRoundRectangle;
    }

}
