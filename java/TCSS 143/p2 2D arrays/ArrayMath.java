/*
ArrayMath.java

TCSS 143 - Spring 2017
Instructor - David Schuessler
Programming assigment 2
*/
/**
This program takes 2d arrays as inputs and either adds, subtracts, or
multiplys the arrays and returns a single 2d array result to the caller

@author Josh Atherton    joshua9@uw.edu
@version 1
*/
public class ArrayMath {
    /**
     Takes two 2D arrays of the same size and adds them together.

     @param theArray1 this is the first int array
     @param theArray2 this is the second in array
     @return return a single 2D array that is the result of the
     added arrays.
     */
    public static int[][] addArrays (int[][] theArray1,
                                     int[][] theArray2) {
        int rowNum = theArray1.length; // get the amount of rows
        int colNum = theArray1[0].length; // get the amount of colums
        int[][] addedArray = new int[rowNum][colNum]; //first row for size
        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                addedArray[row][col] = theArray1[row][col] +
                                       theArray2[row][col];
            }
        }
        return addedArray; //returns a new array of the sum
    }
    /**
     Takes two 2D arrays as input and subtracts them returning
     the new array that is the result of the two

     @param theArray1 array 1 to be added
     @param theArray2 array 2 to be added
     @return an 2D array that is the difference of the two arrays
     */
    public static int[][] subtractArrays(int[][] theArray1,
                                         int[][] theArray2) {
        int rowNum = theArray1.length;  // get the amount of rows
        int colNum = theArray1[0].length; // get the amount of colums
        int[][] subArray = new int[rowNum][colNum]; //first row for size
        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                subArray[row][col] = theArray1[row][col] -
                                     theArray2[row][col];
            }
        }
        return subArray; //returns a new array of the difference
    }
    /**
     This takes two arrays as input and returns one single array
     that is the product both input arrays.

     @param theArray1 this is the first array to times
     @param theArray2 this is the second array to times
     @return a 2D array that is the product of the input arrays
     */
    public static int[][] multiplyArrays (int[][] theArray1,
                                          int[][] theArray2) {
        int rowsInA = theArray1.length; //amount of rows in first arrayA
        int columnsInA = theArray1[0].length; //amount of col in arrayA
        int columnsInB = theArray2[0].length; //amount of col in arrayB
        int[][] multipliedArray = new int[rowsInA][columnsInB];
        for (int row = 0; row < rowsInA; row++) {
            for (int col = 0; col < columnsInB; col++) {
                for (int times = 0; times < columnsInA; times++) {
                    multipliedArray[row][col] = multipliedArray[row][col]
                    + theArray1[row][times]
                    * theArray2[times][col];
                }
            }
        }
        return multipliedArray; //returns a new array of the product
    }
} // close ArrayMath class
