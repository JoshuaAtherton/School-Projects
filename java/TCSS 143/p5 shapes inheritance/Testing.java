import java.util.*;
import java.util.Scanner; // for the scanner
import java.io.*;   // for the input and output
import java.lang.String; // to split string

public class Testing {
   public static void main (String[] theArgs) {
   Scanner in = null;
   PrintStream out = null;
   boolean fileOK = false;

   String myFile = "in5.txt";

   try {
      in = new Scanner(new File(myFile));
      out = new PrintStream(new File("out5.txt"));
      fileOK = true;
   } catch (FileNotFoundException e) {
        System.exit(1);
   }

   outFile(in, out);
   in.close();
   out.close();
   } // end main

   public static void outFile(Scanner theInfile, PrintStream theOutfile) {
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
                } else if ( lineScan.hasNextDouble() && itemCount == 2 ) {
                    secondNum = lineScan.nextDouble();
                    isANumber2 = true;
                } else if ( lineScan.hasNextDouble() && itemCount == 3 ) {
                    thirdNum = lineScan.nextDouble();
                    isANumber3 = true;
                }
             } // close for loop

             System.out.print(totalWords + " word(s) on line.  "); // testing
          
             if ( totalWords == 1 && !line.trim().equals("") && isANumber1) {
                 System.out.print("Circle : ");
                 System.out.println(firstNum + " ");
             } else if ( totalWords == 2 && isANumber2) {
                 System.out.print("Rectangle : ");
                 System.out.println(firstNum + " " + secondNum + " ");
             } else if ( totalWords == 3 && isANumber2 && isANumber3) {
                 System.out.print("Triangle : ");
                 System.out.println(firstNum + " " + secondNum + " " + thirdNum + " ");
             } else {
                 //System.out.println("not valid input");
             }
             System.out.println("\t\t    " + line); // for testing
         
         } // close lineScan while
          
      } // close theInfile while
   } // close method

   // if first item in list has 1 number: create a circle
   // if first item in list has 2 numbers: create a rectangle
   // if first item in list has 3 numbers: create a triangle
   // the classes themselves with throw exceptions if numbers are not valid

    // now only error is creating a circle from the 3 three input line
    
    
    
    
    
    
    
    
   //working version below
   public static void outFileDDDDD (Scanner theInFile, PrintStream theOutFile) {
      while ( theInFile.hasNextLine() ) {
         String line = theInFile.nextLine();
         System.out.println(line);
         theOutFile.println(line);

      }
   } //
} // close class
