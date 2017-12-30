/*
SavingsAccount.java
TCSS 143 - Spring 2017
Instructor: David Schuessler
Project 4
*/

/**
This class represents a more specialized type of bank account, a savings
 account. In addition to the properties of a bank account, a savings
 account should record an active/inactive status property (as a private
 field). A savings account is considered active if the account balance is
 at least $25, otherwise the account is considered inactive. Withdrawals
 cannot be successfully completed from an inactive account. The
 constructor should initialize the owner and interest rate of the
 SavingsAccount object to the specified values.

 @author Josh Atherton  jatherton@uw.edu
 @version 1: April 20, 2017
 */

public class SavingsAccount extends BankAccount implements NamedAccount {
  /** final constant of the minimum savings for the account to be active */
  private static final double MINIMUM_SAVINGS = 25.0;
  /** final constant of withdrawals allowed before fee is applied */
  private static final int FREE_WITHDRAWALS_ALLOWED = 4;
  /** sets to true if user account is over MINIMUM_SAVINGS */
  private boolean myStatusIsActive;

  /**
  Makes an appropriate call to SavingsAccount’s super class constructor and
  also initializes the myStatusIsActive field to an appropriate value.

  @param theNameOfOwner user entered name of the the account owner
  @param theInterestRate user entered interest rate
  */
  public SavingsAccount(final String theNameOfOwner,
                        final double theInterestRate) {
    super(theNameOfOwner, theInterestRate); // call BankAccount constructor
    myStatusIsActive = false; // set active to false
  }
  /**
  Adds “theAmount” to the SavingsAccount object’s balance following the
  same rules as the deposit method of the BankAccount class. In addition,
  if the account balance after a successful deposit is at least $25
  dollars, the account status should be active.

  @param theAmount amount of money to be added to the balance of account
  @return true if can add amount to account, false otherwise
  */
  public boolean processDeposit(final double theAmount){
    boolean deposit = super.processDeposit(theAmount);
    if (getBalance() >= MINIMUM_SAVINGS) {
      myStatusIsActive = true;
    }
    return deposit;
  }
  /**
  Subtracts “theAmount” from the SavingsAccount object’s balance, as with
  the processWithdrawal method of the BankAccount class. If the account is
  inactive, the withdrawal should fail. If the withdrawal succeeds and the
  resulting balance is less than $25, the account status should become
  inactive after the money is withdrawn. Finally, the 5th successful
  withdrawal and any subsequent successful withdrawals each incur a $1
  service charge, to be deducted from the account at the end of the month
  by the performMonthlyProcess method as described next.

   @param theAmount the amount of money to be taken from the account
   @return true if able to withdraw money, false othewise
   */
   public boolean processWithdrawal(final double theAmount){
     boolean withdrawal = super.processWithdrawal(theAmount);
     // if account is not active do not try to withdraw money
     if (!myStatusIsActive) {
       withdrawal = false;
     } else { // if account is active
       // account is inactive if less than $25 inside
       if (getBalance() < MINIMUM_SAVINGS) {
         myStatusIsActive = false;
       }
       //add 1 dollar fee for every withdrawal after 5 withdrawals
       if (super.myMonthlyWithdrawCount > FREE_WITHDRAWALS_ALLOWED) {
         super.myMonthlyServiceCharges++;
       }
       withdrawal = true;
     }
     return withdrawal;
   }
   /**
   This method performs the same monthly processing as is done in the super
   class but also, updates the myStatusIsActive value based on the balance,
   i.e. it is set to true if the balance is greater than or equal to $25.00
   and false otherwise.
   */
   public void performMonthlyProcess() {
     super.performMonthlyProcess();
     myStatusIsActive = false;
     if (getBalance() >= MINIMUM_SAVINGS) {
       myStatusIsActive = true;
     }
   }
   /**
   Format the output in the form of a string if user calls to print.

   @return the formated savingsAccount toString
   */
   public String toString() {
     //for savings account
     String output = ", myStatusIsActive: " + myStatusIsActive;
    return "SavingsAccount" +
    super.toString().substring(11, (super.toString().length() - 1)) +
    output  + ']';
   }
}
