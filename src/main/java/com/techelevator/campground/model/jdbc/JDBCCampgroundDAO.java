package com.techelevator.campground.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;

public class JDBCCampgroundDAO implements CampgroundDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Campground> getAllCampgroundsInPark(Long parkId) {

		List<Campground> allCampgroundsList = new ArrayList<>();
		String sqlGetCampgroundsInPark = "SELECT * FROM campground WHERE campground_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCampgroundsInPark, parkId);
		while (results.next()) {
			Campground foundCampground = mapRowToCampground(results);
			allCampgroundsList.add(foundCampground);
		}

		return allCampgroundsList;
	}

	@Override
	public List<Campground> searchCampgroundByName(String campgroundName) {

		List<Campground> campListByName = new ArrayList<Campground>();

		String sqlFindCampByName = "SELECT * FROM campground WHERE name ILIKE ?";
		// string that takes all columns from department table
		campgroundName = "%" + campgroundName + "%";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindCampByName, campgroundName); // template

		while (results.next()) {
			Campground campground = new Campground();
			campground = mapRowToCampground(results);
			campListByName.add(campground);
		}
		return campListByName;
	}

	@Override
	public List<Campground> searchCampgroundsOpenInRange(String openFrom, String openTo) {
		
		List<Campground> openCampsList = new ArrayList<Campground>();
		
		String sqlFindOpenCamps = "SELECT * FROM campground WHERE  "
		
		return openCampsList;
	}

	private Campground mapRowToCampground(SqlRowSet results) {
		Campground campgroundtInMapRow = new Campground();
		campgroundtInMapRow.setCampgroundId(results.getLong("campground_id"));
		campgroundtInMapRow.setParkId(results.getLong("park_id"));
		campgroundtInMapRow.setCampgroundName(results.getString("name"));
		campgroundtInMapRow.setOpenFrom(results.getString("open_from_mm"));
		campgroundtInMapRow.setOpenTo(results.getString("open_to_mm"));
		campgroundtInMapRow.setDailyFee(results.getDouble("daily_fee"));

		return campgroundtInMapRow;
	}

}
