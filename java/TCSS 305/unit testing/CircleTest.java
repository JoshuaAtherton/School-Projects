/*
 * TCSS 305
 * Assignment 1 - Testing
 */

package testing;
import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;

/**
 * This program tests the Circle class to check that all methods are working properly 
 * without errors.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class CircleTest extends Circle {
    
    /** A tolerance used when comparing double values for equality. */
    private static final double TOLERANCE = .000001;
    
    /** A null point for testing null center point being passed into constructor. */ 
    private static final Point NULL_POINT = null;
    
    /** A null color for testing null color being passed into constructor. */ 
    private static final Color NULL_COLOR = null;
    
    /** A default circle to use in tests for a parameterless circle. */ 
    private Circle myCircle;
   
    /** A second circle to use for creating a circle with parameters. */   
    private Circle myTestCircle;
    
    /**A field to hold radius of circle. */   
    private Double myTestRadius;
    
    /**A field to hold the center point of the circle. */   
    private Point myTestPoint;
    
    /**A field to hold the color of the circle. */   
    private Color myTestColor;
    
    /** 
     * A method to initialize the test fixture before each test.
     */
    @Before
    public void setUp() { 
        myCircle = new Circle();   // create a default circle
        // create a circle with a parameters
        myTestRadius = 2.0; 
        myTestPoint = new Point(2, 4);
        myTestColor = new Color(1, 2, 3);
        myTestCircle = new Circle(myTestRadius, myTestPoint, myTestColor);
    }
     
    /**
     * Test method for the constructor with a value of 0 for radius.
     * @throws java.lang.Exception If the radius in not greater than 0. 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadRadius() {
        new Circle(-1.0, myTestPoint, myTestColor);  
    }
    /**
     * Test method for the default null center passed in.
     * @throws 
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorNullPoint() {
        new Circle(myTestRadius, NULL_POINT, myTestColor); 
    }
    /**
     * Test method for the constructor null color passed in.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorNullColor() {
        new Circle(myTestRadius, myTestPoint, NULL_COLOR); 
    }

    /**
     * Test to make sure you can't set radius to 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetRadiusToZero() {
        myCircle.setRadius(0.0);
    }  
    /**
     * Test to make sure you can't set radius to a negative number.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSetRadiusToNegative() {
        myCircle.setRadius(-1.0);
    }  
    /**
     * Test to make sure you can reset the radius.
     */
    @Test 
    public void testSetRadius() {
        myCircle.setRadius(5.0);
        assertEquals(5.0, myCircle.getRadius(), TOLERANCE);
    }

    /**
     * Test method to see if resetting center method works. 
     */
    @Test 
    public void testSetCenter() {
        myCircle.setCenter(myTestPoint);
        assertEquals(myTestPoint, myCircle.getCenter());
    }
    /**
     * Test to see if method throws error when null value passed in. 
     */
    @Test (expected = NullPointerException.class)
    public void testSetCenterToNull() {  
        myTestCircle.setCenter(NULL_POINT);
    }

    /**
     * Test method to see if resetting color method works.
     */
    @Test
    public void testSetColor() {
        myCircle.setColor(myTestColor);
        assertEquals(myTestColor, myCircle.getColor());
    }
    /**
     * Test method for see if it throws error when passing in a null value.
     */
    @Test (expected = NullPointerException.class)
    public void testSetColorToNull() {
        myCircle.setColor(NULL_COLOR);
    }

    /**
     * Test if the getRadius method for circle retrieves the right result.
     */
    @Test
    public void testGetRadius() {
        assertEquals(1.0, myCircle.getRadius(), TOLERANCE);
    }

    /**
     * Test if the getCenter method for circle retrieves the right result.
     */
    @Test
    public void testGetCenter() { 
        assertEquals(myTestPoint, myTestCircle.getCenter());
    }

    /**
     * Test if the getColor method for circle retrieves the right result. 
     */
    @Test
    public void testGetColor() {
        assertEquals(myTestColor, myTestCircle.getColor());
    }

    /**
     * Test to confirm that the diameter calculation of circle objects is correct.  
     */
    @Test
    public void testCalculateDiameter() {
        myCircle.calculateDiameter();
        assertEquals(2.0, myCircle.calculateDiameter(), TOLERANCE);
    }

    /**
     * Test to confirm that the circumference calculation of circle objects is correct.
     */
    @Test
    public void testCalculateCircumference() {
        double circumference = myCircle.calculateCircumference();
        circumference = Math.floor(circumference * 100) / 100;
        assertEquals(6.28, circumference, TOLERANCE);
    }

    /**
     * Test to confirm that the area calculation of circle objects is correct. 
     */
    @Test
    public void testCalculateArea() {
        double area = myCircle.calculateArea();
        area = Math.floor(area * 100) / 100;
        assertEquals(3.14, area, TOLERANCE);
    }

    /**
     * Test to make sure the toString method is printing the correct result and in the 
     * correct format. 
     */
    @Test
    public void testToString() {
        assertEquals("Circle [radius=1.00, center=java.awt.Point[x=0,y=0], "
                        + "color=java.awt.Color[r=0,g=0,b=0]]", myCircle.toString());
        
    }

}
