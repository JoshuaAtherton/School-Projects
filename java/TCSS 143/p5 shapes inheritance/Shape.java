/*
Shape.java
TCSS 143 - Spring 2017
Instructor: David Schuessler
Project 5
*/

/**
This is an interface that has 2 abstract methods, and passes the
responsibility of implementing the compareTo method to the class that
implements Shape (you may note, normally Comparable is “implemented” by a
class. However, an interface cannot implement because interfaces can only
contain abstract methods. That said, an interface can only extend other
interfaces and the responsibility of actually “implementing” the abstract
method(s) of the super class interface is passed on to the sub-classes):

This Class builds
Uses driver Assignemt5.java

@author Josh Atherton  jatherton@uw.edu
@version 1: April 29, 2017
*/
public interface Shape extends Comparable<Shape> {
   /**
   Used to calculate the area of the shapes
   @return the area of the shape
   */
   public double calculateArea();

   /**
   This method copies the shape
   @return a copy of the shape
   */
   public Shape copyShape();

   /**
   Method to get the name of the shape 
   @return the shape name
   */
   public String getName();
}
