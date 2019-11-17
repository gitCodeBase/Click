package com.group.booking.click.dao.impl;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.group.booking.click.dao.ItemDao;
import com.group.booking.click.model.Image;
import com.group.booking.click.model.Item;
import com.group.booking.click.model.ItemDetails;
import com.group.booking.click.model.ItemSearchCriteria;
import com.group.booking.click.utility.DBConstants;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;

@Component
public class ClickRepositoryImpl implements ItemDao {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	GridFsTemplate gridFsTemplate;

	// --------------------------------------------------------------------------------------------------------------------//

	@Override
	public List<Item> fetchItems(Item item) {

		List<Item> itemList = mongoTemplate.find(Query.query(Criteria.where(DBConstants.ITEM_TYPE).in(item.getType())
				.and(DBConstants.ITEM_PLACE_ID).is(item.getPlaceId())), Item.class);

		return itemList;
	}

	/**
	 * Method to insert a new item details
	 * 
	 */
	public void insertItemDetails(Item itemDetails) throws JsonProcessingException {

		mongoTemplate.save(itemDetails);
	}

	/**
	 * Method to update item details
	 * 
	 */
	public void updateItemDetails(Item itemDetails) throws JsonProcessingException {

		Query updateQuery = new Query();
		updateQuery.addCriteria(Criteria.where(DBConstants.ITEM_ID).is(new ObjectId(itemDetails.get_id())));

		Update update = new Update();

		update.set(DBConstants.ITEM_TYPE, itemDetails.getType());
		update.set(DBConstants.ITEM_DETAILS, itemDetails.getDetails());
		update.set(DBConstants.ITEM_PRICE, itemDetails.getPrice());
		update.set(DBConstants.ITEM_FILTER, itemDetails.getFilter());
		update.set(DBConstants.ITEM_REVIEWS, itemDetails.getReviews());
		update.set(DBConstants.ITEM_LASTUPDATEDBY, itemDetails.getLastUpdatedBy());
		update.set(DBConstants.ITEM_LASTUPDATEDON, itemDetails.getLastUpdatedOn());

		mongoTemplate.updateFirst(updateQuery, update, Item.class);
	}

	@Override
	public List<Item> filteredItems(ItemSearchCriteria itemSearchCriteria) {

		Query filterQuery = new Query();
		filterQuery
				.addCriteria(Criteria.where(DBConstants.ITEM_TYPE).in(itemSearchCriteria.getType())
						.and(DBConstants.ITEM_PLACE_ID).is(itemSearchCriteria.getPlaceId())
						.and(DBConstants.ITEM_FILTER_SEATING_CAPCITY).gte(itemSearchCriteria.getSeatingCapacity())
						.and(DBConstants.ITEM_FILTER_TEMP_CONTROL).is(itemSearchCriteria.getTempControl()))
				.with(new Sort(Sort.Direction.ASC, DBConstants.ITEM_DETAILS_AMOUNT));

		List<Item> filteredList = mongoTemplate.find(filterQuery, Item.class);
		return filteredList;
	}

	@Override
	public Item fetchItemDetails(String itemId) {

		Query filterQuery = new Query();
		filterQuery.addCriteria(Criteria.where(DBConstants.ITEM_ID).is(itemId));

		Item itemDetails = mongoTemplate.findById(itemId, Item.class);//(filterQuery, Item.class);
		return itemDetails;
	}
	
	public List<Item> getItemsBasedOnVendorId(String vendorId) {
		Query filterQuery = new Query();
		filterQuery.addCriteria(Criteria.where(DBConstants.ITEM_VENDOR_ID).is(vendorId));

		List<Item> itemDetails = mongoTemplate.find(filterQuery, Item.class);
		return itemDetails;
	}

	public GridFSDBFile retrieveImages(String fileName) {
	//	GridFSFile gridFsFile = gridFsTemplate.findOne(new Query(Criteria.where("filename").is(fileName)));
		GridFSDBFile imageFile = gridFsTemplate.findOne(new Query(Criteria.where("filename").is(fileName)));
		
	//	List<GridFSDBFile> imageFile = gridFsTemplate.find(new Query(Criteria.where("images.$.filename").regex(itemId)));
		
		return imageFile;
	}
	
	public void saveImage(InputStream targetStream, String fileName) throws FileNotFoundException {
		//  InputStream targetStream = new FileInputStream(convFile);
		gridFsTemplate.store(targetStream, fileName);

	}
	
	public void saveImageNames(Item item) {
		
		Query updateQuery = new Query();
		updateQuery.addCriteria(Criteria.where(DBConstants.ITEM_ID).is(new ObjectId(item.get_id())));
		
		Update update = new Update();
		/*ItemDetails details = new ItemDetails();
		details.setMainImageUrl(item.getDetails().getMainImageUrl());
		List<Image> images = new ArrayList<Image>();
		Image image = new Image();
		image.setFileName(item.getImages().get(0).getFileName());
		images.add(image);*/
		update.set(DBConstants.ITEM_IMAGES, item.getImages());
		update.set(DBConstants.ITEM_DETAILS, item.getDetails());
		
		mongoTemplate.updateFirst(updateQuery, update, Item.class);
	}
	
/*	public List<Images> getImageDetails(String itemId) {
		Query filterQuery = new Query();
		filterQuery.addCriteria(Criteria.where(DBConstants.ITEM_ID).is(itemId));
		
		return mongoTemplate.find(filterQuery, Images.class);
	}*/


}
