package com.group.booking.click.model;

import java.io.Serializable;
import java.util.List;

public class AvailDates implements Serializable{

	private int year;
	private List<MonthDay> monthDays;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public List<MonthDay> getMonthDays() {
		return monthDays;
	}
	public void setMonthDays(List<MonthDay> monthDays) {
		this.monthDays = monthDays;
	}
	
}