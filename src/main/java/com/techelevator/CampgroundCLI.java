package com.techelevator;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.ReservationDAO;
import com.techelevator.campground.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.campground.model.jdbc.JDBCReservation;

public class CampgroundCLI {

	CampgroundDAO c;
	ReservationDAO res;

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
		c = new JDBCCampgroundDAO(dataSource);
		res = new JDBCReservation(dataSource);

	}

	public void run() {
		findReservationById();
		getAllCampgroundsInPark();
		printAllReservationsBySiteId();
		findReservationByName();
	}

	
	
	
	public void getAllCampgroundsInPark() {
		System.out.println(c.getAllCampgroundsInPark((long) 1).get(0).getCampgroundId());

	}

	public void printAllCampgroundsInParkById() {
		System.out.println(c.getAllCampgroundsInPark(Long.valueOf(1)).size());
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
		//System.out.println(res.findReservationByName(nameOfPerson).get(0).getReservationId());
	}

}
