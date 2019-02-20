package com.group.booking.click.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ItemDetails")
public class ItemDetails implements Serializable{

	@Id
	private String _id;
	private String name;
	private String type;
	private String address;
	private FilterDetails filter;	// this can be appended with any filter that is required
	private double price;
	private int priorityItem;	//to set the order in which this item to be shown in the main page
	private List<String> imageDetails; //for storing image/ video urls
	private String lastUpdatedUserId;
	private String lastUpdatedDate;
	
	@Transient
	private List<AvailDates> bookingDates;
	
	
	
	public List<String> getImageDetails() {
		return imageDetails;
	}
	public void setImageDetails(List<String> imageDetails) {
		this.imageDetails = imageDetails;
	}
	@Transient
	public List<AvailDates> getBookingDates() {
		return bookingDates;
	}
	@Transient
	public void setBookingDates(List<AvailDates> bookingDates) {
		this.bookingDates = bookingDates;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public FilterDetails getFilter() {
		return filter;
	}
	public void setFilter(FilterDetails filter) {
		this.filter = filter;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getPriorityItem() {
		return priorityItem;
	}
	public void setPriorityItem(int priorityItem) {
		this.priorityItem = priorityItem;
	}
	public String getLastUpdatedUserId() {
		return lastUpdatedUserId;
	}
	public void setLastUpdatedUserId(String lastUpdatedUserId) {
		this.lastUpdatedUserId = lastUpdatedUserId;
	}
	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
}
