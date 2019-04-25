package com.group.booking.click.dao;

import java.util.List;

import com.group.booking.click.model.Booking;

public interface BookingDao {

	public String createBooking(Booking bookingObj);
	
	public void updateBookingDetails(Booking bookingObj);
	
	public List<Booking> fetchAvailabilityBasedOn(Booking bookingObj);
	
	public boolean confirmItemAvailability(Booking bookingObj);
}
