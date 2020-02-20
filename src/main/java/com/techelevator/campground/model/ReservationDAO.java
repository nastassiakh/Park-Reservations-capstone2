package com.techelevator.campground.model;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDAO {
	
	/* Find all reservations where site id = {@code id} */
	
	public List<Reservation> getReservatinsBySiteId(Long siteId);
 
	//find reservation  by given id
	public List<Reservation> getReservationById(Long reservationId);
	
	
	/*find reservation  by given name If a search string is blank,
	 * ignore it. Be sure to use LIKE or ILIKE for proper search matching!
	 */
	public List<Reservation> getReservationById(String nameOfPerson);
	
	
	
	/* find reservations by given campgrondId WHERE fromDate  */
	public List<Reservation> getAvailableReservaonsOnGivenSite(Long siteId, LocalDate fromDate, LocalDate toDate);
	
	
	
		
	

}
