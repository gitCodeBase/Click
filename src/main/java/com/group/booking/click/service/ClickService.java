package com.group.booking.click.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.group.booking.click.business.AvailabilityProcessor;
import com.group.booking.click.business.ItemProcessor;
import com.group.booking.click.model.AvailabilityDetails;
import com.group.booking.click.model.BookingDetails;
import com.group.booking.click.model.ItemDetails;

@RestController
@RequestMapping("/api")
public class ClickService {
 
    public static final Logger logger = LoggerFactory.getLogger(ClickService.class);
 
    @Autowired
    AvailabilityProcessor avaliabilityProcessor; //Service which will do all data retrieval/manipulation work
    @Autowired
    ItemProcessor itemProcessor;
    @Autowired
    AvailabilityProcessor availabilityProcessor;
 
    /*---------------------------- Availability Details --------------------------------------------------------------------*/
    
    /**
     * Method to retrieve Item availability details based on Item Id, from and todate
     * @param itemId
     * @param fromDate
     * @param toDate
     * @return
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/avail/fetch", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AvailabilityDetails>> retrieveAvailability(@RequestBody AvailabilityDetails availObj) {
    	
    	List<AvailabilityDetails> availabilityDetailsList = avaliabilityProcessor.fetchAvailabilityBasedOn(availObj);
        return new ResponseEntity((List<AvailabilityDetails>) availabilityDetailsList, HttpStatus.OK);
    }
    
    /**
     * Method to insert the availability details for a month
     * @param AvailabilityDetails
     * @return
     * @throws JsonProcessingException
     * @throws ParseException
     */
    @RequestMapping(value = "/avail/insert", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDetails> insertAvailabilityDetails(@RequestBody AvailabilityDetails availabilityDetails) throws JsonProcessingException, ParseException {
       
    	availabilityProcessor.insertAvailability(availabilityDetails);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }
  
    /**
     * Method to update availability based on new booking
     * @param availabilityDetails
     * @return
     * @throws JsonProcessingException
     * @throws ParseException
     */
    @RequestMapping(value = "/avail/update", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDetails> updateAvailabilityDetails(@RequestBody AvailabilityDetails availabilityDetails) throws JsonProcessingException, ParseException {
       
    	availabilityProcessor.updateAvailability(availabilityDetails);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }
    
    
    
    
    
    /*---------------------------- Item Details --------------------------------------------------------------------*/
 
    /**
     * Method to fetch all items based on type
     * @param type
     * @return
     * @throws CustomException
     */
    @RequestMapping(value = "/item/fetch/{type}", method = RequestMethod.GET)
    public ResponseEntity<List<ItemDetails>> getItems(@PathVariable("type") String type) throws CustomException {
     
    	if(true){
    		throw new CustomException();
    	} else {
    		List<ItemDetails> itemDetailsList = itemProcessor.fetchItems(type);
            return new ResponseEntity<List<ItemDetails>>(itemDetailsList, HttpStatus.OK);
    	}
    	
    }
    
    /**
     * Method to fetch item details based on itemId
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public ResponseEntity<ItemDetails> getItemDetails(@PathVariable("itemId") String itemId) {
     
    	ItemDetails itemDetails = itemProcessor.retrieveItemDetails(itemId);
        return new ResponseEntity<ItemDetails>(itemDetails, HttpStatus.OK);
    }
    
    /**
     * Method to insert a new item
     * @param itemDetails
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/item/insert", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDetails> createItem(@RequestBody ItemDetails itemDetails) throws JsonProcessingException {
       
    	itemProcessor.insertItemDetails(itemDetails);
        return new ResponseEntity<ItemDetails>(itemDetails, HttpStatus.CREATED);
    }
    
    /**
     * Method to update item details
     * @param itemDetails
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/item/update", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDetails> updateItem(@RequestBody ItemDetails itemDetails) throws JsonProcessingException {
       
    	itemProcessor.insertItemDetails(itemDetails);
        return new ResponseEntity<ItemDetails>(itemDetails, HttpStatus.CREATED);
    }
    
    /*---------------------------- Booking Details --------------------------------------------------------------------*/

    @RequestMapping(value = "/item/booking", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingDetails> bookItem(@RequestBody BookingDetails bookingDetails) throws JsonProcessingException {
       
    	//itemProcessor.updateBookingDetails(bookingDetails);
        return new ResponseEntity<BookingDetails>(bookingDetails, HttpStatus.CREATED);
    }
    
  
 
    // ------------------- Update a User ------------------------------------------------
 
   /* @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
      
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
 
    // ------------------- Delete a User-----------------------------------------
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
 
    // ------------------- Delete All Users-----------------------------
 
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
       
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }*/
 
}
