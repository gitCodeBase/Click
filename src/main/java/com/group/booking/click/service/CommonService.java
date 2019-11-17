package com.group.booking.click.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.group.booking.click.business.CommonProcessor;
import com.group.booking.click.model.City;
import com.group.booking.click.model.ItemType;
import com.group.booking.click.model.Places;
import com.group.booking.click.model.States;

@Controller
@CrossOrigin(origins="http://localhost:8081", maxAge = 3600)
@RequestMapping("/common")
public class CommonService {
	
	public static final Logger logger = LoggerFactory.getLogger(CommonService.class);
	@Autowired
	private CommonProcessor commonProcessor;;
	

	@RequestMapping(value ="/states", produces= {"application/json"}, method = {RequestMethod.GET})
    @ResponseBody
	public List<States> retrieveStates() {
    	
    	List<States> states = commonProcessor.retrieveStates();
        return states;
    }
	
	@RequestMapping(value ="/cities/{stateId}", produces= {"application/json"}, method = {RequestMethod.GET})
    @ResponseBody
	public List<City> retrieveCities(@PathVariable("stateId") String stateId) {
    	
    	List<City> cities = commonProcessor.retrieveCities(stateId);
        return cities;
    }
	
	@RequestMapping(value ="/places/{cityId}", produces= {"application/json"}, method = {RequestMethod.GET})
    @ResponseBody
	public List<Places> retrievePlaces(@PathVariable("cityId") String cityId) {
    	
    	List<Places> placesList = commonProcessor.retrievePlaces(cityId);
        return placesList;
    }
	
	@RequestMapping(value ="/types", produces= {"application/json"}, method = {RequestMethod.GET})
    @ResponseBody
	public List<ItemType> retrieveTypes() {
    	
    	List<ItemType> typeList = commonProcessor.retrieveTypes();
        return typeList;
    }
	
	@RequestMapping(value ="/states/save", consumes=MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    @ResponseBody
	public void saveStates(@RequestBody List<States> states) {
    	
    	commonProcessor.saveStates(states);
        System.out.println("saved...");
    }
	

}
