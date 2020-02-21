package com.techelevator.campground.model;

import java.util.List;

public interface ParkDAO {

	public List<Parks> getAllParks();

	// get all parks by name sorted by alphabetical
	// return all parks as objects in a list

	public List<Parks> searchParksByName(String nameSearch);

	// get all parks that have a name that contains the search string.
	// return all matching parks as Parks objects in a list

	public List<Parks> searchParksByLocation(String locationSearch);

	// get all parks that have a location that contains the search string.
	// return all matching locations as Parks in a list.

}