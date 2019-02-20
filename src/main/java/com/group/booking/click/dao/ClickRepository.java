package com.group.booking.click.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.group.booking.click.model.AvailabilityDetails;

public interface ClickRepository extends MongoRepository<AvailabilityDetails, String>, AvailDao, ItemDao {


}