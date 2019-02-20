package com.group.booking.click.model;

import java.util.Date;

public class UserDetails {

	private String userId;
	private String name;
	private String address;
	private String contactNbr;
	private String password;
	private String securityQues1;
	private String securityQues2;
	private String securityAns1;
	private String securityAns2;
	private String email;
	private Date dob;
	private Date lastUpdatedDate;
		
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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
	public String getContactNbr() {
		return contactNbr;
	}
	public void setContactNbr(String contactNbr) {
		this.contactNbr = contactNbr;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSecurityQues1() {
		return securityQues1;
	}
	public void setSecurityQues1(String securityQues1) {
		this.securityQues1 = securityQues1;
	}
	public String getSecurityQues2() {
		return securityQues2;
	}
	public void setSecurityQues2(String securityQues2) {
		this.securityQues2 = securityQues2;
	}
	public String getSecurityAns1() {
		return securityAns1;
	}
	public void setSecurityAns1(String securityAns1) {
		this.securityAns1 = securityAns1;
	}
	public String getSecurityAns2() {
		return securityAns2;
	}
	public void setSecurityAns2(String securityAns2) {
		this.securityAns2 = securityAns2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	
}
