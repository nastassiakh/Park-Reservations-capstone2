package com.techelevator.campground.model;

import java.time.LocalDate;
import java.util.List;

public interface SiteDAO {
	
	
	public  List<Site> searchHandicappedAccessible ();
	
	//this will return a list of handicapped accessible sites if true//
	
	public List <Site> searchAvailableUtilities (boolean availableUtilities);
	
	//this will return a list of sites availble by utilities searched are available on camp sites
	
	public List<Site >searchByRvLength (long maxLength);
	
	//this will return a list of sites that an accommodate an rv based off length
	
	public List<Site> searchByMaxOccupancy (long maxOccupancy);
	
	//this will return a list of of camp sites where max occupancy is
	//compared to the amount entered by the user to check and see if 
	//they are able to accommodate the visitors 
	


	

}
