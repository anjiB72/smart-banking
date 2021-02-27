/*
Title:  Smart Banking Feature
Author: Angela Boakes
Date: 27/02/2021

Notes:
Transaction class stores details of individual transactions related to an account
By encapsulating the data within it's own class makes the code easier to maintain and update as required.
Assuming the data were to be persisted at some point, then this class could align to a database table.
*/


public class Transaction {

    private int accountID;
    private String accountType;
    private String initiatorType;
    private String transactionDateTime;
    private float transactionAmount;

    //Constructor
    public Transaction(int accountID, String accountType, String initiatorType, String transactionDateTime, float transactionAmount) {
        this.accountID = accountID;
        this.accountType = accountType;
        this.initiatorType = initiatorType;
        this.transactionDateTime = transactionDateTime;
        this.transactionAmount = transactionAmount;
    }

    //Getters & Setters
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getInitiatorType() {
        return initiatorType;
    }

    public void setInitiatorType(String initiatorType) {
        this.initiatorType = initiatorType;
    }

    public String getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    /*
    OTHER CLASS METHODS
     */

    //float amount to string
    public String floatToString(float amount){
        return Float.toString(amount);
    }

    //accountID to string
    public String intToString(int id){
        return Integer.toString(id);
    }

    @Override //convert transaction details to comma separated string
    public String toString() {
        return  this.intToString(accountID) + ","+
                accountType + "," +
                initiatorType + "," +
                transactionDateTime + "," +
                this.floatToString(transactionAmount);
    }
}
