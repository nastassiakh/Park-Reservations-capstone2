package com.techelevator.campground.menue;

import java.util.Scanner;

public class Menue {
	Scanner scan = new Scanner(System.in);
	
	public String getParkNameFromUser() {

		System.out.println("Please, print Name of the Park for Further Detail");
		String userInputParkName = scan.nextLine();
		return userInputParkName;
	}
	
	public String getSecondChoiceFromUser() {
		
		System.out.println("Please, Select a Command ");
		String userInputParkName = scan.nextLine();
		return userInputParkName;
	}

}
