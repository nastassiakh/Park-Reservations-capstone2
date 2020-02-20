package com.techelevator.campground.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.ParkDAO;
import com.techelevator.campground.model.Parks;

public class JDBCParksDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCParksDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Parks> getAllParks() {
		ArrayList<Parks> myParks = new ArrayList<>();
		String sqlFindParks = "SELECT * FROM park";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindParks);
		while (results.next()) {
			Parks allParks = mapRowToParks(results);
			myParks.add(allParks);
		}

		return myParks;

	}

	@Override
	public List<Parks> searchParksByName(String nameSearch) {
		ArrayList<Parks> searches = new ArrayList<>();
		String sqlsearchParks = "SELECT * FROM Park WHERE name ILIKE ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlsearchParks, nameSearch);
		while (results.next()) {
			Parks parkSearches = mapRowToParks(results);
			searches.add(parkSearches);
		}

		return searches;

	}

	@Override
	public List<Parks> searchParksByLocation(String locationSearch) {

		ArrayList<Parks> searches = new ArrayList<>();
		String sqlsearchLocations = "SELECT * FROM Park WHERE location ILIKE ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlsearchLocations, locationSearch);
		while (results.next()) {
			Parks searchLocations = mapRowToParks(results);
			searches.add(searchLocations);
		}

		return searches;

	}

	private Parks mapRowToParks(SqlRowSet results) {
		Parks allParks = new Parks();
		allParks.setParkId(results.getLong("park_id"));
		allParks.setName(results.getString("name"));
		allParks.setLocation(results.getString("location"));

		return allParks;

	}

}
