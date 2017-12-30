/*
Sizes.java
TCSS 143 - Spring 2017
Instructor: David Schuessler
Project 6
*/
import java.util.Comparator;
/**
This comparator sorts the anagram families by the size of the families
from largest to smallest family.

@author Josh Atherton  jatherton@uw.edu
@version 1: May 22, 2017
*/
public class Sizes implements Comparator<AnagramFamily> {
   /**
   Sort the anagram family objects by the size of the family from
   largest family to smallest family: descending order.

   @param theFamily1 the first family size to be compared
   @param theFamily2 the second family size to be compared
   @return the sorted order of the two parameters
   */
   public int compare(AnagramFamily theFamily1, AnagramFamily theFamily2){
      return (theFamily2.getFamilySize()) - (theFamily1.getFamilySize());
   }
}
