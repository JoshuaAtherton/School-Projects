/*
SafeDepositBoxAccount.java
TCSS 143 - Spring 2017
Instructor: David Schuessler
Project 4
*/

/**
This class represents the rental of a bank's safe deposit box to a
customer. Because banks do not know what is stored in the safe deposit
box, the only information that the class will keep track of is the
name of the person who owns the safe deposit box account (as a
private field).You are to write the code that makes up the methods of this
class (Remember, you are implementing NamedAccount). Also, don’t forget
the name or the owner field.

@author Josh Atherton  jatherton@uw.edu
@version 1: April 20, 2017
*/
public class SafeDepositBoxAccount implements NamedAccount {
  /** the name of the safe box account holder */
  private String safeBoxAccountOwner;

  /**
  This constructor sets up SafeDepositBoxAccount parameters.
  Takes a name as input and sets that name to the value of the safe
  box owner.

  @param theSafeBoxOwner the name of the safety box owner
  */
  public SafeDepositBoxAccount (final String theSafeBoxOwner) {
    safeBoxAccountOwner = theSafeBoxOwner;
  }
  /**
  This method gets the name of the account ownner for the caller.

  @return the name of the name of the safe box owner
  */
  public String getAccountHolderName() {
    return safeBoxAccountOwner;
  }
  /**
  This method sets the name of the safe box owner to a new name.

  @param theNewName the name of the safe box owner
  */
  public void setAccountHolderName(final String theNewName) {
    safeBoxAccountOwner = theNewName;
  }
  /**
  Format the output in the form of a string if called to print

  @return a formated string to the console
  */
  public String toString() {
    return "SafeDepositBoxAccount[owner: " + getAccountHolderName() + "]";
  }
}
