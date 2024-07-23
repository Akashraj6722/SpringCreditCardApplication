package com.chainsys.creditcard.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class Transactions {
	
	int id;
	String cardNumber;
	String dateTime;
	String transcationId;
	int amount;
	String description;
	
	public Transactions() {
		
	}
	
	
	
	public Transactions(int int1, String string) {
		
	}






	public Transactions(String string, String string2, int int1, String string3) {
	}



	public String getDateTime() {
		return dateTime;
	}



	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
	
	public String getTranscationId() {
		return transcationId;
	}
	public void setTranscationId(String transcationId) {
		this.transcationId = transcationId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
}
