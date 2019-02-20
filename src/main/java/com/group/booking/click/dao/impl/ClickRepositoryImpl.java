package com.group.booking.click.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.booking.click.dao.AvailDao;
import com.group.booking.click.dao.BookingDao;
import com.group.booking.click.dao.ItemDao;
import com.group.booking.click.model.AvailabilityDetails;
import com.group.booking.click.model.BookingDetails;
import com.group.booking.click.model.ItemDetails;
import com.group.booking.click.utility.Helper;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Component
public class ClickRepositoryImpl implements AvailDao, ItemDao, BookingDao {


	 @Autowired
	 MongoTemplate mongoTemplate;

	 @Override
	 public List<AvailabilityDetails> fetchAvailability(AvailabilityDetails availabilityDetails){
		 ConnectionImpl clickDao = ConnectionImpl.getInstance();
		 DB db = clickDao.getDb();
		 DBCollection collection = db.getCollection("AvailDetails");
		
		 DBObject condition = new BasicDBObject();
		 BasicDBList values = new BasicDBList();
	
		 if(availabilityDetails.getItemId() != null){
			 values.add(new BasicDBObject("itemId", availabilityDetails.getItemId()));
		 }
		 if(availabilityDetails.getFromDate() != null && availabilityDetails.getToDate() != null) {
			 String[] splittedFromDates = Helper.splitDate(availabilityDetails.getFromDate());
			 String[] splittedToDates = Helper.splitDate(availabilityDetails.getToDate());
			 if(availabilityDetails.getItemId() != null) {
				 condition.put("$and", values);
			 }
			 values.add(new BasicDBObject("availDates.year", new BasicDBObject("$eq", Integer.valueOf(splittedFromDates[2]))
			 	.append("$lte", Integer.valueOf(splittedToDates[2]))));
				condition.put("$and", values);
				values.add(new BasicDBObject("availDates.monthDays.month", new BasicDBObject("$gte", Integer.valueOf(splittedFromDates[0]))
					.append("$lte", Integer.valueOf(splittedToDates[0]))));
				condition.put("$and", values);
				values.add(new BasicDBObject("availDates.monthDays.days", new BasicDBObject("$gte", Integer.valueOf(splittedFromDates[1]))
					.append("$lte", Integer.valueOf(splittedToDates[1]))));
				condition.put("$and", values);
		 }
		// /result stored in cursor using find() method
		DBCursor cursor2 = collection.find(condition);
		
		List<AvailabilityDetails> returnList = new ArrayList<AvailabilityDetails>();
		while (cursor2.hasNext()) {
		    BasicDBObject obj2 = (BasicDBObject) cursor2.next();
		    AvailabilityDetails fetchedDetails = mongoTemplate.getConverter().read(AvailabilityDetails.class, obj2);  
		    returnList.add(fetchedDetails); 
		    System.out.println(obj2.toJson().toString());
		}
		
		/*BasicDBObject query = new BasicDBObject();
		BasicDBObject field = new BasicDBObject();*/
		
		/*field.put("availableDates", 1);
		DBCursor cursor1 = collection.find(query,field);
		*/
		return returnList;
	 }
	 /**
	 * Method to insert a new item availability details and to add new availability details
	 * For eg. For a month.
	 */
	public void saveOrUpdateAvailability(AvailabilityDetails availabilityDetails) throws JsonProcessingException{
		ConnectionImpl clickDao = ConnectionImpl.getInstance();
		DB db = clickDao.getDb();
		DBCollection collection = db.getCollection("AvailDetails");
		ObjectMapper kk = new ObjectMapper();
		String hh = kk.writeValueAsString(availabilityDetails);
		
		DBObject bson = ( DBObject ) JSON.parse( hh );
		//will do both save or update. collection.insert -> only for saving....		
		collection.save(bson);
		
		System.out.println("Inserted.....");
	}
	
	//--------------------------------------------------------------------------------------------------------------------//
	
	@Override
	public List<ItemDetails> fetchItems(String type) {
		ConnectionImpl clickDao = ConnectionImpl.getInstance();
		DB db = clickDao.getDb();
		DBCollection collection = db.getCollection("ItemDetails");
		
		DBObject query = new BasicDBObject("type", new BasicDBObject("$eq", type));
		//result stored in cursor using find() method
		DBCursor cursor2 = collection.find(query);

		List<ItemDetails> returnList = new ArrayList<ItemDetails>();
		while (cursor2.hasNext()) {
		    BasicDBObject obj2 = (BasicDBObject) cursor2.next();
		    ItemDetails fetchedDetails = mongoTemplate.getConverter().read(ItemDetails.class, obj2);  
		    returnList.add(fetchedDetails); 
		    System.out.println(obj2.toJson().toString());
		}
		System.out.println("found.  1....");
		
		return returnList;
	}
	
	/**
	 * Method to insert a new item details
	 * 
	 */
	public void insertItemDetails(ItemDetails itemDetails) throws JsonProcessingException{
		ConnectionImpl clickDao = ConnectionImpl.getInstance();
		DB db = clickDao.getDb();
		DBCollection collection = db.getCollection("ItemDetails");
		ObjectMapper kk = new ObjectMapper();
		String hh = kk.writeValueAsString(itemDetails);
		
		DBObject bson = ( DBObject ) JSON.parse( hh );
		//will do both save or update. collection.insert -> only for saving....		
		collection.save(bson);
		
		System.out.println("Inserted.....");
	}
	
	
	@Override
	public List<ItemDetails> filteredItems(String type, String place,
			String zip, String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ItemDetails fetchItemDetails(String itemId) {
		
		ConnectionImpl connection = ConnectionImpl.getInstance();
		DB db = connection.getDb();
		DBCollection collection = db.getCollection("ItemDetails");
		
		DBObject query = new BasicDBObject("_id", new BasicDBObject("$eq", itemId));
		DBCursor cursor = collection.find(query);
		
		ItemDetails fetchedDetails = null;
		while (cursor.hasNext()) {
		    BasicDBObject obj2 = (BasicDBObject) cursor.next();
		    fetchedDetails = mongoTemplate.getConverter().read(ItemDetails.class, obj2);  
		    System.out.println(obj2.toJson().toString());
		}
		System.out.println("found.  1....");
		
		return fetchedDetails;
	}
	
	//------------------------------------- Booking Details -----------------------------------//
	
	@Override
	public void updateBookingDetails(BookingDetails bookingDetails) {
		
	}
	

}
