package com.techelevator.campground.model;

import java.util.List;

public interface SiteDAO {
	
	
	public boolean searchHandicappedAccessible ();
	
	//this will return true if it is handicapped accessible//
	
	public boolean searchAvailableUtilities ();
	
	//this will return true if utilities searched are available on camp sites
	
	public List<Site> searchByCampgroundId();
	
	//this will return a list of camp grounds that contain the same ID searched 
	
	
	
	

	

}
