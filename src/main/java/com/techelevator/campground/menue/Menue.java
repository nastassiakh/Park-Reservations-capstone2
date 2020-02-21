package com.techelevator.campground.menue;

import java.util.Scanner;

public class Menue {
	Scanner scan = new Scanner(System.in);

	public String getParkIdFromUser() {

		System.out.println("Please, print code of the Park for Further Detail (ex.1)");
		String userInputParkId = scan.nextLine();
		return userInputParkId;
	}

	public String getSecondChoiceFromUser() {

		System.out.println("Please, Select a Command");
		String userInputCommand = scan.nextLine();
		return userInputCommand;
	}

	public String getUserChoiceForReservation() {
		System.out.println("Please, Select a Command");
		String userInputReservSearch = scan.nextLine();
		return userInputReservSearch;
	}

	public String getCampgroundIdFromUser() {

		System.out.println("Enter code of the campground you are interesed in (ex.1), enter 0 to cancel");
		String campgroundId = scan.nextLine();
		return campgroundId;
	}

	public String getArrivalDateFromUser() {

		System.out.println("What is the arrival date (YYYY/MM/DD)?");
		String fromDate = scan.nextLine();
		return fromDate;
	}
	
	public String getDepartureDateFromUser() {

		System.out.println("What is the departure date (YYYY/MM/DD)?");
		String toDate = scan.nextLine();
		return toDate;
	}
	
	public String getSiteIdFromUser() {

		System.out.println("Which site should be reserved?");
		String siteId = scan.nextLine();
		return siteId;
	}
	
	public String getNameOfUser() {

		System.out.println("Which site should be reserved?");
		String nameOfPerson = scan.nextLine();
		return nameOfPerson;
	}
	
}
