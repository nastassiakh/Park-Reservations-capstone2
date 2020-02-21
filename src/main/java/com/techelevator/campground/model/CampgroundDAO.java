package com.techelevator.campground.model;

import java.util.List;

public interface CampgroundDAO {

    //Get all campgrounds that are in the park with the {@code id}.
	public List<Campground> getAllCampgroundsInPark(Long parkId);
	
	
	
	/*Find all the campgrounds which names match the search strings. If a search string is blank,
	 * ignore it. Be sure to use LIKE or ILIKE for proper search matching!*/
	
	public List<Campground> searchCampgroundByName(String campgroundName);
	


}
