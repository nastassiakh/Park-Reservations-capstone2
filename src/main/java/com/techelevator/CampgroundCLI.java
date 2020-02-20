package com.techelevator;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.jdbc.JDBCCampgroundDAO;

public class CampgroundCLI {

	
		CampgroundDAO c;

		
		
		
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
		
	}

	public void run() {
		System.out.println(c.getAllCampgroundsInPark(Long.valueOf(1)).size());
		System.out.println("$"+c.getAllCampgroundsInPark((long) 1).get(0).getDailyFee()+"0");
	}
}
