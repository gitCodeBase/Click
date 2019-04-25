package com.group.booking.click.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="notification")
public class Notification implements Serializable {

	private String _id;
	private String vendorId;
	private String message;
	private boolean isRead;
	private int notificationCount;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public int getNotificationCount() {
		return notificationCount;
	}
	public void setNotificationCount(int notificationCount) {
		this.notificationCount = notificationCount;
	}
	
	
}
