/*
Descending.java
TCSS 143 - Spring 2017
Instructor: David Schuessler
Project 6
*/
import java.util.Comparator;
/**
This comparator sorts word objects by the normalForm of the word in
reverse alphabetical order.

@author Josh Atherton  jatherton@uw.edu
@version 1: May 22, 2017
*/
public class Descending implements Comparator<Word> {
   /**
   This method compare when called on sorts the words in reverse
   alphabetical order.

   @param theNormalWord1 the first word to be compared
   @param theNormalWord2 the second word to be compared to the first word
   @return either a negative of positive number used to sort the words
   */
   public int compare(Word theNormalWord1, Word theNormalWord2) {
      return theNormalWord2.getNormalWord().compareToIgnoreCase(
                                          theNormalWord1.getNormalWord());
   }
}
