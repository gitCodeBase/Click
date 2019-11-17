package com.group.booking.click.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.group.booking.click.dao.UserDetailsDao;
import com.group.booking.click.model.User;
import com.group.booking.click.model.Vendor;
import com.group.booking.click.utility.DBConstants;
import com.mongodb.WriteResult;

@Component
public class UserRepositoryImpl implements UserDetailsDao {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public User retrieveUserDetails(User user) {
		List<User> userDetailsList = mongoTemplate.find(Query.query(
				Criteria.where(DBConstants.USER_EMAILID).in(user.getEmailId())), User.class);
		if(userDetailsList.size() > 0)
			return userDetailsList.get(0);
		else
			return null;
	}
	
	@Override
	public String createUser(User user) {
		mongoTemplate.save(user);
		return user.get_id();
	}
	
	@Override
	public User retrieveUser(String userId) {
		List<User> userDetailsList = mongoTemplate.find(Query.query(
				Criteria.where(DBConstants.USER_ID).in(userId)), User.class);
		if(userDetailsList.size() > 0)
			return userDetailsList.get(0);
		else
			return null;
	}
	
	public void incrementWrongPasswordCount(User user) {
		Query query = new Query();
		query.addCriteria(Criteria.where(DBConstants.USER_ID).is(user.get_id()));
		
		Update update = new Update();
		update.set("wrongPasswordCount", user.getWrongPasswordCount() + 1);
		
		mongoTemplate.updateFirst(query, update, User.class);
	}
	
	///----------------Vendor
	
	public void createVendor(Vendor vendor) {
		mongoTemplate.save(vendor);
	}
	
	public Vendor retrieveVendorDetails(Vendor vendor) {
		List<Vendor> vendorDetailsList = mongoTemplate.find(Query.query(
				Criteria.where(DBConstants.USER_EMAILID).in(vendor.getEmailId())), Vendor.class);
		if(vendorDetailsList.size() > 0)
			return vendorDetailsList.get(0);
		else
			return null;
	}
	
	@Override
	public Vendor retrieveVendor(String vendorId) {
		List<Vendor> vendorDetailsList = mongoTemplate.find(Query.query(
				Criteria.where(DBConstants.USER_ID).in(vendorId)), Vendor.class);
		if(vendorDetailsList.size() > 0)
			return vendorDetailsList.get(0);
		else
			return null;
	}
	
	public void incrementWrongPasswordCount(Vendor vendor) {
		Query query = new Query();
		query.addCriteria(Criteria.where(DBConstants.USER_ID).is(vendor.get_id()));
		
		Update update = new Update();
		update.set("wrongPasswordCount", vendor.getWrongPasswordCount() + 1);
		
		mongoTemplate.updateFirst(query, update, Vendor.class);
	}
	
	//---------------
	
	public boolean updateUserPassword(User user) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where(DBConstants.USER_EMAILID).is(user.getEmailId()));
		
		Update update = new Update();
		update.set("password", user.getPassword());
		
		WriteResult resultDetails = mongoTemplate.updateFirst(query, update, User.class);
		return resultDetails.isUpdateOfExisting();
	}
	
	public boolean updateVendorPassword(Vendor vendor) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where(DBConstants.USER_EMAILID).is(vendor.getEmailId()));
		
		Update update = new Update();
		update.set("password", vendor.getPassword());
		
		WriteResult resultDetails = mongoTemplate.updateFirst(query, update, Vendor.class);
		return resultDetails.isUpdateOfExisting();
	}
	

}
