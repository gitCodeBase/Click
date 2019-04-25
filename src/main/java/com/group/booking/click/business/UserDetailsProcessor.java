package com.group.booking.click.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.group.booking.click.dao.UserDetailsDao;
import com.group.booking.click.model.User;
import com.group.booking.click.model.Vendor;
import com.group.booking.click.utility.PasswordUtils;
import com.group.booking.click.utility.RandomPasswordGenerator;

@Component
public class UserDetailsProcessor {

	@Autowired
	private UserDetailsDao userDetailsDao;
	@Autowired
	private RandomPasswordGenerator randomPasswordGenerator;
	@Autowired
	private UserPasswordMailProcessor userPasswordMailProcessor;
	
	/**
	 * Method to fetch Item details based on Item Id
	 * @param String -itemId
	 * @return Item
	 */
	public User validateUserDetails(User user) {
	  
		//call the dao for fetching the item details 
		User userDetails = userDetailsDao.retrieveUserDetails(user);
		
		if(userDetails != null) {
	        // Encrypted and Base64 encoded password read from database
	        boolean passwordMatch = PasswordUtils.verifyUserPassword(user.getPassword(), userDetails.getPassword());
	        
	        if(passwordMatch && userDetails.getWrongPasswordCount() <= 4) {
	            System.out.println("Provided user password is correct.");
	            return userDetails; 
	        } else {
	            System.out.println("Provided password is incorrect");
	            if(userDetails.getWrongPasswordCount() <= 4 ) {
	            	userDetailsDao.incrementWrongPasswordCount(userDetails);
	            } else {
	            	userDetails = new User();
	            	userDetails.setWrongPasswordCount(5);
	            	return userDetails;
	            }
	        }
		}
		return null;
 	}
	
	public User retrieveUserDetails(User user) {
		User userDetails = userDetailsDao.retrieveUserDetails(user);
		
		return userDetails;
	}
	
	public User retrieveUser(String userId) {
		User userDetails = userDetailsDao.retrieveUser(userId);
		
		return userDetails;
	}
	
	public void createUser(User user) {
		    
        // Protect user's password. The generated value can be stored in DB.
        String mySecurePassword = PasswordUtils.generateSecurePassword(user.getPassword());
        
        // Print out protected password 
        System.out.println("My secure password = " + mySecurePassword);
        user.setPassword(mySecurePassword);
		userDetailsDao.createUser(user);
	}
	
	//------------------Vendor
	
	public void createVendor(Vendor vendor) {
		 // Protect user's password. The generated value can be stored in DB.
        String mySecurePassword = PasswordUtils.generateSecurePassword(vendor.getPassword());
        
        // Print out protected password 
        System.out.println("My secure password = " + mySecurePassword);
        vendor.setPassword(mySecurePassword);
		userDetailsDao.createVendor(vendor);
	}
	
	/**
	 * Method to fetch Item details based on Item Id
	 * @param String -itemId
	 * @return Item
	 */
	public Vendor validateVendorDetails(Vendor vendor) {
	  
		//call the dao for fetching the item details 
		Vendor vendorDetails = userDetailsDao.retrieveVendorDetails(vendor);
		
		if(vendorDetails != null) {
	        // Encrypted and Base64 encoded password read from database
	        boolean passwordMatch = PasswordUtils.verifyUserPassword(vendor.getPassword(), vendorDetails.getPassword());
	        
	        if(passwordMatch && vendorDetails.getWrongPasswordCount() <= 4) {
	            System.out.println("Provided user password is correct.");
	            return vendorDetails; 
	        } else {
	        	System.out.println("Provided password is incorrect");
	            if(vendorDetails.getWrongPasswordCount() <= 4 ) {
	            	userDetailsDao.incrementWrongPasswordCount(vendorDetails);
	            } else {
	            	vendorDetails = new Vendor();
	            	vendorDetails.setWrongPasswordCount(5);
	            	return vendorDetails;
	            }
	           
	        }
		}
		 return null;
	}
	
	public User retrieveVendorDetails(User user) {
		User userDetails = userDetailsDao.retrieveUserDetails(user);
		
		return userDetails;
	}
	
	public Vendor retrieveVendor(String vendorId) {
		Vendor vendorDetails = userDetailsDao.retrieveVendor(vendorId);
		
		return vendorDetails;
	}
	
	/**
	 * Method to update user password
	 * @param user
	 */
	public void updateUserPassword(User user) {
		userDetailsDao.updateUserPassword(user);
	}
	
	/**
	 * Method to update user password
	 * @param user
	 */
	public void updateVendorPassword(Vendor vendor) {
		userDetailsDao.updateVendorPassword(vendor);
	}
	
	public boolean forgotPassword(String userName, String type) {
		if(!StringUtils.isEmpty(userName)) {
		
			String randomPassword = randomPasswordGenerator.generatePassayPassword();
			// Protect user's password. The generated value can be stored in DB.
	        String mySecurePassword = PasswordUtils.generateSecurePassword(randomPassword);
			
			if(type.equals("vendor")) {
				Vendor vendor = new Vendor();
				vendor.setEmailId(userName);
				vendor.setPassword(mySecurePassword);
				updateVendorPassword(vendor);
			} else {
				User user = new User();
				user.setEmailId(userName);
				user.setPassword(mySecurePassword);
				updateUserPassword(user);
			}
			
			userPasswordMailProcessor.sendMail(userName, randomPassword);
			
			return true;
		} else {
			return false;
		}
	}
}
