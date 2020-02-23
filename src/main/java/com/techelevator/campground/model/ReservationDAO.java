package com.techelevator.campground.model;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDAO {
	
	/* Find all reservations where site id = {@code id} */
	
	public List<Reservation> getReservatinsBySiteId(Long siteId);
 
	//find reservation  by given id
	public Reservation findReservationById(Long reservationId);
	
	
	/*find reservation  by given name If a search string is blank,
	 * ignore it. Be sure to use LIKE or ILIKE for proper search matching!
	 */
	public List<Reservation> findReservationByName(String nameOfPerson);
	
	
	
	/* find reservations by given campgrondId WHERE fromDate  */
	public List<Site> getAvailableReservaonsOnGivenSite(Long campgroundId, LocalDate fromDate, LocalDate toDate);
	
	
	
	/* create a new reservation */
	public Reservation createReservation (Reservation newReservation);
		
	

}
