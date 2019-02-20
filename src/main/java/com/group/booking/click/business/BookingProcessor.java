package com.group.booking.click.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.group.booking.click.dao.impl.ClickRepositoryImpl;
import com.group.booking.click.model.AvailabilityDetails;
import com.group.booking.click.model.BookingDetails;

public class BookingProcessor {

	@Autowired
	private ClickRepositoryImpl itemDetailsDao;
	
	public void updateBookingDetails(BookingDetails bookingDetails){
		
		AvailabilityDetails availabilityDetails = new AvailabilityDetails();
		/*availabilityDetails.setItemId(itemId);
		availabilityDetails.setFromDate(fromDate);
		availabilityDetails.setToDate(toDate);*/
		itemDetailsDao.fetchAvailability(availabilityDetails);
		
		itemDetailsDao.updateBookingDetails(bookingDetails);
	}
}
