package com.fxutility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommonMessage {
	
	private CommonMessage() {
		// No logic
	}
	
	public static final String EMPTY = "Enter the required Input values!";
	
	public static final String INVALID_NAME = "Please enter a valid input!";
	
	public static final String INVALID_EMAIL = "Please enter a valid email!";
	
	public static final String INVALID_MOBILE = "Please enter a valid mobile number!";
	
	public static final String CHECK_INPUT = "Please Check your input!";
	
	public static final String SAVE_CONFIRMATION = "Are you sure you want to save it?";
	
	public static final String DECLARATION = "Check the declaration statement";
	
	public static ObservableList<String> categoryList = FXCollections.observableArrayList("Under Graduate","Post Graduate", "SSLC","HSC");
	
	public static ObservableList<String> nationality = FXCollections.observableArrayList(
			"Indian",
			"USA", 
			"Russia",
			"Korea",
			"Japan",
			"China",
			"Germany",
			"Ireland",
			"England",
			"Austria",
			"Indonesia",
			"Singapore",
			"Norway",
			"Dubai");
	
	public static ObservableList<String> certificationList = FXCollections.observableArrayList(
			"IZ0-808 - Oracle Certified Java Associate (OCAJP)",
			"AWS Certified Solution Architect - Associate",
			"Datastax Certified Apache Cassandra Developer",
			"Datastax Certified Apache Cassandra Administrator",
			"Certified cloud security professional (CCSP)",
			"Certified data privacy solutions engineer (CDPSE)",
			"Microsoft Certified Azure Solutions Architect",
			"Certified ethical hacker (CEH)",
			"Microsoft Certified: Azure Administrator Associate",
			"Cisco Certified Network Professional – Enterprise",
			"AWS Certified Cloud Practitioner",
			"Project Management Professional (PMP)",
			"Google Certified Professional Cloud Architect",
			"Docker Certified Associate (DCA)");
	
}
