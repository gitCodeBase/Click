package com.group.booking.click.business;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.group.booking.click.dao.UserDetailsDao;
import com.group.booking.click.dao.impl.BookingRepositoryImpl;
import com.group.booking.click.model.Booking;
import com.group.booking.click.model.Item;
import com.group.booking.click.model.Notification;
import com.group.booking.click.utility.BookingInvoice;
import com.itextpdf.text.DocumentException;

@Component
public class BookingProcessor {

	@Autowired
	private BookingRepositoryImpl bookingDao;
	@Autowired
	private BookingMailProcessor mailProcessor;
	@Autowired
	private BookingInvoice bookingInvoice;
	@Autowired
	private UserDetailsDao userDetailsDao;
	
	
	/**
	 * Method to fetch availability based on the parameter passed
	 * @param String - itemId
	 * @param String - fromDate
	 * @param String - toDate
	 * @return
	 */
	public List<Booking> fetchAvailabilityBasedOn(Booking bookingObj) {
		return bookingDao.fetchAvailabilityBasedOn(bookingObj);
	}
	
	public boolean confirmItemAvailability(Booking bookingObj) throws Exception {
		
		if(bookingObj != null && bookingObj.getBookingFromDate() != null && bookingObj.getBookingToDate() != null) {
			if(bookingObj.getBookingFromDate().after(bookingObj.getBookingToDate())) {
				throw new Exception("From date cannot be greater than To Date");
			}
		} else {
			throw new Exception("From Date or To Date cannot be null or empty");
		}
		return bookingDao.confirmItemAvailability(bookingObj);
	}
	
	/**
	 * Method to insert availability details - Limited to insert a whole month.
	 * @param AvailabilityDetails
	 * @return
	 * @throws DocumentException 
	 * @throws FileNotFoundException 
	 * @throws JsonProcessingException
	 * @throws ParseException 
	 */
	public String createBooking(Booking bookingObj) throws FileNotFoundException, DocumentException {
		  
		bookingObj.setCreatedBy(bookingObj.getUserId());
		bookingObj.setCreatedOn(new Date());
		bookingObj.setStatus("Confirmed");
		bookingObj.getDetails().setQtyAvailable(bookingObj.getDetails().getQtyAvailable() - 1);
		
		if(!StringUtils.isEmpty(bookingObj.getDetails().getBookedThrough()) && bookingObj.getDetails().getBookedThrough().equals("Direct")) {
			String userId = userDetailsDao.createUser(bookingObj.getDetails().getUserDetails());
			bookingObj.getDetails().setUserDetails(null);
			bookingObj.setUserId(userId);
		}
		
		String responseMsg;
		responseMsg = bookingDao.createBooking(bookingObj);
		try {
			bookingInvoice.generateBookingInvoice();
			mailProcessor.sendMail("", "");
		} catch (Exception e) {
			System.out.println("Exception while generating Invoice");
		}
		return responseMsg;
	}
	
	public void updateBooking(Booking bookingObj) throws JsonProcessingException {
		bookingDao.updateBookingDetails(bookingObj);
	}
	
	 public List<Booking> retrieveBookedDetails(String vendorId) {
		return bookingDao.retrieveBookedDetails(vendorId);
	 }
	 
	 public List<Booking> retrieveUserBookedDetails(String userId) {
		 return bookingDao.retrieveUserBookedDetails(userId);
	 }
	 
	 public Booking retrieveBookedDetailsBasedOn(String bookedId) {
		 return bookingDao.retrieveBookedDetailsBasedOn(bookedId);
	 }
	 
	 public Notification getNotificationCountForVendor(String vendorId) {
		 int notificationCount = bookingDao.getNotificationCountForVendor(vendorId);
		 Notification obj = new Notification();
		 obj.setNotificationCount(notificationCount);
	    	
		 return obj;
	 }
	 
	 public List<Notification> getNotificationForVendor(String vendorId) {
		 return bookingDao.getNotificationForVendor(vendorId);
	 }
	
	
}
