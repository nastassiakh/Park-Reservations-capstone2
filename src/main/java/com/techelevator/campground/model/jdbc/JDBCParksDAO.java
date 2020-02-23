package com.techelevator.campground.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.ParkDAO;
import com.techelevator.campground.model.Parks;
import com.techelevator.campground.model.Reservation;

public class JDBCParksDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCParksDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	//this will return a list of parks and order it in alphabetical order. 
	
	@Override
	public List<Parks> getParksNames() {
		ArrayList<Parks> parksNamesList = new ArrayList<>();
		String parksNames = "SELECT * FROM park ORDER BY name ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(parksNames);
		while (results.next()) {
			Parks allParks = mapRowToParks(results);
			parksNamesList.add(allParks);
		}
		return parksNamesList;
	}

	//this method will allow us to get infomration on the by selecting 
	//the corresponding parkId 
	
	@Override
	public Parks getParkInfoById(long parkId) {
		
		Parks thePark = null;
		String sqlParkInfo = "SELECT * FROM park WHERE park_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlParkInfo, parkId);
		while
			(results.next()) {
			thePark = mapRowToParks(results);
		}
		return thePark;
		
		
	}
	
	//this will return a list of parks given a search be name, 
	//we used thew wild card because you  might want the park to contain a certian word 
	//while searching for specific parks. 

	@Override
	public List<Parks> searchParksByName(String nameSearch) {
		ArrayList<Parks> searches = new ArrayList<>();
		String sqlsearchParks = "SELECT * FROM Park WHERE name ILIKE ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlsearchParks, '%' + nameSearch + '%');
		while (results.next()) {
			Parks parkSearches = mapRowToParks(results);
			searches.add(parkSearches);
		}

		return searches;

	}
	
	//this will return a list of parks by location 

	@Override
	public List<Parks> searchParksByLocation(String locationSearch) {

		ArrayList<Parks> searches = new ArrayList<>();
		String sqlsearchLocations = "SELECT * FROM Park WHERE location ILIKE ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlsearchLocations, +'%' + locationSearch + '%');
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
		allParks.setDescription(results.getString("description"));
		allParks.setVisitors(results.getLong("visitors"));
		allParks.setArea(results.getLong("area"));
		allParks.setEstabishedDate(results.getDate("establish_date"));

		return allParks;

	}

}
