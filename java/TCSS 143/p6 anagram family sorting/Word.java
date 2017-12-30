/*
Word.java
TCSS 143 - Spring 2017
Instructor: David Schuessler
Project 6
*/
import java.util.*;
import java.util.Arrays;
/**
This is the Word class to to create word objects that store the normal
form of the word as well and the canonical version of the word. 

@author Josh Atherton  jatherton@uw.edu
@version 1: May 22, 2017
*/
class Word implements Comparable<Word>{
   /** store normal form of word */
   private String myNormalWord;
   /** store canonical form of word */
   private String myCanonicalWord;

   /**
   Constructor method takes a word and also creates a canonical
   version of the word which are both stored in the respective fiels above

   @param theWord the word passed in becomes myNormalWord to create object
   */
   public Word (String theWord) {
      myNormalWord = theWord;
      myCanonicalWord = convertWord(theWord);

   }
   /**
   This method is to take the original passed in word and convert it to
   a canonical version of that word and then return it.

   @param theWord a string passed in to be converted to a canonical word
   @return the canonical version of the string passed into the method
   */
   public String convertWord(String theWord) {
      char[] wordArray = theWord.toCharArray();
      Arrays.sort(wordArray);
      String canonicalWord = new String(wordArray);
      return canonicalWord;
   }

   /**
   This compare method sorts the word objects in alphabetical order.

   @param theCanonicalWord the word to be passed in to compare
   @return the sorted order of the word objects
   */
   public int compareTo(Word theCanonicalWord){

      return myCanonicalWord.compareToIgnoreCase(
                                    theCanonicalWord.getCanonicalWord());
   }
   /**
   Getter method to return the normal version of the word to the caller.

   @return a copy of myWord to the caller
   */
   public String getNormalWord() {
      return myNormalWord;
   }
   /**
   Getter method to return the canonicla version of the word to the caller

   @return a copy of myCanonicalWord to the caller
   */
   public String getCanonicalWord(){
      return myCanonicalWord;
   }
   /**
   The formatted string output when word objects are called to print

   @return the formatted string output
   */
   public String toString() {
      return myNormalWord + " : " + myCanonicalWord;
   }

}
