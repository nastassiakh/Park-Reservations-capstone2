package com.techelevator.campground.model;

import java.sql.Date;

public class Parks {
	private long parkId;
	private String name;
	private String location;
	private Date estabishedDate;
	private long area;
	private long visitors;
	private String description;

	public long getParkId() {
		return parkId;
	}

	public void setParkId(long parkId) {
		this.parkId = parkId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getEstabishedDate() {
		return estabishedDate;
	}

	public void setEstabishedDate(Date estabishedDate) {
		this.estabishedDate = estabishedDate;
	}

	public long getArea() {
		return area;
	}

	public void setArea(long area) {
		this.area = area;
	}

	public long getVisitors() {
		return visitors;
	}

	public void setVisitors(long visitors) {
		this.visitors = visitors;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
