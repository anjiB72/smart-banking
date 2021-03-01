/*
Title:  Smart Banking Feature
Author: Angela Boakes
Date: 28/02/2021

Notes:
Assumptions made:
- The system transfer occurs at the end of each day when there have been transactions to & from the current account if the balance of
the current account is below zero & will always occur before overdraft interest is calculated.
- The input file filename will always be customer-<customerid>-ledger.csv
- The input file will always have a header row
-The output is to a new file (not to the original file).  Output file will be named New-customer-<customerid>-ledger.csv
*/
import java.io.*;
import java.text.ParseException;
import java.util.*;

public class SmartBanking {

    public static void main(String[] args) {

        //check command line arguments has been completed
        if (args.length != 1) {
            System.out.println("Please enter csv file name as a command line argument");
            System.exit(1);
        }

        //Get customer ID from file name
        String fileName = args[0];
        String[] splitFileName = fileName.split("-");
        String customerIDString = splitFileName[1];
        int customerID = Integer.parseInt(customerIDString);

        //Create new customer object
        Customer customer = new Customer(customerID);
         /*
         Create new current account and savings account objects and assign the customers accounts
         */
        CurrentAccount currentAccount = customer.getCurrentAccount();
        SavingsAccount savingsAccount = customer.getSavingsAccount();

        //initiate BufferedReader and Scanner objects to read input file
        BufferedReader reader = null;
        Scanner scanner = null;

        //Initiate FileWriter and PrintWriter object to write output to a new file
        FileWriter fileWriter;
        PrintWriter printWriter;

        /*
        Initiate a new filereader object within a bufferedreader object for efficiency
        Try and catch block to ensure any file not found exceptions are caught and dealt with
        */
        try {
            reader = new BufferedReader(new FileReader(args[0]));
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error opening file '" + args[0] + "'");
            System.exit(2);
        }

        /*
        Initiate a new scanner object to deal with the file reader object and read csv data
        Try and catch block to deal with any input / output exceptions
         */
        try {
            scanner = new Scanner(reader);
            fileWriter = new FileWriter("New-" + fileName, true);
            printWriter = new PrintWriter(fileWriter);
            String headerLine = scanner.nextLine(); //reads any header line first and stores in a String
            //Prints headerline to new file
            printWriter.println(headerLine);

            //While the file has lines, reads all lines in the file & process the transactions as necessary
            while (scanner.hasNextLine()) {
                //Store transaction data to a String
                String transactionLine = scanner.nextLine();
                //Separate out transaction data into an array of Strings
                String[] transactionData = transactionLine.split(",");
                String idString = transactionData[0];
                String accountType = transactionData[1];
                String initiatorType = transactionData[2];
                String dateTime = transactionData[3];
                String value = transactionData[4];

                //Convert id and value to int and float
                int accountID = Integer.parseInt(idString);
                float amount = Float.parseFloat(value);

                //Create new transaction object
                Transaction transaction = new Transaction(accountID, accountType, initiatorType, dateTime, amount);

                //Check account type & create account if no type exists
                if (accountType.equalsIgnoreCase("CURRENT")) {
                    // check if hasCurrentAcc is false & create new current account
                    if (customer.isHasCurrentAcc()) {
                        currentAccount = customer.getCurrentAccount();
                    } else {
                        //create new current account
                        currentAccount.setAccountID(accountID);
                        customer.setHasCurrentAcc(true);
                    }
                } else {
                    // check if hasSavingsAcc is false & create new savings account
                    if (customer.isHasSavingsAcc()) {
                        //If customer does have savings account
                        savingsAccount = customer.getSavingsAccount();
                    } else {
                        //create new savings account
                        savingsAccount.setAccountID(accountID);
                        customer.setHasSavingsAcc(true);
                    }
                }

                    /*
                    The next If statement checks the date (Day not time) of any previous transaction to the date of current transaction
                    If the date is before the current transaction (which would suggest the day before), then it checks the current balance
                    If the balance is less than 0 then it will move any savings to bring balance to 0 or as close to 0 depending on savings balance.
                    It posts the transaction date of that system transfer as the prevTransaction date & time of 23:59:59.
                     */
                try {
                    if (customer.getAllTransactions().size() > 0) { //Checks that there are entries in the transaction list
                        Date prevTransaction = customer.getLastTransactionDate();
                        Date currTransaction = transaction.stringToDate(dateTime);

                        //Check if prevTransaction date is before currTransaction date
                        if (prevTransaction.compareTo(currTransaction) < 0) {
                            float balance = currentAccount.getCurrentBalance();
                            //Check current account balance - if less than 0, undertake a system transfer
                            if (balance < 0.00) {
                                float amountToTransfer = 0 - currentAccount.getCurrentBalance(); //ensures amount is +ve float
                                List<Transaction> systemTransactions = savingsAccount.systemTransfer(currentAccount, amountToTransfer, prevTransaction);
                                customer.getAllTransactions().addAll(systemTransactions);
                            }
                        }
                    }
                } catch (ParseException pe) {
                    System.err.println(pe.getMessage());
                }
                //Once any system transfers have occurred then the current transaction is posted and the balance updated
                if(accountType.equalsIgnoreCase("CURRENT")){
                    currentAccount.manualTransaction(amount);
                } else {
                    savingsAccount.manualTransaction(amount);
                }
                customer.getAllTransactions().add(transaction);
            }//End of While loop

            //Loop through transaction list, convert to comma separated string and print to file
            List<Transaction> allTransactions = customer.getAllTransactions();
            for (Transaction transaction: allTransactions){
                String transactionString = transaction.toString();
                printWriter.println(transactionString);
            }
            printWriter.close();
            fileWriter.close();
        }
        catch(IOException ioe){
            System.err.println(ioe.getMessage());
            System.exit(3);
        }
        finally {
            scanner.close();
        }

    }
}


