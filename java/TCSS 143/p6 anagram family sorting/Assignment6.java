/*
Assignment6.java
TCSS 143 - Spring 2017
Instructor: David Schuessler
Project 6
*/
import java.util.*;
import java.util.Scanner; // for the scanner
import java.io.*;   // for the input and output
import java.util.ListIterator;
/**
This is the driver class that reads an input file of text and creates
objects from those words in file.

@author Josh Atherton  jatherton@uw.edu
@version 1: May 22, 2017
*/
class Assignment6 {
   /**
   Driver method to run Assignment5 and uses shape objects to perform
   calculations.

   @param theArgs is used for processing the files
   */
   public static void main(String[] theArgs){
      //to time  the program run time
      final long startTime = System.currentTimeMillis();
      Scanner input = null;
      PrintStream output = null;
      String infileName = "words.txt";
      String outfileName = "out6.txt";
      boolean filesOkay = false;

      try {
         input = new Scanner(new File(infileName));
         output = new PrintStream(new File(outfileName));
         filesOkay = true;
      } catch (FileNotFoundException e) {
         System.out.print("File not found - " + e);
      }
      if (filesOkay) { // now process file
          // create a linked list to hold word objects
          List<Word> wordsList = new LinkedList<Word>();
          // read input file and put into a word list
          readInfile(input, wordsList);
          // sort the word collection by canonical form
          Collections.sort(wordsList);
          // create a linked list to hold AnagramFamily objects
          List<AnagramFamily> anagramFamilyList = new
                                              LinkedList<AnagramFamily>();
          // iterate through wordsList and create AnagramFamily list
          createAnagramList(wordsList, anagramFamilyList);
          //sort the anagramFamilyList by family size
          Collections.sort(anagramFamilyList, new Sizes());
          //print the specified families to the outFile
          printOut(output, anagramFamilyList);

          input.close();
          output.close();

          final long endTime = System.currentTimeMillis();
          System.out.println("Program execution time in seconds: " +
                                       (endTime - startTime) / 1000.00);
      } // close if files okay
   } // close main
   /**
   This method takes in a sorted list of type word and fills a list of
   type anagramFamily with new anagram families.

   @param theWordsList the sorted Word list to be iterated through
   @param theAnagramFamilyList list to add the newly created families to
   */
   public static void createAnagramList( List<Word> theWordsList,
                               List<AnagramFamily> theAnagramFamilyList){
      ListIterator<Word> itr = theWordsList.listIterator();
      List<Word> oneAnagramFamily = new LinkedList<Word>();
      boolean haveUniqueCanonicalWord = false;
      Word wordSearchToken = null;
      while ( itr.hasNext() ) {
         if(!haveUniqueCanonicalWord) {
            // get first word to search for
            wordSearchToken = itr.next();
            // add the first word to the list
            oneAnagramFamily.add(wordSearchToken);
            // now that I have a unique new word search for matches
            haveUniqueCanonicalWord = true;
         } else if ((itr.next().getCanonicalWord()).equals(
                                    wordSearchToken.getCanonicalWord()) ){
            // so that you do not skip a line
            itr.previous();
            // if same canonical value get the vale of the word
            Word moreWordsToAdd = itr.next();
            // add the word to the word family list
            oneAnagramFamily.add(moreWordsToAdd);
         } else {
            // so that you do not skip a line
            itr.previous();
            /* sort oneAnagramFamily list by descending form using the
               descending comparator */
            Collections.sort(oneAnagramFamily, new Descending());
            // create a new anagram fam object from the current list
            theAnagramFamilyList.add(new AnagramFamily(oneAnagramFamily));
            /* clear the current anagram fam list so it's ready for the
               next family */
            oneAnagramFamily = new LinkedList<Word>();
            /* So that next time we will set a new word to start
               searching for */
            haveUniqueCanonicalWord = false;
         }
         /* to ensure a single family value at the end of the list is
            added if present */
         if (haveUniqueCanonicalWord && !itr.hasNext() ) {
            oneAnagramFamily = new LinkedList<Word>();
            oneAnagramFamily.add(wordSearchToken);
            theAnagramFamilyList.add(new AnagramFamily(oneAnagramFamily));
         }

      } // close while loop
   } // close createAnagramList method
   /**
   This is used to read an input file of single words. From that file
   create a list of Word objects.

   @param theInfile this is an already created .txt file full of words
   @param theList this is the list that the Word objects are stored in
   */
   public static void readInfile(Scanner theInfile,
                                       List<Word> theList) {
      while (theInfile.hasNext()) {
         theList.add(new Word(theInfile.next()));
      }
   }
   /**
   This is used to print out the required anagram families
   to an output file named out6.txt. This will

   @param thePrintout the Printstream the file is to be outputed to
   @param theList the list to be printed out
   */
   public static void printOut (PrintStream thePrintout,
											           List<AnagramFamily> theList) {
      thePrintout.println("The top five families: ");
      for (int i = 0; i < 5; i++) { // to print top 5 families
         thePrintout.println(theList.get(i));
      }

      thePrintout.println("All families of size 8: ");
      /* switched this method to iterator below to increase speed slightly
      for (AnagramFamily item : theList){ // print all families sized 8
         if (item.getFamilySize() == 8) {
            thePrintout.println(item);
         }
      }
      */
      Iterator<AnagramFamily> itr = theList.iterator();
      while (itr.hasNext()) {
         AnagramFamily currentItem = itr.next();
         if (currentItem.getFamilySize() == 8) {
            thePrintout.println(currentItem);
         }
      }


      thePrintout.println("The last family: ");
      thePrintout.println(theList.get(theList.size() - 1)); // the last 1
	}

} // close Assignment5
