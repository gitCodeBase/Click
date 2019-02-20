package com.group.booking.click.model;

import java.io.Serializable;

public class FilterDetails implements Serializable {

	private String place;
	private String state;
	private String city;
	private int zip;
	private String[] contactNumber; //Can have multiple numbers to contact
	private String[] mailId; 
	private String webAddress;
	private String status;		//current status - whether active or deactive. An item can be made deactive for a certain period.

	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	
	
}
