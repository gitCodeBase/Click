package com.group.booking.click.model;

import java.io.Serializable;
import java.util.Date;

public class Pricing implements Serializable{

	private String id;
	private String itemId;
	private double rate;
	private double tax;
	private double discountPercentage;
	private int minimumAmountForDiscount;
	private double serviceFee;
	private double festivalOffer;
	private Date dateFrom;
	private String lastUpdatedUserId;
	private Date lastUpdatedDate;
	
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
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public int getMinimumAmountForDiscount() {
		return minimumAmountForDiscount;
	}
	public void setMinimumAmountForDiscount(int minimumAmountForDiscount) {
		this.minimumAmountForDiscount = minimumAmountForDiscount;
	}
	public double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}
	public double getFestivalOffer() {
		return festivalOffer;
	}
	public void setFestivalOffer(double festivalOffer) {
		this.festivalOffer = festivalOffer;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getLastUpdatedUserId() {
		return lastUpdatedUserId;
	}
	public void setLastUpdatedUserId(String lastUpdatedUserId) {
		this.lastUpdatedUserId = lastUpdatedUserId;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	
	
}
