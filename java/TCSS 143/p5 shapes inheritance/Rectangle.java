/*
Rectangle.java
TCSS 143 - Spring 2017
Instructor: David Schuessler
Project 5
*/

/**
Be sure to use the invariant that throws an IllegalArgumentException when
method arguments that are <= 0 are used to set the length and width fields.
Supply an appropriate error message in the parameter list (make sure to
decrement myID field before throwing the exception).

This Class builds a rectangle object and holds the given length and width
of the rectangle along with the area for a rectangle of that size.
Uses driver Assignemt5.java

@author Josh Atherton  jatherton@uw.edu
@version 1: April 29, 2017
*/
public class Rectangle extends AbstractShape {
   /** Stores the length of the rectangle */
   private double myLength;
   /** Stores the width of the rectangle */
   private double myWidth;
   /** private “static” int field shared by all Rectangle objects */
   private static int myID = 0;

   /**
   A parameterless constructor that sets the width and length of
   rectangle to 1.0 and 1.0 respectively
   */
   public Rectangle() { // calls this(1.0, 1.0)
       this(1.0, 1.0);
   }
   /**
   This is the vonstructor to set up the new rectangle shape.
   Calls super with “Rectangle” and myID incremented gives shape a unique
   name. Setup the length and width of the rectangle.

   @param theLength the length of the rectangle
   @param theWidth the width of the rectangle
   */
   public Rectangle(final double theLength, final double theWidth) {
      super("Rectangle", ++myID);
      if (theLength > 0.0 && theWidth > 0.0) {
         myLength = theLength;
         myWidth = theWidth;
      } else {
         myID--;
         throw new IllegalArgumentException(": ERROR! Negative or 0" +
                            " value(s) can't be applied to a rectangle.");
      }
   }
   /**
   This method sets the length of the current rectangle to the specified
   ammount.

   @param theLength set the length of the rectangle
   */
   public void setLength(final double theLength) {
      if (theLength > 0.0) {
         myLength = theLength;
      } else {
         throw new IllegalArgumentException(": ERROR! Negative or 0" +
                            " value(s) can't be applied to a rectangle.");
      }
   }
   /**
   Method to set the width of the rectangle.

   @param theWidth set width of rectangle
   */
   public void setWidth(final double theWidth) {
      if (theWidth > 0.0) {
         myWidth = theWidth;
      } else {
         myID--;
         throw new IllegalArgumentException(": ERROR! Negative or 0" +
                            " value(s) can't be applied to a rectangle.");
      }
   }
   /**
   Calculate the area of the given rectangle.

   @return the area of the rectagle is returned.
   */
   public double calculateArea() {
      // retangle A = L * W , (a L or W can't be negative)
      double area = myLength * myWidth;
      return area;
   }
   /**
   Returns a reference to a new Rectangle with the same field
   values as the implied parameter (defensive copy).

   @return a copy of the shape
   */
   public final Shape copyShape() {
      Rectangle newR = new Rectangle();
      newR.myLength = myLength;
      newR.myWidth = myWidth;
      return newR;
   }
   /**
   toString should only return a String that includes the name of the
   class, length, width, and area, e.g. output.out.println(aRect); might
   produce: Rectangle12 [Length: 2.50, Width: 3.00] Area: 7.50

   @return a formated string when the object is called to print
   */
   public String toString() {
      String rectangleString = super.getName() +
         " [Length: " + String.format("%.2f", myLength) +
         ", Width: " + String.format("%.2f", myWidth) + "]" +
         " Area: " + String.format("%.2f", calculateArea());
      return rectangleString;
   }

}
