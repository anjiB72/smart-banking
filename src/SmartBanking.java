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
import java.util.Scanner;

public class SmartBanking {

    public static void main(String[] args){

        //check command line arguments has been completed
        if (args.length != 1){
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

        //initiate BufferedReader and Scanner objects to read input file
        BufferedReader reader = null;
        Scanner scanner = null;

        //Initiate FileWriter and PrintWriter object to write output to a new file
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        /*
        Initiate a new filereader object within a bufferedreader object for efficiency
        Try and catch block to ensure any file not found exceptions are caught and dealt with
        */
        try {
            reader = new BufferedReader(new FileReader(args[0]));
        } catch (FileNotFoundException fnfe){
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

                //TODO Insert main code here
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


