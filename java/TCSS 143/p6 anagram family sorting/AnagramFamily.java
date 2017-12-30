/*
AnagramFamily.java
TCSS 143 - Spring 2017
Instructor: David Schuessler
Project 6
*/
import java.util.*;
/**
This object stores a list of words, the amount of words in that list, a
getter method to get the length of the words list, and a toString method
to print out the object in a formatted way.
Anagrams are words that spell something backwards and forwards.

@author Josh Atherton  jatherton@uw.edu
@version 1: May 22, 2017
*/
class AnagramFamily {
   /** store list of anagrams */
   private List<Word> myWordsList;
   /** store the size of the anagrams list */
   private int myFamilySize;

   /**
   Constructor method taking in a List of word objects & stores that list
   as long as setting the family size field.

   @param theList
   */
   public AnagramFamily(List<Word> theList){
      myWordsList = theList;
      myFamilySize = theList.size();
   }
   /**
   Getter method that gets the size of the anagram Family.

   @return the size of the family
   */
   public int getFamilySize(){
      return myFamilySize;
   }
   /**
   This gets the words in the anagram family and prints them out with a
   space between each word one by one.

   @return a list of the normal words for the toString method
   */
   public String getNormalWordFam(){
      String normalWordFam = "";
      for (int i = 0; i < myWordsList.size(); i++) {
         if ( i == myWordsList.size() - 1 ){
            normalWordFam += myWordsList.get(i).getNormalWord();
         } else {
            normalWordFam += myWordsList.get(i).getNormalWord() + ", ";
         }
      }
      return normalWordFam;
   }
   /**
   The formatted output that prints the number of words in family, the
   canonical word, and the normal word versions.

   @return the formatted output when anagramFamilyCalled to print
   */
   public String toString(){
      String output =
      "\nNumber of Words in Anagram Family: " + myFamilySize +
      "\nCanonical form of Anagram: " +
                                  myWordsList.get(0).getCanonicalWord() +
      "\nMembers of the Family: " + getNormalWordFam() + "\n";

      return output;
   }

}
