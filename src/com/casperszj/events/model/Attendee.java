package com.casperszj.events.model;

public class Attendee {
	
	private String name;
	private String emailAddress;
	private String mobileNumber;
	
	public Attendee(String name, String emailAddress, String mobileNumber) {
		this.name = name;
		this.emailAddress = emailAddress;
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}
	
	

}
