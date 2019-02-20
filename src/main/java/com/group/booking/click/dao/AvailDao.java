package com.group.booking.click.dao;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.group.booking.click.model.AvailabilityDetails;

public interface AvailDao {

	public List<AvailabilityDetails> fetchAvailability(AvailabilityDetails availabilityDetails);
	public void saveOrUpdateAvailability(AvailabilityDetails availabilityDetails) throws JsonProcessingException;
}
