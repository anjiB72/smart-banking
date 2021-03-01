/*
Title:  Smart Banking Feature
Author: Angela Boakes
Date: 28/02/2021

Notes:
Savings account inherits from the Account class
Has additional method to transfer savings to Current account automatically
*/

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SavingsAccount extends Account{

    public SavingsAccount() {
        super();
    }

    public SavingsAccount(int accountID) {
        super(accountID);
    }


    //Method to transfer money to current account automatically

    public List<Transaction> systemTransfer(CurrentAccount account, float amount, Date date) {

        List<Transaction> systemTransactions = new ArrayList<>();
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

        //Withdraw amount from Savings account
        amount = 0.00f - amount;//change amount to negative float
        //withdraw money from savings account
        this.withdrawMoney(amount);
        //create new transaction for withdrawal
        Transaction withdrawalTransaction = new Transaction(savingsAccountID, "SAVINGS", initiator, systemTransactionDate, amount);
        //Add withdrawalTransaction to SavingsAccount transaction list
        systemTransactions.add(withdrawalTransaction);

        //deposit amount into current account
        amount = 0.00f - amount;//change amount to +ve float
        account.depositMoney(amount);
        //create new transaction for deposit to CurrentAccount
        Transaction depositTransaction = new Transaction(currentAccountID,"CURRENT",initiator,systemTransactionDate,amount);
        //Add depositTransaction to CurrentAccount transaction list
        systemTransactions.add(depositTransaction);

       return systemTransactions;
    }
}
