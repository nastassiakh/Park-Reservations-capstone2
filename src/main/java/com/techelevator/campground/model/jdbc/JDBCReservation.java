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

//	String sqlGetAvailbleReservations = "SELECT site.site_id, campground.name "
//			+ "FROM campground "
//			+ "JOIN site ON campground.campground_id = site.campground_id "
//			+ "LEFT JOIN reservation ON reservation.site_id = site.site_id "
//			+ "WHERE (reservation.reservation_id IS NULL "
//			+ "AND campground.campground_id = ? "
//			+ "AND EXTRACT (MONTH FROM DATE ?) > CAST (campground.open_from_mm AS INTEGER) AND EXTRACT (MONTH FROM DATE ?) < CAST (campground.open_to_mm AS INTEGER) "
//			+ "OR (reservation.reservation_id IS NOT NULL "
//			+ "AND campground.campground_id = ? "
//			+ "AND EXTRACT (MONTH FROM DATE ?) > CAST (campground.open_from_mm AS INTEGER) AND EXTRACT (MONTH FROM DATE ?) < CAST (campground.open_to_mm AS INTEGER) "
//			+ " AND site.site_id NOT IN "
//								+ "(SELECT reservation.site_id "
//								+ " FROM campground"
//								+ "JOIN site ON campground.campground_id = site.campground_id "
//								+ "JOIN reservation ON reservation.site_id = site.site_id "
//								+ "WHERE site.campground_id = ? "
//								+ "AND (? >= reservation.from_date AND ? <= reservation.to_date)//userinput from date comparison "
//								+ "OR (? >= reservation.from_date AND ? <= reservation.to_date))//userinput to date comparison "
//								+ "ORDER BY site.site_id, campground.name "
//								+ "LIMIT 5) ";

		String sqlGetAvailbleReservations = "SELECT site_id, site_number, daily_fee "+
											"FROM campground "+
											"JOIN site USING (campground_id) "+
											"LEFT JOIN reservation USING (site_id) "+
											"WHERE campground_id = ? AND site_id NOT IN ( "+
											"SELECT site_id "+
											"FROM campground "+
											"JOIN site USING (campground_id) "+
											"LEFT JOIN reservation USING (site_id) "+
											"WHERE campground_id = ? AND (? "+
											"BETWEEN from_date AND to_date "+
											"OR ? BETWEEN from_date AND to_date "+
											"OR (? < from_date AND ? > to_date))) "+
											"GROUP BY site_id, site_number, daily_fee "+
											"LIMIT 5";
		
		
		
//		String a = "SELECT site.site_number, site.max_occupancy, site.accessible, site.max_rv_length, site.utilities, campground.daily_fee\n"
//				+ "FROM campground\n" + "JOIN site ON campground.campground_id = site.campground_id\n"
//				+ "LEFT JOIN reservation ON reservation.site_id = site.site_id\n"
//				+ "WHERE (reservation.reservation_id IS NULL\n" + "AND campground.campground_id = ?\n"
//				+ "AND EXTRACT (MONTH FROM DATE ?) > CAST (campground.open_from_mm AS INTEGER) AND EXTRACT (MONTH FROM DATE '2020-02-14') < CAST (campground.open_to_mm AS INTEGER)\n"
//				+ ")\n" + "OR (reservation.reservation_id IS NOT NULL\n" + "AND campground.campground_id = 1\n"
//				+ "AND EXTRACT (MONTH FROM DATE '2020-02-08') > CAST (campground.open_from_mm AS INTEGER) AND EXTRACT (MONTH FROM DATE '2020-02-14') < CAST (campground.open_to_mm AS INTEGER)\n"
//				+ "AND site.site_id NOT IN\n" + "(SELECT reservation.site_id\n" + "FROM campground\n"
//				+ "JOIN site ON campground.campground_id = site.campground_id\n"
//				+ "JOIN reservation ON reservation.site_id = site.site_id\n" + "WHERE site.campground_id = 1\n"
//				+ "AND ('2020/02/08' >= reservation.from_date AND '2020/02/08' <= reservation.to_date)\n"
//				+ "OR ('2020/02/14' >= reservation.from_date AND '2020/02/14' <= reservation.to_date)))\n" + "\n"
		
		
		
//				+ "order by site.site_id, campground.name LIMIT 5; ";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAvailbleReservations, campgroundId,  campgroundId, fromDate, toDate,fromDate, toDate);
																					// campgroundId, fromDate, toDate,
																					// campgroundId,fromDate, fromDate,
																					// toDate, toDate );
		while (results.next()) {
			Site allSites = mapRowToSite(results);
			availbleReservations.add(allSites);
		}

		return availbleReservations;
	}

	/*
	 * @Override public Reservation makeReservation(Reseravation object ) {
	 * Reservation res = new Reservation();
	 * 
	 * String sqlGetNextId = "SELECT nextval('seq_reservation_id')"; SqlRowSet
	 * results = jdbcTemplate.queryForRowSet(sqlGetNextId); results.next(); //
	 * advances to the first row int id = results.getInt(1); // returns the integer
	 * value of the first column of table (i.e. index 1)
	 * 
	 * 
	 * res.setId((long)id);
	 * 
	 * String sqlInsertDepartment =
	 * "INSERT INTO department(department_id, name) VALUES(?, ?)";
	 * jdbcTemplate.update(sqlInsertDepartment, department.getId(),
	 * theDepartment.getName());
	 * 
	 * return department;
	 * 
	 * }
	 */

	private Site mapRowToSite(SqlRowSet results) {
		Site allSites = new Site();

		allSites.setAccessible(results.getBoolean("accessible"));
		allSites.setUtilities(results.getBoolean("utilities"));
		allSites.setMaxRvLength(results.getLong("max_rv_length"));
		allSites.setMaxOccupancy(results.getLong("max_occupancy"));
		allSites.setSiteNumber(results.getLong("site_number"));
		allSites.setCost(results.getDouble("daily_fee"));

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
