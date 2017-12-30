/*
AbstractShape.java
TCSS 143 - Spring 2017
Instructor: David Schuessler
Project 5
*/

/**
This abstract class will implement the compareTo method passed on from the
Shape interface and will pass on the responsibility of implementing
calculateArea to the extending sub-classes (compareTo will use the
calculateArea method when comparing 2 Shape objects). Along with compareTo,
one more concrete method should be included. The following will be used by
the sub-classes’ toString method:

Uses driver Assignemt5.java

@author Josh Atherton  jatherton@uw.edu
@version 1: April 29, 2017
*/
public abstract class AbstractShape implements Shape {
   /** instance field to store the name of each object. a myName field */
   private String myName;

   /** constructor
   The constructor which sets this field should receive the name
   and a number to be concatenated to the name and then stored in the name
   field. Recall, when the super class has a parameterized constructor, the
   sub-classes will need to call it AND the sub- classes will need to also
   provide a constructor without parameters.

   @param theShape this is the shape name
   @param theID this is the number to be concatenated to the name
   */
   public AbstractShape(String theShape, int theID) {
      myName = theShape + theID;

   }
   /**
   Compares the shapes by area and sorts them from largest to smallest area
   Also sorts the shapes alphabetically by their name.

   @return the sorted shapes by area and name
   */
   public int compareTo(Shape theShape) {
      int sorted = 0;
       if (this.getClass().getName().compareTo(theShape.getClass().
                                                        getName()) == 0) {
           sorted = (int) (100 * (theShape.calculateArea() -
                                                   this.calculateArea()));
       } else {
           sorted = this.getClass().getName().compareTo(
                                           theShape.getClass().getName());
       }
       return sorted;
   }
   /**
   This method simply gets the name field data.

   @return the name field data to the caller
   */
   public String getName() {
      return myName;
   }

}
