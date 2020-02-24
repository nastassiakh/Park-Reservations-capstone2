package com.techelevator.campground.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.ReservationDAO;
import com.techelevator.campground.model.Site;

public class JDBCReservation implements ReservationDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCReservation(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Reservation> getReservatinsBySiteId(Long siteId) {

		ArrayList<Reservation> allReservationsList = new ArrayList<>();
		String sqlGetReservationsInCampground = "SELECT * FROM reservation WHERE site_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetReservationsInCampground, siteId);
		while (results.next()) {
			Reservation foundReservations = mapRowToReservation(results);
			allReservationsList.add(foundReservations);
		}

		return allReservationsList;
	}

	@Override
	public Reservation findReservationById(Long reservationId) {

		Reservation theReservation = null;
		String sqlFindReservationById = "SELECT * FROM reservation WHERE reservation_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindReservationById, reservationId);
		if (results.next()) {
			theReservation = mapRowToReservation(results);
		}
		return theReservation;
	}

	@Override
	public List<Reservation> findReservationByName(String nameOfPerson) {

		List<Reservation> reservationByNameList = new ArrayList<>();
		String sqlGetReservationByName = "SELECT * FROM reservation WHERE name ILIKE ?";
		nameOfPerson = "%" + nameOfPerson + "%";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetReservationByName, nameOfPerson);
		while (results.next()) {
			Reservation foundReservations = mapRowToReservation(results);
			reservationByNameList.add(foundReservations);
		}
		return reservationByNameList;
	}

	@Override
	public List<Site> getAvailableReservaonsOnGivenSite(Long campgroundId, LocalDate fromDate, LocalDate toDate) {
		ArrayList<Site> availbleReservations = new ArrayList<>();

//we are going into our database to see if a reservation is available, if it is available we also need
//to check and see if any overlapping dates exists and if the park is actually open to take reservations 

		String sqlGetAvailbleReservations = "SELECT site_id, site_number, daily_fee " + "FROM campground "
				+ "JOIN site USING (campground_id) " + "LEFT JOIN reservation USING (site_id) "
				+ "WHERE campground_id = ? AND site_id NOT IN ( " + "SELECT site_id " + "FROM campground "
				+ "JOIN site USING (campground_id) " + "LEFT JOIN reservation USING (site_id) "
				+ "WHERE campground_id = ? AND (? " + "BETWEEN from_date AND to_date "
				+ "OR ? BETWEEN from_date AND to_date " + "OR (? < from_date AND ? > to_date))) "
				+ "GROUP BY site_id, site_number, daily_fee " + "LIMIT 5";


		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAvailbleReservations, campgroundId, campgroundId,
				fromDate, toDate, fromDate, toDate);

		while (results.next()) {
			Site allSites = mapRowToSite(results);
			availbleReservations.add(allSites);
		}

		return availbleReservations;
	}

	@Override
	public Reservation createReservation(Reservation newReservation) {
		Reservation res = new Reservation();
		String sqlCreateReservation = "INSERT INTO reservation (reservation_id, site_id ,name, from_date, to_date, create_date) Values (DEFAULT, ? ,? ,? , ?, ?)";
		
		//String sqlGetNextId = "SELECT nextval('reservation_reservation_id_seq')";
		//SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetNextId);
		//results.next(); // advances to the first row
		
		//int id = results.getInt("reservation_id"); // returns the integer value of the first column of table (i.e. index 1)

		//res.setReservationId((long)id);
		res.setSiteId(newReservation.getSiteId());
		res.setNameOfPerson(newReservation.getNameOfPerson());
		res.setFromDate(newReservation.getFromDate());
		res.setToDate(newReservation.getToDate());
		res.setCreateDate(newReservation.getCreateDate());
		
		jdbcTemplate.update(sqlCreateReservation,newReservation.getSiteId(), newReservation.getNameOfPerson(), newReservation.getFromDate(), 
				newReservation.getToDate(), newReservation.getCreateDate());
		
		//jdbcTemplate.update(sqlCreateReservation,newReservation.getReservationId(), newReservation.getSiteId(), newReservation.getNameOfPerson(), newReservation.getFromDate(), 
				//newReservation.getToDate(), newReservation.getCreateDate());
				
				return newReservation;

	}

	private Site mapRowToSite(SqlRowSet results) {
		Site allSites = new Site();

		//allSites.setAccessible(results.getBoolean("accessible"));
		//allSites.setUtilities(results.getBoolean("utilities"));
		//allSites.setMaxRvLength(results.getLong("max_rv_length"));
		//allSites.setMaxOccupancy(results.getLong("max_occupancy"));
		allSites.setSiteNumber(results.getLong("site_number"));
		allSites.setCost(results.getDouble("daily_fee"));
		allSites.setSiteId(results.getLong("site_id"));

		return allSites;

	}

	private Reservation mapRowToReservation(SqlRowSet results) {
		Reservation reservationInMapRow = new Reservation();
		reservationInMapRow.setReservationId(results.getLong("reservation_id"));
		reservationInMapRow.setSiteId(results.getLong("site_id"));
		reservationInMapRow.setNameOfPerson(results.getString("name"));
		reservationInMapRow.setFromDate(results.getDate("from_date").toLocalDate());
		reservationInMapRow.setToDate(results.getDate("to_date").toLocalDate());
		reservationInMapRow.setCreateDate(results.getDate("create_date").toLocalDate());

		return reservationInMapRow;
	}

}
