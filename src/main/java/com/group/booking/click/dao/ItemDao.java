package com.group.booking.click.dao;

import java.util.List;

import com.group.booking.click.model.ItemDetails;

public interface ItemDao {

	public List<ItemDetails> fetchItems(String type);
	public List<ItemDetails> filteredItems(String type, String place, String zip, String fromDate, String toDate);
	public ItemDetails fetchItemDetails(String itemId);
}
