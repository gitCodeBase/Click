package com.group.booking.click.dao.impl;

import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

@Component
public class ConnectionImpl {

	private static MongoDatabase db = null;
	private static ConnectionImpl instance;
	
	protected ConnectionImpl(){
		
	}
	
	public static ConnectionImpl getInstance(){
		if(instance == null) {
			instance = new ConnectionImpl();
			
			MongoClient mongo = null;
			mongo = new MongoClient("localhost", 27017);

			/**** Get database ****/
			// if database doesn't exists, MongoDB will create it for you
			db = mongo.getDatabase("catalog");
		}
		return instance;
	}

	public MongoDatabase getDb() {
		return db;
	}
	
}
