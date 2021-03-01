# Smart Banking Feature

This is a java programme that runs from the command line.

It takes a CSV (comma separated values) file of account transactions, and undertakes an automatic transfer of funds from a savings account to a current account if
the current account has a negative balance at the end of the day.

It outputs a new file with the original transaction plus the new automated transactions.

<h2>Pre-requisites</h2>

<ul>
  <li>Java version 11.0.05 installed</li>
</ul>
  
 <h2>Assumptions</h2>
 <ul>
  <li>The Customer only has one current account and one savings account</li>
  <li>The input file will always have the naming convention of 'customer-"customerid"-ledger.csv</li>
  <li>The input file will always have a header row</li>
  <li>The transactions in the file will be in date order, starting with earliest first</li>
  <li>The System transfer occurs at the end of each day, where it is identified that the current account has gone into an overdraft</li>
  <li>The system transactions posted will have the date of the above day and a time of 23:59:59</li>
  <li>The output file is to a new file (not the original input file).  The output file will be named 'New-customer-"customerid"-ledger.csv'</li>
 </ul>
    
 
 
 <h2>How to run the programme</h2>
 <ul>
  <li>Download the files as a ZIP file using the Green button above - save to a local directory on your computer</li>
  <li>Extract files from the Zipped folder and navigate to the src folder</li>
  <li>Open up a command line interface such as Command Prompt on Windows</li>
  <li>Navigate to the src folder in your command line</li>
  <li>To build the programme, in the command line, type 'javac *.java'. </li>
  <li>You should see a new line appear if the compilation is successful & .class files appear in the src folder.</li>
  <li>Move the .csv ledger file that you want to use into the src folder </li>
  <li>Type 'java SmartBanking name-of-ledgerfile.csv' into the command line</li>
  <li>The output file will save to the src folder and should be visible in the folder to open and view the output</li>
</ul>
 
  

