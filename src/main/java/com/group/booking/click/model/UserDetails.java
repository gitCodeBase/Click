package com.group.booking.click.model;

import java.io.Serializable;
import java.util.Date;

public class UserDetails implements Serializable{

	private String name;
	private String address;
	private String[] contactNbr;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String[] getContactNbr() {
		return contactNbr;
	}
	public void setContactNbr(String[] contactNbr) {
		this.contactNbr = contactNbr;
	}
	
	
}
