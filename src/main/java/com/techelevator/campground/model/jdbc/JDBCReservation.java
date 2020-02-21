package com.techelevator.campground.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.ReservationDAO;

public class JDBCReservation implements ReservationDAO{
	
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
	public List<Reservation> getAvailableReservaonsOnGivenSite(Long siteId, LocalDate fromDate, LocalDate toDate) {
		// TODO Auto-generated method stub
		return null;
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
