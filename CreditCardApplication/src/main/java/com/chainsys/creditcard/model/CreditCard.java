package com.chainsys.creditcard.model;

import org.springframework.stereotype.Component;

@Component
public class CreditCard {
	
	int id;
	String accountNumber;
	String holderName;
	String cardNumber;
	String cardType;
	int cvvNumber;
	int pin;
	int creditPoints;
	String cardAppliedDate;
	String validity;
	String cardApproval;
	String cardStatus;
	
	
	
	public CreditCard(String string, int i) {
		
		
	}
	public CreditCard() {
	}
	public CreditCard(String string, int int1, String string2, String string3) {

	}
	public CreditCard(String string, int int1, String string2) {
	}
	public CreditCard(int int1) {
		// TODO Auto-generated constructor stub
	}
	
	public CreditCard(int int1, String string) {
	}
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	public String getCardApproval() {
		return cardApproval;
	}
	public void setCardApproval(String cardApproval) {
		this.cardApproval = cardApproval;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardNumber() {
		return cardNumber;
		
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public int getCvvNumber() {
		return cvvNumber;
	}
	public void setCvvNumber(int cvvNumber) {
		this.cvvNumber = cvvNumber;
	}
	
	public String getCardAppliedDate() {
		return cardAppliedDate;
	}
	public void setCardAppliedDate(String cardAppliedDate) {
		this.cardAppliedDate = cardAppliedDate;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public int getCreditPoints() {
		return creditPoints;
	}
	public void setCreditPoints(int creditPoints) {
		this.creditPoints = creditPoints;
	}

	
	
	

}
