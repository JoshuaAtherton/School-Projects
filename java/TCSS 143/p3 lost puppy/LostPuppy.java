/*
LostPuppy.java
TCSS 143 - Spring 2017
Instructor: David Schuessler
Project 3
*/

import java.util.Random;
import java.lang.Math;

/**
This Class builds a 2D array to represent a building, hides the puppy in
the building, checks if the puupy has been found, and prints out an array
representing the current version of the building. Uses driver
PuppyPlay.java to run a guess and find game.

@author Josh Atherton  jatherton@uw.edu
@version 1: April 16, 2017
*/

public class LostPuppy {
  /** array representing rooms/floors in a building */
  private char[][] myHidingPlaces;
  /** the floor location o the puppy */
  private int myFloorLocation;
  /** the room location of the puppy */
  private int myRoomLocation;
  /** to hold character of winning player when a player finds the puppy */
  private char myWinner;
  /** flag to see if puppy has been found, set to true when puppy found */
  private boolean myFound;
  /**
  Constructor. Set up a building by creating a 2D array representing the
  number of floors(rows) and rooms(cols) entered by the user in the
  driver class. Put the lost puppy inside the building.

  @param theFloors this is the user entered floor number (row)
  @param theRooms this is the user entered room number (col)
  */
  public LostPuppy(int theFloors, int theRooms) {
    //create 2D array of the size player specified
    myHidingPlaces = new char[theFloors][theRooms];
    // fill the array full of one space char ' '
    for (int row = 0; row < myHidingPlaces.length; row++) {
      for (int col = 0; col < myHidingPlaces[row].length; col++) {
        myHidingPlaces[row][col] = ' ';
      }
    }
    // put the lost puppy in his place
    Random random = new Random();
    // randomized floor puppy is on, use first param
    myFloorLocation = random.nextInt(theFloors);
    // randomized room puppy is lost in, use second param
    myRoomLocation = random.nextInt(theRooms);
    // put the puppy into the randomly picked spot in the 2D array
    myHidingPlaces[myFloorLocation][myRoomLocation] = 'P';
    myWinner = ' '; // set my winner to an empty string
    myFound = false; // initiate puppy to lost

  }
  // ------ Methods ------ below -------

  /**
  Receives the floor and room to be searched and returns true if the
  room has already been searched (has a value or 1 or 2), false otherwise.

  @param theFloor user entered floor (row) value
  @param theRoom user entered room (col) value
  @return true if array value equals 1 or 2, otherwise false
  */
  public boolean roomSearchedAlready(int theFloor, int theRoom) {
    if (myHidingPlaces[convertUserFloor(theFloor)][theRoom] == 1 ||
        myHidingPlaces[convertUserFloor(theFloor)][theRoom] == 2) {
      return true;
    } else {
      return false;
    }

  }
  /**
  Receives the floor and room to be searched and returns true if puppy
  found false otherwise. This should does not change any of the fields.

  @param theFloor row value of the 2D array
  @param theRoom col value of the 2D array
  @return true if entered array index equals 'P', false otherwise
  */
  public boolean puppyLocation(int theFloor, int theRoom) {
    if (myHidingPlaces[theFloor][theRoom] == 'P') {
      return true;
    } else {
      return false;
    }
  }
  /**
  Receives the floor and room to be searched and returns true if the
  floor and room values are within the array indices range, false otherwise
  (used to check that these indices will not cause an error when applied to
  the array).

  @param theFloor user entered floor (row)
  @param theRoom user entered room (col)
  @return true if entered value is within array, false otherwise
  */
  public boolean indicesOK(int theFloor, int theRoom) {
    if ( theFloor < numberOfFloors() && theRoom < numberOfRooms() ) {
      return true;
    } else {
      return false;
    }
  }
  /** returns how many floors are in the building – starting at zero.
  (Rows)

  @return an int representing the number of rooms
  */
  public int numberOfFloors() {
    return myHidingPlaces.length;
  }
  /** returns # of rooms are on each floor of the building - starting at
  zero. (colums)

  @return an int representing the number of rooms
  */
  public int numberOfRooms() {
    return myHidingPlaces[0].length;
  }
  /**
  Search floor/room and returns true if puppy is found
  when found sets myWinner field to the current player AND sets myFound to true
  if puppy not found set myHidingPlaces array at the recieved floor/room num
  to received player vales ('1' or '2') and set to false.

  @param theFloor takes the user entered floor
  @param theRoom takes the user entered room
  @param theCurrentPlayer which player is
  @return true the puppy is found, false otherwise
  */
  public boolean searchRoom(int theFloor, int theRoom,
                            char theCurrentPlayer) {
    boolean flag = false;
    // first if: did they find the puppy?
    if (myHidingPlaces[convertUserFloor(theFloor)][theRoom] == 'P') {
      myWinner = theCurrentPlayer; // set winner to either '1' or '2'
      myFound = true;
      flag = true;
    // the puppy was not there, Put player char into current location
    //in building array.
    } else if (myHidingPlaces[convertUserFloor(theFloor)][theRoom]
               == ' ') {
      myHidingPlaces[convertUserFloor(theFloor)][theRoom]
      = theCurrentPlayer;
      flag = false;
    }
    return flag;
  }
  /**
  Displays the current hidingPlaces array and it's contents except the
  location of the puppy which remains hidden until it's found at which
  point toString will be called by the driver; then both the player who
  found the puppy and a 'P' will be displayed in the same cell.

  @return the current version of the building in the form of a string
  */
  public String toString() {
    String building = "";
    // build the roof of the building!
    for (int roof = 0; roof < myHidingPlaces[0].length; roof++) {
        if (roof == 0) {
          building += " ___";
        } else if (roof == myHidingPlaces[0].length - 1) {
          building += "____ ";
        } else {
          building += "____";
        }
    }
    for (int floor = 0; floor < numberOfFloors(); floor++) {
       building += "\n|"; // upper wall of first room
       for (int room = 0; room < numberOfRooms(); room++) {
          if (puppyLocation(floor, room)) {
             // myFound true; print player char and a P. Puppy's found.
             if(myFound) {
               building += "" + myWinner + 'p' + " ";
             } else { // myfound false hide the puppy
               building += "   ";
             }
           } else { // if puppyLocation false
             building += " " + myHidingPlaces[floor][room] + " ";
           }
           building += "|";
       } // close room for loop
       building += "\n" + "|";
       for (int bottomRoom = 0; bottomRoom <=
           (myHidingPlaces[0].length - 1); bottomRoom++) {
          building += "___|";
       }
    } // close floor for loop
    return building;
  } // close toString

  /**
  Method to convert the user entered floor to the equivalent array row.
  Say if they enter 3 for the top floor of building that is acutally array
  index 0 not 3 as the user entered

  @param theUserFloor takes the user entered number
  @return the correct array row index number
  */
  public int convertUserFloor(int theUserFloor) {
    if (theUserFloor == myHidingPlaces.length - 1) {
      return 0;
    } else {
      return Math.abs((myHidingPlaces.length - 1) - theUserFloor);
    }
  }
} // end LostPuppy class
