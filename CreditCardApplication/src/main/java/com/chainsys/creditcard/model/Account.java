package com.chainsys.creditcard.model;

import org.springframework.stereotype.Component;

@Component
public class Account {
	
	int id;
	String accountNumber;
	String ifsc;
	String accountType;
	int balance;
	int cibil;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCibil() {
		return cibil;
	}
	public void setCibil(int cibil) {
		this.cibil = cibil;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
	

}
