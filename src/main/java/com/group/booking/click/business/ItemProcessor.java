package com.group.booking.click.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.group.booking.click.dao.impl.ClickRepositoryImpl;
import com.group.booking.click.model.ItemDetails;

@Component
public class ItemProcessor {

	@Autowired
	private ClickRepositoryImpl itemDetailsDao;
	
	public ItemDetails retrieveItemDetails(String itemId){
		
		//call the dao for fetching the item details
		ItemDetails itemDetails = new ItemDetails();
		if(!StringUtils.isEmpty(itemId)) {
			itemDetails = itemDetailsDao.fetchItemDetails(itemId);
		}
		return itemDetails;
	}
	
	public List<ItemDetails> fetchItems(String type){
		
		List<ItemDetails> itemDetailsList = new ArrayList<ItemDetails>();
		if(!StringUtils.isEmpty(type)) {
			itemDetailsList = itemDetailsDao.fetchItems(type);
		}
		return itemDetailsList;
	}
	
	public void insertItemDetails(ItemDetails itemDetails) throws JsonProcessingException{
		itemDetailsDao.insertItemDetails(itemDetails);
	}
}
