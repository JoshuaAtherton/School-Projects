/*
 * TCSS - 305: Assignment 5 - PowerPaint
 */
package tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;

/**
 * A tool to draw a free-form line like you would with a pencil by hand.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class PencilTool extends AbstractTool {
    
    /** A path that the pencil draws. */
    private Path2D myPencil;
    
    /**
     * The constructor to setup pencil field myPencil.
     */
    public PencilTool() {
        super();
        myPencil = (Path2D) new Path2D.Double();    //.clone();
    }

    /** 
     * Creating a new Path object for the new pencil to draw.
     * {@inheritDoc} 
     */
    @Override
    public void setStartPoint(final Point thePoint) { 
        super.setStartPoint(thePoint);
        myPencil = (Path2D) new Path2D.Double();  //.clone();
        myPencil.moveTo(getStartPoint().getX(), getStartPoint().getY());
    }
        
    /**
     * Draws a free-form pencil line.
     * {@inheritDoc}
     */
    @Override
    public Shape getShape() {      
        myPencil.lineTo(getEndPoint().getX(), getEndPoint().getY());
        return myPencil;
    }
}
