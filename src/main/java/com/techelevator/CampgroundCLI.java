package com.techelevator;

import java.time.LocalDate;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.campground.menue.Menue;
import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.ParkDAO;
import com.techelevator.campground.model.Parks;
import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.ReservationDAO;
import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.campground.model.jdbc.JDBCParksDAO;
import com.techelevator.campground.model.jdbc.JDBCReservation;

public class CampgroundCLI {

	CampgroundDAO camp;
	ReservationDAO res;
	ParkDAO park;
	Menue menue;
	String userInputParkId;

	public static void main(String[] args) {
		CampgroundCLI application = new CampgroundCLI();
		application.run();

	}

	public CampgroundCLI() {
		// create your DAOs here

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		camp = new JDBCCampgroundDAO(dataSource);
		res = new JDBCReservation(dataSource);
		park = new JDBCParksDAO(dataSource);
		menue = new Menue();

	}

	public void run() {
		// this.findAvailableReservations();

		while (true) {

			// 1.print all National Parks by Name 2.Ask user and print
			// getParkInfo();

			System.out.println("National Parks: ");
			park.getParksNames();

			for (Parks p : park.getParksNames()) {
				System.out.println(p.getParkId() + " " + p.getName());
			}
			System.out.println("");

			userInputParkId = menue.getParkIdFromUser();
			System.out.println(park.getParkInfoById(Long.parseLong(userInputParkId)).getName() + " National Park");
			System.out.println("Location: " + park.getParkInfoById(Long.parseLong(userInputParkId)).getLocation());
			System.out.println(
					"Established " + park.getParkInfoById(Long.parseLong(userInputParkId)).getEstabishedDate());
			System.out.println("Area: " + park.getParkInfoById(Long.parseLong(userInputParkId)).getArea());
			System.out
					.println("Annual Visitors " + park.getParkInfoById(Long.parseLong(userInputParkId)).getVisitors());
			System.out.println("");
			System.out.println(park.getParkInfoById(Long.parseLong(userInputParkId)).getDescription());
			System.out.println("");

			// Ask user next step
			String userInputCommand = menue.getSecondChoiceFromUser();

			while (true) {

				// print all campgrounds info in Park
				if (userInputCommand.contentEquals("1")) {

					camp.getAllCampgroundsInPark(Long.parseLong(userInputParkId));

					System.out.println("     Name " + "  Open " + "Close" + " Daily Fee");
					for (Campground c : camp.getAllCampgroundsInPark(Long.parseLong(userInputParkId))) {
						System.out.println("#" + c.getCampgroundId() + " " + c.getCampgroundName() + " "
								+ c.getOpenFrom() + " " + c.getOpenTo() + "    " + c.getDailyFee());
					}
					System.out.println("");

					// reservations
					String userChoice = menue.getUserChoiceForReservation();

					if (userChoice.contentEquals("1")) {
						findAvailableReservations();

						// return to previous screen
					} else if (userChoice.contentEquals("2")) {
						break;
					}

				} else if (userInputCommand.contentEquals("2")) {

					findAvailableReservations();

					// return to previous screen
				} else {
					 break;

					// DON'T KNOW HOW ASK USER CHOICE HERE
				}
			}
		}
	}
	// }

	public void getParkInfo() {
		System.out.println("National Parks: ");
		park.getParksNames();

		for (Parks p : park.getParksNames()) {
			System.out.println(p.getParkId() + " " + p.getName());
		}
		System.out.println("");

		userInputParkId = menue.getParkIdFromUser();
		System.out.println(park.getParkInfoById(Long.parseLong(userInputParkId)).getName() + " National Park");
		System.out.println("Location: " + park.getParkInfoById(Long.parseLong(userInputParkId)).getLocation());
		System.out.println("Established " + park.getParkInfoById(Long.parseLong(userInputParkId)).getEstabishedDate());
		System.out.println("Area: " + park.getParkInfoById(Long.parseLong(userInputParkId)).getArea());
		System.out.println("Annual Visitors " + park.getParkInfoById(Long.parseLong(userInputParkId)).getVisitors());
		System.out.println("");
		System.out.println(park.getParkInfoById(Long.parseLong(userInputParkId)).getDescription());
		System.out.println("");

	}

	public void getAllCampgroundsInPark() {

		camp.getAllCampgroundsInPark(Long.parseLong(userInputParkId));

		System.out.println("     Name " + "  Open " + "Close" + " Daily Fee");
		for (Campground c : camp.getAllCampgroundsInPark(Long.parseLong(userInputParkId))) {
			System.out.println("#" + c.getCampgroundId() + " " + c.getCampgroundName() + " " + c.getOpenFrom() + " "
					+ c.getOpenTo() + "    " + c.getDailyFee());
		}
		System.out.println("");

		// reservations
		String userChoice = menue.getUserChoiceForReservation();

		if (userChoice.contentEquals("1")) {
			findAvailableReservations();

			// return to previous screen
		} else if (userChoice.contentEquals("2")) {
			// break;
		}

	}

	public void findAvailableReservations() {

		String userInputCampId = menue.getCampgroundIdFromUser();
		String arrDate = menue.getArrivalDateFromUser();
		String deptDate = menue.getDepartureDateFromUser();

		// parsing user Input
		Long campgroundId = Long.parseLong(userInputCampId);
		LocalDate fromDate = LocalDate.parse(arrDate);
		LocalDate toDate = LocalDate.parse(deptDate);
		;

		// LocalDate fromDate = LocalDate.of(2020, 03, 05);
		// LocalDate toDate = LocalDate.of(2020, 03, 18);

		for (Site s : res.getAvailableReservaonsOnGivenSite(campgroundId, fromDate, toDate)) {
			System.out.println(s.getSiteNumber() + "  " + s.getMaxOccupancy() + "  " + s.isAccessible() + " "
					+ s.getMaxRvLength() + "  " + s.isUtilities() + " $" + s.getCost());

		}
	}

	public void reserveSite() {
		String userInputSiteId = menue.getSiteIdFromUser();
		Long siteId = Long.parseLong(userInputSiteId);
	}

	/*
	 * public void getAllCampgroundsInPark() {
	 * System.out.println(camp.getAllCampgroundsInPark((long)
	 * 1).get(0).getCampgroundId());
	 * 
	 * }
	 */

	public void printAllCampgroundsInParkById() {
		System.out.println(camp.getAllCampgroundsInPark(Long.valueOf(1)).size());
	}

	public void printAllReservationsBySiteId() {
		System.out.println(res.getReservatinsBySiteId((Long.valueOf(24))).size());
	}

	public void findReservationById() {
		System.out.println(res.findReservationById((long) 44).getNameOfPerson());
	}

	public void findReservationByName() {
		String nameOfPerson = "Bill";
		for (Reservation r : res.findReservationByName(nameOfPerson)) {
			System.out.println(r.getReservationId());

		}
	}

	// System.out.println(res.findReservationByName(nameOfPerson).get(0).getReservationId());

	public void findCampgrundByNAme() {
		String campgroundName = "";

		if (campgroundName != null && campgroundName == "Seawall") {

			System.out.println(camp.searchCampgroundByName(campgroundName));
		}
	}
}
