package com.group.booking.click.dao;

import java.util.List;

import com.group.booking.click.model.Item;
import com.group.booking.click.model.ItemSearchCriteria;

public interface ItemDao {

	public List<Item> fetchItems(Item item);
	
	public List<Item> filteredItems(ItemSearchCriteria itemSearchCriteria); 
	
	public Item fetchItemDetails(String itemId);
	 
}
