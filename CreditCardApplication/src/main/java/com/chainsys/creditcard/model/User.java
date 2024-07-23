package com.chainsys.creditcard.model;

import org.springframework.stereotype.Component;

@Component
public class User {
	
	int customerID;
	String fName;
	String lName;
	String dob;
	String aadhaar;
	byte[] aadhaarProof;
	String pan;
	byte[] panProof;
	String mail;
	String phone;
	String password;
	
	
	
	
	public byte[] getAadhaarProof() {
		return aadhaarProof;
	}
	public void setAadhaarProof(byte[] aadhaarProof) {
		this.aadhaarProof = aadhaarProof;
	}
	public byte[] getPanProof() {
		return panProof;
	}
	public void setPanProof(byte[] panProof) {
		this.panProof = panProof;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	}
	
	
	
	
	


