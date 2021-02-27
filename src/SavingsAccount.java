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
        float savingsBalance = this.getCurrentBalance();
        //check if amount is greater than savings account balance and change the transfer amount to the balance if it is.
        if (amount > savingsBalance) {
            amount = savingsBalance;
        }

        int currentAccountID = account.getAccountID();
        int savingsAccountID = this.getAccountID();

        //Create local variable for date & time and set a date format pattern
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String systemDateString = formatter.format(date);
        String systemTransactionDate = systemDateString+"T23:59:59Z";

        //Create local variable for the InitiatorType
        String initiator = "SYSTEM";

        //deposit amount into current account
        account.depositMoney(amount);
        //create new transaction for deposit to CurrentAccount
        Transaction depositTransaction = new Transaction(currentAccountID,"CURRENT",initiator,systemTransactionDate,amount);
        //Add depositTransaction to CurrentAccount transaction list
        account.getTransactionList().add(depositTransaction);
        System.out.println("System Transaction - Current account balance: " + account.getCurrentBalance());

        //Withdraw amount from Savings account
        amount = 0.00f - amount;//change amount to negative float
        //withdraw money from savings account
        this.withdrawMoney(amount);
        //create new transaction for withdrawal
        Transaction withdrawalTransaction = new Transaction(savingsAccountID, "SAVINGS", initiator, systemTransactionDate, amount);
        //Add withdrawalTransaction to SavingsAccount transaction list
        this.getTransactionList().add(withdrawalTransaction);
        System.out.println("System Transaction - Savings account balance: " + this.getCurrentBalance());
    }
}
