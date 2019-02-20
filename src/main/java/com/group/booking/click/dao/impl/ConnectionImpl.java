package com.group.booking.click.dao.impl;

import java.net.UnknownHostException;

import org.springframework.stereotype.Component;

import com.mongodb.DB;
import com.mongodb.MongoClient;

@Component
public class ConnectionImpl {

	private static DB db = null;
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
			db = mongo.getDB("test");
		}
		return instance;
	}

	public DB getDb() {
		return db;
	}
	
}
