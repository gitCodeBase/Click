package com.group.booking.click.dao;

import com.group.booking.click.model.User;
import com.group.booking.click.model.Vendor;

public interface UserDetailsDao {

	public User retrieveUserDetails(User user);
	
	public String createUser(User user);
	
	public User retrieveUser(String userId);
	
	public void incrementWrongPasswordCount(User user);
	
	//----Vendor
	
	public void createVendor(Vendor vendor);
	
	public Vendor retrieveVendorDetails(Vendor vendor);
	
	public Vendor retrieveVendor(String vendorId);
	
	public void incrementWrongPasswordCount(Vendor vendor);
	
	//---------
	
	public boolean updateUserPassword(User user);
	
	public boolean updateVendorPassword(Vendor vendor);
	
}
