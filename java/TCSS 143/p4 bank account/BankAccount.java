/*
BankAccount.java
TCSS 143 - Spring 2017
Instructor: David Schuessler
Project 4
*/

/**
This Class builds a Bank account to store an account user name as well as
other essential processes like the acount balance and withdrawal fees.
Uses driver AccountTester.java to simulate a bank account.

@author Josh Atherton  jatherton@uw.edu
@version 1: April 20, 2017
*/
//represents a basic bank account
public class BankAccount implements NamedAccount {
  /** final constant represents months in the year: 12.0  */
  public static final double MONTHS_IN_YEAR = 12.0;
  /** customer account holder name */
  private String myName;
  /** total funds in my account */
  private Double myAccountBalance;
  /** caller entered intrest rate */
  private Double myInterestRate;
  /** the # of Withdrawals this month */
  protected int myMonthlyWithdrawCount;
  /** the service charges this month  */
  protected double myMonthlyServiceCharges;

  /**
  The constructor -- initializes the name and interest rate to the values
  of the passed parameters (taking care that the interest rate is a
  legitimate value) and sets the remaining fields to 0.

  @param theNameOfOwner to become that name of the account holder
  @param theInterestRate the entered user interest rate for this account
  */
  public BankAccount(final String theNameOfOwner,
                     final double theInterestRate) {
    myName = theNameOfOwner;
    myInterestRate = 0.0; // set to 0.0 by default
    // if caller enters a rate greater than 0.0 use that rate
    if (theInterestRate >= 0.0 ) {
      myInterestRate = theInterestRate;
    }
    myAccountBalance = 0.0;
    myMonthlyWithdrawCount = 0;
    myMonthlyServiceCharges = 0.0;
  }
  /**
  Get the current balance of the account

  @return the current balance of account
  */
  public double getBalance() {
    return myAccountBalance;
  }
  /**
  Adds the specified amount of money to the calling BankAccount object.
  However, if the “theAmount” of money is zero or negative, the deposit
  fails (it's not possible to withdraw money by depositing a negative
  amount), the account balance is unchanged, and the method should return
  false. Otherwise, if the amount of money is positive, the deposit
  succeeds and the method should return true.

  @param theAmount the amount of money to be added to account
  @return true if a valid amount was entered, false otherwise
  */
  public boolean processDeposit(final double theAmount) {
    boolean deposit = false;
    if (theAmount > 0.0) {
      myAccountBalance += theAmount;
      deposit = true;
    }
    return deposit;
  }
  /**
  Subtracts the specified amount of money from the calling BankAccount
  object. However, if the amount of money is not a positive value or is
  more than the current balance of the account, the withdraw fails, the
  Otherwise, the withdrawal succeeds, the record of successful
  withdrawals made in the current month is incremented, and the method
  should return true.

  @param theAmount amount of money to be subtacted from account
  @return true if a valid amount entered, false otherwise
  */
  public boolean processWithdrawal(final double theAmount) {
    boolean withdrawal = false;
    if (theAmount > 0.0 && theAmount <= myAccountBalance) {
      myAccountBalance -= theAmount; // take out the entered amount
      myMonthlyWithdrawCount++; // increment withdrawal count
      return true; // withdrawal was successful
    }
    return withdrawal;
  }
  /**
  Returns the monthly interest based on the APR (Annual Percentage Rate).
  Which is: 𝑚𝑦𝐵𝑎𝑙𝑎𝑛𝑐𝑒 ∗ 𝑚𝑦𝐼𝑛𝑡𝑒𝑟𝑒𝑠𝑡𝑅𝑎𝑡𝑒 / 12.0 Do NOT use APY
  (Annual Percentage Yield). APY is derived when the above (APR) is
  compounded (daily, monthly, quarterly, etc.) during the course of a
  year.

  @return the calculated interest for the month (APR)
  */
  public double calculateInterest() {
    return myAccountBalance * (myInterestRate / MONTHS_IN_YEAR);

  }
  /**
  Subtracts all monthly service charges from the balance, adds the monthly
  interest accrued to the balance through a call to calculateInterest(),
  and resets the withdrawal count and service charge amount to 0. Finally,
  the balance should not be set to anything less than 0.
  */
  public void performMonthlyProcess() {
    //can not set balance to anything less than 0
    myAccountBalance -= myMonthlyServiceCharges;
    if (myAccountBalance < 0.0) {
        myAccountBalance = 0.0;
    }
    myAccountBalance += calculateInterest();
    myMonthlyWithdrawCount = 0;
    myMonthlyServiceCharges = 0;
  }
  /**
  Get the name of the account holder and return it to the caller.
  This method required by the interface NamedAccount.java.

  @return the name of the account holder
  */
  public String getAccountHolderName (){
    return myName;
  }
  /**
  Change the the account holder name to the entered name.
  This method required by the interface NamedAccount.java.

  @param theNewName the entered name to become the new account holder name
  */
  public void setAccountHolderName (final String theNewName) {
    myName = theNewName;
  }
  /**
  Format the output in the form of a string if user calls to print.

  @return the formated BankAccount in a string form
  */
  public String toString() {
    String output = "BankAccount[owner: " + getAccountHolderName() +
        ", balance: " + String.format("%.2f", getBalance()) +
        ", interest rate: " + String.format("%.2f", myInterestRate) +
        "\n\tnumber of withdrawals this month: " + myMonthlyWithdrawCount +
        ", service charges for this month: " +
        String.format("%.2f", myMonthlyServiceCharges) + "]";

    return output;
  }
}
