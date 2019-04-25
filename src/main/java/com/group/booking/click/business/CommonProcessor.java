package com.group.booking.click.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.group.booking.click.dao.impl.CommonRepositoryImpl;
import com.group.booking.click.model.City;
import com.group.booking.click.model.ItemType;
import com.group.booking.click.model.Places;
import com.group.booking.click.model.States;

@Component
public class CommonProcessor {

	@Autowired
	private CommonRepositoryImpl commonRepositoryImpl;
	
	public List<States> retrieveStates() {
		List<States> statesList = commonRepositoryImpl.retrieveStates();
		
		return statesList;
	}
	
	public List<City> retrieveCities(String stateId) {
		List<City> cityList = commonRepositoryImpl.retrieveCities(stateId);
		
		return cityList;
	}
	
	public List<Places> retrievePlaces(String cityId) {
		List<Places> placeList = commonRepositoryImpl.retrievePlaces(cityId);
		
		return placeList;
	}
	
	public List<ItemType> retrieveTypes() {
		List<ItemType> typeList = commonRepositoryImpl.retrieveTypes();
		
		return typeList;
	}
}
