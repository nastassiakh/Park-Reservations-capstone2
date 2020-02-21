package com.techelevator.campground.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.SiteDAO;

public class JDBCSiteDAO implements SiteDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Site> searchHandicappedAccessible() {
		ArrayList<Site> myHandicappedSites = new ArrayList<>();
		String sqlFindHandicappedAccessibleSites = "SELECT * FROM site WHERE accesible = true";

		/*
		 * String sqlFindHandicappedAccessibleSites = "SELECT * FROM site"; if
		 * (isAccessible) { sqlFindHandicappedAccessibleSites +=
		 * "WHERE isAccesible = true"; }
		 */

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindHandicappedAccessibleSites);
		while (results.next()) {
			Site allAssessible = mapRowToSite(results);
			myHandicappedSites.add(allAssessible);
		}
		return myHandicappedSites;
	}

	@Override
	public List<Site> searchAvailableUtilities() {
		ArrayList<Site> myUtilities = new ArrayList<>();
		String sqlFindSitesWithWantedUtilities = "SELECT * FROM site WHERE utilities = true";
//		if (availableUtilities) {
//			sqlFindSitesWithWantedUtilities += "WHERE availableUtilities = true";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindSitesWithWantedUtilities);
		Site availbleSitesWithUtilities = mapRowToSite(results);
		myUtilities.add(availbleSitesWithUtilities);

		return myUtilities;
	}

	@Override
	public List<Site> searchByRvLength(long maxLength) {
		ArrayList<Site> checkRvLength = new ArrayList<>();
		String sqlFindForMyRv = "SELECT * FROM site WHERE max_rv_length >= ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindForMyRv);
		Site availableSitesFOrMyRv = mapRowToSite(results);
		checkRvLength.add(availableSitesFOrMyRv);

		return checkRvLength;
	}

	@Override
	public List<Site> searchByMaxOccupancy(long maxOccupancy) {
		ArrayList<Site> checkMaxOccupancy = new ArrayList<>();
		String sqlFindSiteByMaxOccupancy = "SELECT * FROM site WHERE max_occupancy = ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindSiteByMaxOccupancy);
		Site availbleSitesByMaxOccupancy = mapRowToSite(results);
		checkMaxOccupancy.add(availbleSitesByMaxOccupancy);

		return checkMaxOccupancy;

	}

	private Site mapRowToSite(SqlRowSet results) {
		Site allSites = new Site();
		allSites.setAccessible(results.getBoolean("accessible"));
		allSites.setUtilities(results.getBoolean("utilities"));
		allSites.setMaxRvLength(results.getLong("max_rv_length"));
		allSites.setMaxOccupancy(results.getLong("max_occupancy"));

		return allSites;

	}
}
