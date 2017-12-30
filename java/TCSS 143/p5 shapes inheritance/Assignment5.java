/*
Assignment5.java
TCSS 143 - Spring 2017
Instructor: David Schuessler
Project 5
*/
import java.util.*;
import java.util.Scanner; // for the scanner
import java.io.*;   // for the input and output
import java.lang.String; // to split string
/**
This Class builds
Uses driver Assignemt5.java to .

@author Josh Atherton  jatherton@uw.edu
@version 1: April 29, 2017
*/

public class Assignment5 {
   /**
   Driver method to run Assignment5 and uses shape objects to perform
   calculations.

   @param theArgs is used for processing the files
   */
   public static void main(String[] theArgs) {
      Scanner input = null;
      PrintStream output = null;
      String infileName = "in6.txt";
      String outfileName = "out5.txt";
      boolean filesOkay = false;

      try {
         input = new Scanner(new File(infileName));
         output = new PrintStream(new File(outfileName));
         filesOkay = true;
      } catch (FileNotFoundException e) {
         System.out.print("File not found - " + e);
      }
      if (filesOkay) { // now process file to get computations
          // create linked list
          List<Shape> myShapeList = new LinkedList<Shape>();
          List<Shape> myListCopy = new ArrayList<Shape>();
          myListCopy = readInfile(input, myShapeList);

          output.println("Original List[unsorted]:");
          outFile(output, myShapeList);
          output.println();

          output.println("Copied List[sorted]:");
          Collections.sort(myListCopy);
          outFile(output, myListCopy);
          output.println();

          output.println("Original List[unsorted]:");
          outFile(output, myShapeList);
          output.println();


          input.close();
          output.close();
      } // close if files okay
   } //close main

   /**
   Method to process the input file and split each line into a Linkedlist.
   to create shape objects then return the new list to an ArrayList.

   @param theInfile the file to read the input for the shapes from
   @param theList the list passed in to contain the created object shapes
   @return an ArrayList containing the created shape objects
   */
    public static List<Shape> readInfile(Scanner theInfile,
                                         List<Shape> theList) {
        List<Shape> copyList = new ArrayList<Shape>();

        theInfile.useDelimiter(" ");
        while ( theInfile.hasNextLine() ) {
            int totalWords= 0;
            double firstNum = 0.0;
            double secondNum = 0.0;
            double thirdNum = 0.0;
            boolean isANumber1 = false;
            boolean isANumber2 = false;
            boolean isANumber3 = false;
            String line = theInfile.nextLine();
            Scanner lineScan = new Scanner(line);
            String[] wordCount = line.split(" ");
            totalWords = wordCount.length;
            while (lineScan.hasNextDouble() ) {
                for (int itemCount = 1; itemCount <= 3; itemCount++) {
                    if ( lineScan.hasNextDouble() && itemCount == 1 ) {
                        firstNum = lineScan.nextDouble();
                        isANumber1 = true;
                    } else if ( lineScan.hasNextDouble() &&
                                                        itemCount == 2 ) {
                        secondNum = lineScan.nextDouble();
                        isANumber2 = true;
                    } else if ( lineScan.hasNextDouble() &&
                                                        itemCount == 3 ) {
                        thirdNum = lineScan.nextDouble();
                        isANumber3 = true;
                    }
                } // close for loop to get three number values
                // create the three shape types into a LinkedList
                //!line.trim().equals("") check if line has no input on it
                try {
                    if ( totalWords == 1 && !line.trim().equals("") &&
                                                             isANumber1) {
                        // Create Circle shape
                        theList.add(new Circle(firstNum));
                    } else if ( totalWords == 2 && isANumber2) {
                        // Create Rectangle shape
                        theList.add(new Rectangle(firstNum, secondNum));
                    } else if ( totalWords == 3 && isANumber2
                                                && isANumber3) {
                        // create Rectangle shape
                        theList.add(new Triangle(
                                        firstNum, secondNum, thirdNum));
                    } else {
                        // not fully valid line of input so do nothing
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e);
                }
            } // close lineScan while
        } // close theInfile while

        //add items to shape list
        for ( Shape each : theList ) {
            Shape s = each.copyShape();
            copyList.add(s);
        }
        return copyList;
    } // close method readInfile

   /**
   This method take in two inputs: a file to be written to, and a list
   containing the values to process. The method uses a for each loop to
   write the values of the list to the outfile.

   @param theOutput the file that the results of the method are written to
   @param theList the list passed in to be processed in the method
   */
   public static void outFile(PrintStream theOutput, List<Shape> theList) {
       for (Shape element : theList) {
           theOutput.println(element);
       }
   }

} // close Assignment5
