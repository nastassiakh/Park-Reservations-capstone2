package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
//import org.springframework.util.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.techelevator.campground.model.ParkDAO;
import com.techelevator.campground.model.Parks;
import com.techelevator.campground.model.jdbc.JDBCParksDAO;

public class JDBCParksDAOTEST extends DAOIntegrationTest {
	public ParkDAO dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dao = new JDBCParksDAO(this.getDataSource());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testJDBCParksDAO() {
		List<Parks> p1 = new ArrayList<Parks>();
		p1.addAll(dao.getParksNames());
		
		Assert.assertTrue(p1.size() > 0);
		//assertEquals();	
		//fail("Not yet implemented");
	}

	@Test
	public void testGetParksNames() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetParkInfoById() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchParksByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchParksByLocation() {
		fail("Not yet implemented");
	}

}
