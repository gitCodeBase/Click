package com.group.booking.click.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.group.booking.click.dao.BookingDao;
import com.group.booking.click.model.Booking;
import com.group.booking.click.model.Notification;
import com.group.booking.click.utility.DBConstants;

@Repository
public class BookingRepositoryImpl implements BookingDao {

	@Autowired
	MongoTemplate mongoTemplate;
	
	/**
	 * Method to fetch the items availability based on type, date and place
	 */
	//DFDate - Db FromDate, DTDate - Db ToDate, RFDate- Requested FromDate, RTD- Requested ToDate
	//(DFDate > RFDate && DFDate > RTDate) OR (DTDate < RFDate && DTDate < RTDate)
	public List<Booking> fetchAvailabilityBasedOn(Booking bookingObj) {
		Query query = new Query();
		Criteria x = Criteria.where(
				DBConstants.BOOKING_ITEM_TYPE).in(bookingObj.getDetails().getType())
				.and(DBConstants.BOOKING_PLACE_ID).is(bookingObj.getDetails().getPlace());
		
		Criteria y = new Criteria().andOperator(Criteria.where(DBConstants.BOOKING_FROM_DATE).gt(bookingObj.getBookingFromDate()),
		        Criteria.where(DBConstants.BOOKING_FROM_DATE).gt(bookingObj.getBookingToDate()));
		
		Criteria z = new Criteria().andOperator(Criteria.where(DBConstants.BOOKING_TO_DATE).lt(bookingObj.getBookingFromDate()),
		        Criteria.where(DBConstants.BOOKING_TO_DATE).lt(bookingObj.getBookingToDate()));
		
		Criteria a = x.orOperator(y,z);
		
		query.addCriteria(a);
		
		List<Booking> bookingObjList = mongoTemplate.find(query, Booking.class);

		return bookingObjList ;
	}
	/**
	 * Method to confirm availability based on itemId and date
	 */
	//DFDate - Db FromDate, DTDate - Db ToDate, RFDate- Requested FromDate, RTD- Requested ToDate
	//(DFDate >= RFDate && DTDate <= RTDate) OR (DFDate <= RFDate && DTDate >= RFDate) OR (DFDate <= RTDate && DTDate >= RTDate)
	public boolean confirmItemAvailability(Booking bookingObj) {
		Query query = new Query();
		Criteria w = Criteria.where(
				DBConstants.BOOKING_ITEM_ID).in(bookingObj.getItemId());
		
		Criteria x = Criteria.where(DBConstants.BOOKING_FROM_DATE).gte(bookingObj.getBookingFromDate())
				.and(DBConstants.BOOKING_TO_DATE).lte(bookingObj.getBookingToDate());
		
		Criteria y = new Criteria().andOperator(Criteria.where(DBConstants.BOOKING_FROM_DATE).lte(bookingObj.getBookingFromDate()),
		        Criteria.where(DBConstants.BOOKING_TO_DATE).gte(bookingObj.getBookingFromDate()));
		
		Criteria z = new Criteria().andOperator(Criteria.where(DBConstants.BOOKING_FROM_DATE).lte(bookingObj.getBookingToDate()),
		        Criteria.where(DBConstants.BOOKING_TO_DATE).gte(bookingObj.getBookingToDate()));
		
		Criteria a = w.orOperator(x,y,z);
		query.addCriteria(a);
		
		List<Booking> bookingObjList = mongoTemplate.find(query, Booking.class);
		if(bookingObjList != null && !bookingObjList.isEmpty()) {
			return false;
		}

		return true;
	}
	
	public String createBooking(Booking bookingObj) {
		String responseMsg;
		try {
			if(confirmItemAvailability(bookingObj)) {
				mongoTemplate.save(bookingObj);
				responseMsg = "success";
			} else {
				responseMsg ="notAvailabile";
			}
		} catch(Exception e) {
			responseMsg = "Error";
			System.out.println("Exception while saving a booking");
		}
		
		return responseMsg;
	}
	
	@Override
	public void updateBookingDetails(Booking bookingObj) {
		// TODO Auto-generated method stub
		
	}
	
	 public List<Booking> retrieveBookedDetails(String vendorId) {
		Query query = new Query();
		query = Query.query(
				Criteria.where(DBConstants.BOOKING_VENDOR_ID).is( vendorId));
		query.with(new Sort(Sort.Direction.DESC, DBConstants.BOOKING_FROM_DATE));
			
		List<Booking> bookingList = mongoTemplate.find(query, Booking.class);
		 
		return bookingList;
	 }
	 
	 public List<Booking> retrieveUserBookedDetails(String userId) {
		Query query = new Query();
		query = Query.query(
				Criteria.where(DBConstants.BOOKING_USER_ID).is(userId));
		query.with(new Sort(Sort.Direction.DESC, DBConstants.BOOKING_FROM_DATE));
			
		List<Booking> bookingList = mongoTemplate.find(query, Booking.class);
		 
		return bookingList;
	 }
	 
	 public Booking retrieveBookedDetailsBasedOn(String bookedId) {
		 Query query = new Query();
		 query = Query.query(
					Criteria.where(DBConstants.BOOKING_ID).is( bookedId));
				
		List<Booking> bookingList = mongoTemplate.find(query, Booking.class);
		return bookingList.get(0);
	 }
	 
	 //---------------------------Notification 
	 
	 public int getNotificationCountForVendor(String vendorId) {
		 Query query = new Query();
		 query = Query.query(
					Criteria.where(DBConstants.NOTIFICATION_VENDOR_ID).is(vendorId).andOperator(
							Criteria.where(DBConstants.NOTIFICATION_IS_READ).is(false)));
				
		List<Notification> notificationList = mongoTemplate.find(query, Notification.class); 
		
		if(notificationList != null && !notificationList.isEmpty()) {
			return notificationList.size();
		} else {
			return 0;
		}
	 }
	 
	 public List<Notification> getNotificationForVendor(String vendorId) {
		 Query query = new Query();
		 query = Query.query(
					Criteria.where(DBConstants.NOTIFICATION_VENDOR_ID).is(vendorId));
				
		List<Notification> notificationList = mongoTemplate.find(query, Notification.class);
		
		Query updateQuery = new Query();
		updateQuery.addCriteria(Criteria.where(DBConstants.NOTIFICATION_VENDOR_ID).is(vendorId).andOperator(
				Criteria.where(DBConstants.NOTIFICATION_IS_READ).is(false)));

		Update update = new Update();
		update.set(DBConstants.NOTIFICATION_IS_READ, true);
		mongoTemplate.updateMulti(updateQuery, update, Notification.class);
		
		return notificationList;
	 }
}
