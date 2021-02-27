/*
Title:  Smart Banking Feature
Author: Angela Boakes
Date: 27/02/2021

Notes:
The Account class is the super class for the current and savings account who inherit it's
properties and methods.

*/

import java.util.List;

public class Account {

    private int accountID;
    private float currentBalance;
    List<Transaction> transactionList;

    //Constructor
    public Account(int accountID) {
        this.accountID = accountID;
        this.currentBalance = 0.00f;
        this.transactionList = null;
    }

    //Getters & setters

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public float getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(float currentBalance) {
        this.currentBalance = currentBalance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    /*
    Other class methods
     */
    public String accountIDToString(int accountID){
        return Integer.toString(accountID);
    }

    public void depositMoney(float amount){
        this.setCurrentBalance(this.getCurrentBalance() + amount);
    }

    public void withdrawMoney(float amount){
        this.setCurrentBalance(this.getCurrentBalance() + amount);
    }

    public void manualTransaction(float amount){
        if (amount > 0) {
            this.depositMoney(amount);
        } else {
            this.withdrawMoney(amount);
        }
    }

}