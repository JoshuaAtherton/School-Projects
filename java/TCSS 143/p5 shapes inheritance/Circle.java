/*
Circle.java
TCSS 143 - Spring 2017
Instructor: David Schuessler
Project 5
*/
import java.lang.Math;
/**
Be sure to use the invariant that throws an IllegalArgumentException when a
method argument that is <= 0 is used to set the radius and supply an
appropriate error message in the parameter list (discussed in class & make
sure to decrement myID field before throwing the exception).

This Class builds a circle object to hold the radius of a given circle and
the area.
Uses driver Assignemt5.java

@author Josh Atherton  jatherton@uw.edu
@version 1: April 29, 2017
*/
public class Circle extends AbstractShape {
   /** Stores the radius of the circle */
   private double myRadius;
   /** private “static” int field shared by all Circle objects */
   private static int myID = 0;

   /**
   A parameterless constructor that sets the radius of the circle to 1.0.
   */
   public Circle() { // calls this(1.0);
       this(1.0);
   }
   /**
   Has to call super passing “Circle” and myID incremented.
   @param theRadius the radius of the circle
   */
   public Circle(final double theRadius) {
      super("Circle", ++myID);
      if (theRadius > 0.0) {
         myRadius = theRadius;
      } else {
         myID--;
         throw new IllegalArgumentException(
                              ": ERROR! Negative of 0 value" +
                              " can't be applied to a circle radius.");
      }
   }
   /**
   Method to set the radius of a circle object to the specified length

   @param setRadius set the radius of the circle
   */
   public void setRadius(final double theRadius) {
      if (theRadius > 0.0) {
         myRadius = theRadius;
      } else {
         throw new IllegalArgumentException(
                              ": ERROR! Negative of 0 value" +
                              " can't be applied to a circle radius.");
      }
   }
   /**
   Used to calculate the area of the given circle and return the value
   of the calculated area to the caller.

   @return the calculated area of the circle
   */
   public double calculateArea() {
      // area of circle: A = pi * radius^2
      double area = (double) Math.PI * (Math.pow(myRadius, 2.0));
      return area;
   }
   /**
   // Presented here as an example for the remaining concrete classes.
   This is a defensive copy that returns a reference to new Circle object

   @return a copy of the circle shape
   */
   public final Shape copyShape() {
      Circle newC = new Circle();
      newC.myRadius = myRadius;
      return newC;
   }
   /**
   toString should only return a String that includes the name of the
   class object, radius, and the area, e.g. output.out.println(aCircle);
   might produce: Circle5 [Radius: 4.40] Area: 60.82

   @return a formated string when printing object
   */
   public String toString() {

      String circleString = super.getName() +
                  " [Radius: " + String.format("%.2f", myRadius) +
                  "] Area: " + String.format("%.2f", calculateArea());
      return circleString;
   }

}
