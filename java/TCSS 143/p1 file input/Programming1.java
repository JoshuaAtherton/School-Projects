/*
*  Programming1.java
*
*  TCSS 143 - Spring 2017
*  Instructor: David Schuessler
*  Project 1
*/
import java.util.Scanner;
/**
* This program gets user input for five different towns and creates a
* bar graph representing the user entered population for each town.
* – Each asterisk represents 1,000 people in population.
*
* @author Joshua Atherton   joshua9@uw.edu
* @version 1
*/
public class Programming1 {
  /**
  * Driver method of the class average processing.
  *
  * @param theArgs is used for command line input.
  */
  public static void main(String[] theArgs) {
    //call getPopulation method to get population of towns & store
    int town1Pop = getPopulation(1);
    int town2Pop = getPopulation(2);
    int town3Pop = getPopulation(3);
    int town4Pop = getPopulation(4);
    int town5Pop = getPopulation(5);

    System.out.println("\n" + "POPULATION GRAPH:");
    //call drawPopulationBar to convert town population to bar graph form
    drawPopulationBar(1, town1Pop);
    drawPopulationBar(2, town2Pop);
    drawPopulationBar(3, town3Pop);
    drawPopulationBar(4, town4Pop);
    drawPopulationBar(5, town5Pop);
  }
  /**
  * Gets population: Takes input from the user and returns entered
  * input as an integer.
  *
  * @param theTown is used to denote the current town for user input
  * @return the population of the town as an integer
  */
  public static int getPopulation(int theTown) {
    Scanner readInput = new Scanner(System.in); //Scanner to read input
    System.out.print("Enter the population of town " + theTown + ": ");
    int theTownPop = readInput.nextInt(); //store input into var townPop
    return theTownPop; //return population of town to caller
  }
  /**
  * Takes town populations and converts them into a bar graph
  *
  * @param Town this is the current town that we are referencing
  * @param theTownPop this is the population of the current town
  */
  public static void drawPopulationBar(int theTown, int theTownPop) {
    String graph = "";  // create an empty string for bar graph
    int asterisk = theTownPop / 1000; //calculate # of asterisks needed
    for (int i = asterisk; i > 0; i--) {
      graph += "*"; //concatenate # of asterisks need to graph variable
    }
    System.out.println("Town " + theTown + ": " + graph); //print results
  }
}
