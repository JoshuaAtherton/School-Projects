/*
 Assign_2.java

 TCSS 143 - Spring 2017
 Instructor - David Schuessler
 Programming assigment 2
 */
import java.util.*;
import java.io.*;
/**
 This program reads an input txt file and splits the file into
 four 2D lists: A, B, C, D. Then adds and subtracts A & B and
 multiplies C & D. Finally the program writes the results to a txt file.

 @author Josh Atherton    joshua9@uw.edu
 @version 1
 */
public class Assign_2 {
   
    public static void main(String[] theArgs) {
        Scanner input = null; // create a scanner
        PrintStream output = null; // create a PrintStream
        String inFileName = "in2a.txt"; //input file name and type
        String outFileName = "out.txt"; //outfile name and type
        boolean filesOk = false; // Indicates if the files are accessible.
        // try to open input and output files
        try {
            input = new Scanner(new File(inFileName));
            output = new PrintStream(new File(outFileName));
            filesOk = true;
        } catch (FileNotFoundException e) {
            System.out.println("Can't open file - " + e);
        }
        if (filesOk) {
            //read arrays from file and assign to variables
            int[][] arrayA = inputHelper (input);
            int[][] arrayB = inputHelper (input);
            int[][] arrayC = inputHelper (input);
            int[][] arrayD = inputHelper (input);
            //do math on arrays using ArrayMath module
            int[][] addResult = ArrayMath.addArrays(arrayA, arrayB);
            int[][] subResult = ArrayMath.subtractArrays(arrayA, arrayB);
            int[][] multiplyResult = ArrayMath.multiplyArrays(arrayC,
                                                              arrayD);
            //use outputHelper to write results to file all pretty like
            outputHelper(arrayA, arrayB, arrayC, arrayD,
                         addResult, subResult, multiplyResult, output);
        } // close if filesOk statement
        if (input != null) {
          input.close();
        }
    } // close main


    //input.closeFile();
    //output.closeFile();
    //remember to close infile and outfile !!!!!!!

    /**
     This takes the input txt file and reads the file into an int 2D array
     based on the specified row and colum amount

     @param theListFile this specifies the input file to read from
     @return a 2D array
     */
    public static int[][] inputHelper (Scanner theListFile) {
        //read input file
        //get first two ints from file to get list col and row count
        int rowCount = theListFile.nextInt();
        int colCount = theListFile.nextInt();
        int[][] newArray = new int[rowCount][colCount];
        // get array into a 2d list
        for (int rowAmount = 0; rowAmount <= rowCount - 1; rowAmount++) {
            for (int colAmount = 0; colAmount <= colCount - 1;
                 colAmount++) {
                newArray[rowAmount][colAmount] = theListFile.nextInt();
            }
        }
        theListFile.nextLine();
        return newArray; //return the newArray we got from the file
    }
    /**
    This takes several arguments of 2d arrays and writes the formated results
    of these arrays for a txt file

    @param theArrayA this takes int 2dArray A to be added
    @param theArrayB this takes int 2dArray B to be added
    @param theArrayC this takes int 2dArray C to be multiplied
    @param theArrayD this takes int 2dArray D to be multiplied
    @param theAddResult this takes int 2dArray that is the sum of A & B
    @param theSubResult this takes int 2dArray that is the dif of A & B
    @param theMultiplyResult this takes int 2dArray that is * of C & D
    @param theOutput this is the file to write the program results to

    */
    public static void outputHelper (int[][] theArrayA, int[][] theArrayB,
                                     int[][] theArrayC, int[][] theArrayD,
                                     int[][] theAddResult,
                                     int[][] theSubResult,
                                     int[][] theMultiplyResult,
                                     PrintStream theOutput) {
        //call parameters to print a + b =
        theOutput.println ("MATRIX A PLUS MATRIX B:");
        theOutput.println(arrayFormatHelper(theArrayA, theArrayB,
                          theAddResult, "+", theOutput));

        //call parameters to print a - b =
        theOutput.println ("MATRIX A MINUS MATRIX B:");
        theOutput.println(arrayFormatHelper(theArrayA, theArrayB,
                          theSubResult, "-", theOutput));

        //call parameters to print c * d =
        theOutput.println ("MATRIX C TIMES MATRIX D:");
        theOutput.println(arrayFormatHelper(theArrayC, theArrayD,
                          theMultiplyResult, "X", theOutput));

    }
    /**
     Takes int 2D arrays as input, converts, formats and writes the arrays
     to a specified output file

     @param theArray1 this is an int 2D array A to be written to file
     @param theArray2 this is an int 2D array B to be written to file
     @param theArrayResults an int 2D array Result to be written to file
     @param theOperator this selects wheather you print +, -, or X
     @param theOutput this is where the 2d arrays are written to
     @return the results to to the caller
     */
    public static String arrayFormatHelper (int[][] theArray1,
                                            int[][] theArray2,
                                            int[][] theArrayResults,
                                            String theOperator,
                                            PrintStream theOutput) {
        //take the arrays passed in and print them in a horizontal format
        for (int count = 0; count < theArray1.length ||
             count < theArray2.length; count++) {
            // print first array row
            for (int item = 0; item < theArray1[0].length; item++) {
              if ( count >= theArray1.length) {
                theOutput.print(String.format("%10s", "" ));
              } else {
                theOutput.print(String.format("%10d",
                                              theArray1[count][item]));
              }
            }
            // print the operator second time through the outermost loop
            if (count == 1) {
                theOutput.print(String.format("%10s", theOperator));
            } else {
                theOutput.print(String.format("%10s", "" ));
            }
            // print second array row
            for (int item = 0; item < theArray2[0].length; item++) {
              if ( count >= theArray2.length) {
                theOutput.print(String.format("%10s", "" ));
              } else {
                theOutput.print(String.format("%10d",
                                              theArray2[count][item]));
              }
            }
            if (count == 1) { //print equals sign second time though
                theOutput.print(String.format("%10s", "=" ));
            } else {
                theOutput.print(String.format("%10s", "" ));
            }
            // print the third array row
            for (int item = 0; item < theArrayResults[0].length; item++) {
              if ( count >= theArrayResults.length) {
                theOutput.print(String.format("%10s", "" ));
              } else {
                theOutput.print(String.format("%10d",
                                theArrayResults[count][item]));
              }
            }
            //print a new line to start row 2 of arrays on the next line
            theOutput.println();
        }
        return "";

        // for loop for geting biggest number to set colum widths below
        /* I made these loops to format the colum widths to be the correct
        width. I got it to return the correct length of the bigges number
        in the arrays passed in but I could not figure out how to
        dynamicly insert this lenght into the string formatter.

        int columWidth = 0;
        String arrayToString = null;
        for (int row = 0; row < theArray1.length; row++) {
            for (int col =0; col < theArray1[0].length; col++)
                arrayToString = String.valueOf(theArray1[row][col]);
                //get item string length
                if (arrayToString.length() > columWidth){
                    columWidth = arrayToString.length();
                }
        }

        for (int row = 0; row < theArray2.length; row++) {
            for (int col =0; col < theArray2[0].length; col++)
                arrayToString = String.valueOf(theArray2[row][col]);
            // get item string length
            if (arrayToString.length() > columWidth){
                columWidth = arrayToString.length();
            }
        }
        for (int row = 0; row < theArrayResults.length; row++) {
            for (int col =0; col < theArrayResults[0].length; col++)
                arrayToString = String.valueOf(theArrayResults[row][col]);
            if (arrayToString.length() > columWidth){ //item string length
                columWidth = arrayToString.length();
            }
        }
        System.out.println(columWidth);
        */

    } // close arrayFormatHelper
} // close classAssign_2
