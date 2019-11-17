package com.group.booking.click.business;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.group.booking.click.dao.impl.ClickRepositoryImpl;
import com.group.booking.click.model.Image;
import com.group.booking.click.model.Item;
import com.group.booking.click.model.ItemDetails;
import com.group.booking.click.model.ItemSearchCriteria;
import com.group.booking.click.model.Pricing;
import com.mongodb.gridfs.GridFSDBFile;

@Component
public class ItemProcessor {

	@Autowired
	private ClickRepositoryImpl itemDetailsDao;
	
	/**
	 * Method to fetch Item details based on Item Id
	 * @param String -itemId
	 * @return Item
	 */
	public Item retrieveItemDetails(String itemId) {
	  
		//call the dao for fetching the item details 
		Item item = new Item();
		if(!StringUtils.isEmpty(itemId)) { 
			item = itemDetailsDao.fetchItemDetails(itemId); 
		} 
		return item; 
	}
	
	public List<Item> getItemsBasedOnVendorId(String vendorId) {
		return itemDetailsDao.getItemsBasedOnVendorId(vendorId); 
	}
	/**
	 * Method to fetch items based on Type and place
	 * @param ItemSearchCriteria
	 * @return List<Item>
	 */
	public List<Item> fetchItems(Item item){
		
		List<Item> itemDetailsList = new ArrayList<Item>();
		if(!StringUtils.isEmpty(item)) {
			itemDetailsList = itemDetailsDao.fetchItems(item);
		}
		return itemDetailsList;
	}
	/**
	 * Method to save Item Details
	 * @param Item
	 * @throws JsonProcessingException
	 */
	public void insertItemDetails(Item itemDetails) throws JsonProcessingException {
		
		itemDetails.setCreatedBy(itemDetails.getVendorId());
		itemDetails.setCreatedOn(new Date());
		itemDetails.setStatus("PENDING");
		Pricing price = itemDetails.getPrice();
		double subTotal = price.getBaseRate() + price.getTax() -price.getDiscount();
		itemDetails.getPrice().setSubTotal(subTotal);
		
		itemDetailsDao.insertItemDetails(itemDetails); 
	}
	/**
	 * Method to update Item Details
	 * @param Item
	 * @throws JsonProcessingException
	 */
	public void updateItemDetails(Item itemDetails) throws JsonProcessingException{ 
		itemDetailsDao.updateItemDetails(itemDetails); 
	}
	/**
	 * Method to filter based on the criteria
	 * @param ItemSearchCriteria
	 * @return List<Item>
	 */
	public List<Item> filteredItems(ItemSearchCriteria itemSearchCriteria){
		return itemDetailsDao.filteredItems(itemSearchCriteria);
	}
	
	public List<Item> getItemsForBookingByVendor(String vendorId) {
		return itemDetailsDao.getItemsBasedOnVendorId(vendorId);//Need to change this method to fetch only the required attributes
	}
	
	public void saveImage(InputStream targetStream, String fileName) throws FileNotFoundException {
		itemDetailsDao.saveImage(targetStream, fileName);
	}
	
	public GridFSDBFile retrieveImages(String fileName) {
		return itemDetailsDao.retrieveImages(fileName);
	}
	 
	public void saveImageNames(String itemId, String fileName, boolean isMainImage) {
		
		Item savedItem = itemDetailsDao.fetchItemDetails(itemId);
		
		Item item = new Item();
		item.set_id(itemId);
		item.setDetails(savedItem.getDetails());
		if(isMainImage) {
			ItemDetails details = savedItem.getDetails();
			details.setMainImageUrl(fileName);
			item.setDetails(details);
		}
		List<Image> imageList = savedItem.getImages() != null ? savedItem.getImages(): new ArrayList<Image>();
		Image imageDetails = new Image();
		imageDetails.setFileName(fileName);
		
		imageList.add(imageDetails);
		item.setImages(imageList);
		itemDetailsDao.saveImageNames(item);
	}
	
	/*public List<Images> getImageDetails(String itemId) {
		return itemDetailsDao.getImageDetails(itemId);
	}*/
	
}
