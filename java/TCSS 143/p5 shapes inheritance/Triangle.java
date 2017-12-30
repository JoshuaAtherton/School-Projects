/*
Triangle.java
TCSS 143 - Spring 2017
Instructor: David Schuessler
Project 5
*/
import java.lang.Math;
/**
Be sure to use the invariant that throws an IllegalArgumentException when a
method argument is used to set any of the sides of the triangle to values
that are <= 0 AND if the longest side is >= to the sum of the remaining
sides. Supply an appropriate error message in the parameter list (make
sure to decrement myID field before throwing the exception).

This Class builds a triangle with the specified lengths for the three sides
and holds the area value for that triangle.
Uses driver Assignemt5.java

@author Josh Atherton  jatherton@uw.edu
@version 1: April 29, 2017
*/
public class Triangle extends AbstractShape {
   /** Stores the length of sideA of triangle */
   private double mySideA;
   /** Stores the length of sideB of triangle */
   private double mySideB;
   /** Stores the length of sideC of triangle */
   private double mySideC;
   /** private “static” int field shared by all Triangle objects */
   private static int myID = 0;

   /**
   A parameterless constructor that sets the sideA, sideB, and sideC for
   triangle to 1.0, 1.0 and 1.0 respectively.
   */
   public Triangle() { // calls this(1.0, 1.0, 1.0);
       this(1.0, 1.0, 1.0);
   }
   /**
   Constructor calls super with “Triangle” and myID incremented. Then
   sets the three sides of the triangle to the specified lengths with the
   given parameters checking if valid entries are made. Otherwise throws
   an error.

   @param theSideA the length of the first side: A
   @param theSideB the length of the second side: B
   @param theSideC the length of the third side: C
   */
   public Triangle(final double theSideA, final double theSideB,
                   final double theSideC) {
      super("Triangle", ++myID);
       if ( theSideA + theSideB > theSideC &&
            theSideB + theSideC > theSideA &&
            theSideA + theSideC > theSideB  )
       {
               mySideA = theSideA;
               mySideB = theSideB;
               mySideC = theSideC;
       } else if ( theSideA <= 0.0 || theSideB <= 0.0 || theSideC <= 0.0 )
       {
           myID--;
           throw new IllegalArgumentException(
                                      ": ERROR! Negative or 0 value(s)" +
                                      " can't be applied to a Triangle.");
       } else {
          myID--;
          throw new IllegalArgumentException(": ERROR! Not a Triangle." +
                                             " Longest side too long.");
      }
   }
   /**
   This sets the side A of the triangle to the new given length

   @param theSideA takes in a double to set SideA length
   */
   public void setSideA(final double theSideA) {
      if ( theSideA + mySideB > mySideC &&
           mySideB + mySideC > theSideA &&
           theSideA + mySideC > mySideB  )
      {
             mySideA = theSideA;
      } else if ( theSideA <= 0.0) {
          throw new IllegalArgumentException(
                                     ": ERROR! Negative or 0 value(s)" +
                                     " can't be applied to a Triangle.");
      } else {
         throw new IllegalArgumentException(": ERROR! Not a Triangle." +
                                            " Longest side too long.");
     }
   }
   /**
   This sets the side B of the triangle to the new given length

   @param theSideB take in a double to set SideB length
   */
   public void setSideB(final double theSideB) {
      if ( mySideA + theSideB > mySideC &&
           theSideB + mySideC > mySideA &&
           mySideA + mySideC > theSideB  )
      {
             mySideB = theSideB;
      } else if ( theSideB <= 0.0) {
          throw new IllegalArgumentException(
                                     ": ERROR! Negative or 0 value(s)" +
                                     " can't be applied to a Triangle.");
      } else {
         throw new IllegalArgumentException(": ERROR! Not a Triangle." +
                                            " Longest side too long.");
     }
   }
   /**
   This sets the side C of the triangle to the new given length

   @param theSideC takes in a double to set SideC length
   */
   public void setSideC(final double theSideC) {
      if ( mySideA + mySideB > theSideC &&
           mySideB + theSideC > mySideA &&
           mySideA + theSideC > mySideB  )
      {
             mySideC = theSideC;
      } else if ( theSideC <= 0.0) {
          throw new IllegalArgumentException(
                                     ": ERROR! Negative or 0 value(s)" +
                                     " can't be applied to a Triangle.");
      } else {
         throw new IllegalArgumentException(": ERROR! Not a Triangle." +
                                            " Longest side too long.");
     }
   }
   /**
   This calculates the area of the triangle and returns the result to the
   caller

   @return the calculated area of the triangle
   */
   public double calculateArea() {
      // retangle area given three sides: first) S = (a + b + c) / 2
      // second) √(s(s-a)(s-b)(s-c))
      double semiPerimeter =  ( mySideA + mySideB + mySideC ) / 2 ;
      double area = Math.sqrt(semiPerimeter * (semiPerimeter - mySideA) *
                                              (semiPerimeter - mySideB) *
                                              (semiPerimeter - mySideC) );
      return area;
   }
   /**
   Returns a reference to a new Triangle with the same field
   values as the implied parameter (defensive copy).

   @return a copy of the triangle shape
   */
   public final Shape copyShape() {
      Triangle newT = new Triangle();
      newT.mySideA = mySideA;
      newT.mySideB = mySideB;
      newT.mySideC = mySideC;
      return newT;

   }
   /**
   toString should only return a String that includes the name of the
   class, the sides, and the area, e.g. output.out.println(aTri); might
   produce: Triangle1 [SideA: 2.50, SideB: 3.00, SideC: 4.00] Area: 3.75

   @return a formated string when object is called on to print
   */
   public String toString() {
      String triangleString = super.getName() +
            " [SideA: " + String.format("%.2f", mySideA) +
            ", SideB: " + String.format("%.2f", mySideB) +
            ", SideC: " + String.format("%.2f", mySideC) +
            "] Area: " + String.format("%.2f", calculateArea());
      return triangleString;
   }

}
