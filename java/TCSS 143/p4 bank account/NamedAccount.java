/*
NamedAccount.java
TCSS 143 - Spring 2017
Instructor: David Schuessler
Project 4
*/

/**
Any classes that implement this interface “must” implement the
getAccountHolderName and setAccountHolderName methods. The
getAccountHolderName method will return the name of the account holder,
as appropriate for the implementing class. The setAccountHolderName
method will permit the account holder or owner to be changed.

@author Josh Atherton  jatherton@uw.edu
@version 1: April 20, 2017
*/

public interface NamedAccount {
  /**
  Get the name of the account holder and return it to the caller.
  This method required by the interface NamedAccount.java.

  @return the name of the account holder
  */
  String getAccountHolderName();
  /**
  Change the the account holder name to the entered name.
  This method required by the interface NamedAccount.java.

  @param theNewName the entered name to become the new account holder name
  */
  void setAccountHolderName(final String theNewName);
}
