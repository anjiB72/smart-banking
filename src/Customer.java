/*
Title:  Smart Banking Feature
Author: Angela Boakes
Date: 28/02/2021

Notes:
Assumptions made that a Customer will only have one current account
and one Savings account.
If customer can have more than one of each type, Lists for CurrentAccounts and a List for SavingsAccounts
could be added to the class.
*/

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {

    private int customerID;
    private boolean hasCurrentAcc;
    private boolean hasSavingsAcc;
    private CurrentAccount currentAccount;
    private SavingsAccount savingsAccount;
    private List<Transaction> allTransactions;

    //Constructor
    public Customer(int customerID) {
        this.customerID = customerID;
        this.hasCurrentAcc = false;
        this.hasSavingsAcc = false;
        this.currentAccount = new CurrentAccount();
        this.savingsAccount = new SavingsAccount();
        this.allTransactions = new ArrayList<>();
    }


    //Getters & Setters
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

    public List<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public void setAllTransactions(List<Transaction> allTransactions) {
        this.allTransactions = allTransactions;
    }


    //Get date of last entry in Transaction List
    public Date getLastTransactionDate() throws ParseException {
        int listLength = this.allTransactions.size();
        Transaction lastTransaction =  this.allTransactions.get(listLength-1);
        return lastTransaction.stringToDate(lastTransaction.getTransactionDateTime());
    }
}
