package com.techelevator.campground.model;

import java.util.List;

public interface CampgroundDAO {

    //Get all campgrounds that are in the park with the {@code id}.
	public List<Campground> getAllCampgroundsInPark(Long parkId);
	
	
	/*Find all the campgrounds which names match the search strings. If a search string is blank,
	 * ignore it. Be sure to use LIKE or ILIKE for proper search matching!*/
	
	public List<Campground> searchCampgroundByName(String campgroundName);
	
	
	/*Find all the campgrounds  which open in beetween given dates. 
	 * If openFrom is <= givenDate1 and openTo >= givenDate2 */
	
	public List<Campground> searchCampgroundsOpenInRange(String openFrom, String openTo);
	
	
	
	/*Find all the campgrounds where givenDouble contains the searchDouble */
	//public List<Campground> searchAllCampgroundsInParkByFee(double dailyFee);

}
