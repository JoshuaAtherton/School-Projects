/*
 * TCSS - 305: Assignment 5 - PowerPaint
 */
package tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * A tool to draw an ellipse shape.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class EllipseTool extends AbstractTool {
    
    /**
     * Draws an ellipse.
     * {@inheritDoc}
     */
    @Override
    public Shape getShape() {
        final Ellipse2D anEllipse = new Ellipse2D.Double();
        anEllipse.setFrameFromDiagonal(getStartPoint(), getEndPoint());
        return anEllipse;
    }

}