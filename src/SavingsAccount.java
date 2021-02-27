/*
Title:  Smart Banking Feature
Author: Angela Boakes
Date: 28/02/2021

Notes:
Savings account inherits from the Account class
Has additional method to transfer savings to Current account automatically
*/

import java.text.SimpleDateFormat;
import java.util.Date;

public class SavingsAccount extends Account{


    public SavingsAccount(int accountID) {
        super(accountID);
    }


    //Method to transfer money to current account automatically
    public void systemTransfer(CurrentAccount account, float amount, Date date) {
        float transferAmount = 0.00f;
        float savingsBalance = this.getCurrentBalance();
        //check if amount is greater than savings account balance and change the transfer amount to the balance if it is.
        if (amount > savingsBalance) {
            transferAmount = savingsBalance;
        } else {
            transferAmount = amount;
        }
        int currentAccountID = account.getAccountID();
        int savingsAccountID = this.getAccountID();

        //Create local variable for date & time and set a date format pattern
        Date systemDate = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String systemDateString = formatter.format(systemDate);
        String systemTransactionDate = systemDateString+"T23:59:59Z";

        //Create local variable for the InitiatorType
        String initiator = "SYSTEM";

        //deposit amount into current account
        account.depositMoney(transferAmount);
        //create new transaction for deposit to CurrentAccount
        Transaction depositTransaction = new Transaction(currentAccountID,"CURRENT",initiator,systemTransactionDate,transferAmount);
        //Add depositTransaction to CurrentAccount transaction list
        account.getTransactionList().add(depositTransaction);

        //Withdraw amount from Savings account
        transferAmount = 0.00f - transferAmount;//change amount to negative float
        //withdraw money from savings account
        this.withdrawMoney(transferAmount);
        //create new transaction for withdrawal
        Transaction withdrawalTransaction = new Transaction(savingsAccountID, "SAVINGS", initiator, systemTransactionDate, transferAmount);
        //Add withdrawalTransaction to SavingsAccount transaction list
        this.getTransactionList().add(withdrawalTransaction);
    }
}
