package com.group.booking.click.service;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.group.booking.click.business.BookingProcessor;
import com.group.booking.click.model.Booking;
import com.group.booking.click.model.Notification;
import com.group.booking.click.model.ResponseModel;
import com.itextpdf.text.DocumentException;

@Controller
//@CrossOrigin(origins="http://localhost:4200", maxAge = 3600)
@RequestMapping("/booking")
public class BookingService {
	
	public static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	BookingProcessor bookingProcessor;
	
 /*---------------------------- Availability Details --------------------------------------------------------------------*/
    
    /**
     * Method to retrieve Item availability details based on ItemId, type and date
     * @param Booking
     * @return List<Booking>
     */
    @RequestMapping(value = "/avail/fetch", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Booking> fetchAvailabilityBasedOn(@RequestBody Booking bookingObj) {
    	
    	List<Booking> availabilityDetailsList = bookingProcessor.fetchAvailabilityBasedOn(bookingObj);
        return availabilityDetailsList;
    }
    
    /**
     * Method to confirm availability of an item based on itemId and date
     * @param Booking
     * @return Booking
     * @throws Exception 
     */
    @RequestMapping(value = "/avail/confirm", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean confirmItemAvailability(@RequestBody Booking bookingObj) throws Exception {
    	
    	boolean availabilityFlag = bookingProcessor.confirmItemAvailability(bookingObj);
        return availabilityFlag;
    }
    
    /**
     * Method to insert the availability details for a month
     * @param AvailabilityDetails
     * @return
     * @throws JsonProcessingException
     * @throws ParseException
     * @throws DocumentException 
     * @throws FileNotFoundException 
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseModel createBooking(@RequestBody Booking bookingObj) throws JsonProcessingException, ParseException, FileNotFoundException, DocumentException {
       
    	 String response = bookingProcessor.createBooking(bookingObj);
    	 ResponseModel responseModel = new ResponseModel();
    	 responseModel.setResponseMsg(response);
    	 return responseModel;
    }
  
    /**
     * Method to update availability based on cancel/refund
     * @param availabilityDetails
     * @return
     * @throws JsonProcessingException
     * @throws ParseException
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    public void updateBooking(@RequestBody Booking bookinObj) throws JsonProcessingException, ParseException {
       
    	bookingProcessor.updateBooking(bookinObj);
        
    }
    
    /**
     * Method to retrieve booked details based on vendorId
     * @param vendorId
     * @return List<Booking>
     */
    @RequestMapping(value = "/{vendorId}", produces= {"application/json"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Booking> retrieveBookedDetails(@PathVariable("vendorId") String vendorId) {
       
    	List<Booking> bookedDetails = bookingProcessor.retrieveBookedDetails(vendorId);
    	
    	return bookedDetails;
        
    }
    
    /**
     * Method to retrieve booked details based on vendorId
     * @param vendorId
     * @return List<Booking>
     */
    @RequestMapping(value = "user/{userId}", produces= {"application/json"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Booking> retrieveUserBookedDetails(@PathVariable("userId") String userId) {
       
    	List<Booking> bookedDetails = bookingProcessor.retrieveUserBookedDetails(userId);
    	
    	return bookedDetails;
        
    }
    
    /**
     * Method to retrieve booked details based on vendorId
     * @param vendorId
     * @return List<Booking>
     */
    @RequestMapping(value = "/bookedDetails/{bookedId}", produces= {"application/json"}, method = RequestMethod.GET)
    @ResponseBody
    public Booking retrieveBookedDetailsBasedOn(@PathVariable("bookedId") String bookedId) {
       
    	Booking bookedDetails = bookingProcessor.retrieveBookedDetailsBasedOn(bookedId);
    	
    	return bookedDetails;
    }
    
    @RequestMapping(value = "/notificationCount/{vendorId}", produces= {"application/json"}, method = RequestMethod.GET)
    @ResponseBody
    public Notification getNotificationCountForVendor(@PathVariable String vendorId) {
    	return bookingProcessor.getNotificationCountForVendor(vendorId);
    }
    
    /**
     * Method to retrieve booked details based on vendorId
     * @param vendorId
     * @return List<Booking>
     */
    @RequestMapping(value = "/notification/{vendorId}", produces= {"application/json"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Notification> getNotificationForVendor(@PathVariable("vendorId") String vendorId) {
       
    	List<Notification> notificationList = bookingProcessor.getNotificationForVendor(vendorId);
    	return notificationList;
    }
    
   

}
