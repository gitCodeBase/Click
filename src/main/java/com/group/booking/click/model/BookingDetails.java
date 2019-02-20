package com.group.booking.click.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Transient;

public class BookingDetails implements Serializable{

	private String id;
	private String itemId;
	private String userId;
	private List<AvailDates> dates;
	private double totalAmount;
	private double taxAmount;
	private double amountPaid;
	private String lastUpdateUserId;
	private Date lastUpdateDate;
	@Transient
	private List<AvailDates> bookingDates;
	
	public List<AvailDates> getDates() {
		return dates;
	}
	@Transient
	public void setDates(List<AvailDates> dates) {
		this.dates = dates;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<AvailDates> getBookingDates() {
		return bookingDates;
	}
	public void setBookingDates(List<AvailDates> bookingDates) {
		this.bookingDates = bookingDates;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	public void setLastUpdateUserId(String lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	
	
}
