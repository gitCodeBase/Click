package com.group.booking.click.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.group.booking.click.business.UserDetailsProcessor;
import com.group.booking.click.model.User;
import com.group.booking.click.model.Vendor;

@Controller
//@CrossOrigin(origins="http://localhost:4200", maxAge = 3600)
@RequestMapping("/user")
public class UserService {

	public static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDetailsProcessor userDetailsProcessor;
	
	
	@RequestMapping(value ="/validate", consumes= {"application/json"}, produces= {"application/json"}, method = {RequestMethod.POST})
    @ResponseBody
	public User validateUser(@RequestBody User userObj) {
    	
    	User userDetails = userDetailsProcessor.validateUserDetails(userObj);
        return userDetails;
    }
	
	@RequestMapping(value = "/retrieve", consumes= {"application/json"}, produces= {"application/json"}, method = {RequestMethod.POST})
	public User retrieveUserDetails(@RequestBody User user) {
    	
		User userDetails = userDetailsProcessor.retrieveUserDetails(user);
		return userDetails;
    }
	
	@RequestMapping(value = "/profile/{userId}", consumes= {"application/json"}, produces= {"application/json"}, method = {RequestMethod.GET})
	@ResponseBody
	public User retrieveUser(@PathVariable String userId) {
    	
		User userDetails = userDetailsProcessor.retrieveUser(userId);
		return userDetails;
    }
	
	@RequestMapping(value ="/create", consumes= {"application/json"}, method = {RequestMethod.POST})
    public void createUser(@RequestBody User user) {
		userDetailsProcessor.createUser(user);
	}
	
	@RequestMapping(value ="/update", consumes= {"application/json"}, method = {RequestMethod.PUT})
    public void updateUserDetails(@RequestBody User user) {
		
	}
	
	@RequestMapping(value ="/update/status", consumes= {"application/json"}, method = {RequestMethod.PUT})
    public void updateUserStatus(@RequestBody User user) {
	
	}
	
	//----------------vendor------------
	
	@RequestMapping(value ="/create/vendor", consumes= {"application/json"}, method = {RequestMethod.POST})
    public void createVendor(@RequestBody Vendor vendor) {
		userDetailsProcessor.createVendor(vendor);
	}
	
	@RequestMapping(value ="/validate/vendor", consumes= {"application/json"}, produces= {"application/json"}, method = {RequestMethod.POST})
    @ResponseBody
	public Vendor validateVendor(@RequestBody Vendor vendorObj) {
    	
    	Vendor vendorDetails = userDetailsProcessor.validateVendorDetails(vendorObj);
        return vendorDetails;
    }
	
	@RequestMapping(value = "/vendorProfile/{vendorId}", consumes= {"application/json"}, produces= {"application/json"}, method = {RequestMethod.GET})
	@ResponseBody
	public Vendor retrieveVendor(@PathVariable String vendorId) {
    	
		Vendor vendorDetails = userDetailsProcessor.retrieveVendor(vendorId);
		return vendorDetails;
    }
	
	//--------------------------
	@RequestMapping(value = "/forgotPassword", consumes= {"application/json"}, produces= {"application/json"}, method = {RequestMethod.GET})
	@ResponseBody
	public boolean forgotPassword(@PathVariable String userName, @PathVariable String type) {
    	
		boolean isMsgSend = userDetailsProcessor.forgotPassword(userName, type);
		return isMsgSend;
    }
	
	
}
