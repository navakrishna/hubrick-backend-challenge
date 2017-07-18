package com.hubrick.challenge.main;

import java.io.IOException;

import com.hubrick.challenge.delegator.ChallengeDelegator;

/**
 * It initiates the flow to process the logic. 
 * 
 */
public class ChallengeMain {
 /**
 * @param args
 * 
 * Main method triggering point for the business flow. 
 * Inputs are taken from run time arguments in the below order.
 * args[0] = Departments.csv
 * args[1] = Age.csv
 * args[2] = Employees.csv
 * 
 * @NOTE: 
 * There are data issues in below files.
 * Employees.csv -- For one user salary is populated in gender column,
 * 					I have corrected it.I have corrected to salary the 100th employee to 4470.00 from 4470.0O 
 * Department.csv -- There are only 7 departments but in employees.csv 8th dept id is also present.
 * 
 * I am using column order to set the values on Pojos. Please make sure if you want to run the program on other
 * csv files maintain the same order.
 * 
 * 
 */
public static void main(String[] args) {
	    ChallengeDelegator delegator = new ChallengeDelegator();
	    try {
			delegator.delegateChallenge(args[0], args[1], args[2]);
			System.out.println("Please find the output files in output folder of project");
		} catch (IOException e) {
			e.getMessage();
		}
   }
}
