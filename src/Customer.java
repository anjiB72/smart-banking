/*
Title:  Smart Banking Feature
Author: Angela Boakes
Date: 27/02/2021

Notes:
Assumptions made that a Customer will only have one current account
and one Savings account only.
If customer can have more than one of each type, Lists for CurrentAccounts and a List for SavingsAcccounts
could be added to the class.
*/

public class Customer {

    private int customerID;
    private boolean hasCurrentAcc;
    private boolean hasSavingsAcc;
    private CurrentAccount currentAccount;
    private SavingsAccount savingsAccount;

    //Constructor
    public Customer(int customerID) {
        this.customerID = customerID;
        this.hasCurrentAcc = false;
        this.hasSavingsAcc = false;
        this.currentAccount = null;
        this.savingsAccount = null;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public boolean isHasCurrentAcc() {
        return hasCurrentAcc;
    }

    public void setHasCurrentAcc(boolean hasCurrentAcc) {
        this.hasCurrentAcc = hasCurrentAcc;
    }

    public boolean isHasSavingsAcc() {
        return hasSavingsAcc;
    }

    public void setHasSavingsAcc(boolean hasSavingsAcc) {
        this.hasSavingsAcc = hasSavingsAcc;
    }

    public CurrentAccount getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(CurrentAccount currentAccount) {
        this.currentAccount = currentAccount;
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(SavingsAccount savingsAccount) {
        this.savingsAccount = savingsAccount;
    }
}
