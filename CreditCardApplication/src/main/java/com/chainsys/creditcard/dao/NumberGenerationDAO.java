package com.chainsys.creditcard.dao;

import org.springframework.stereotype.Repository;


public interface NumberGenerationDAO {
	
	public String accountNumber();
	
	public String ifsc();
	
	public  int ccvNumber();
	
	public  String visaCreditCardNumber();
	
	public  String masterCreditCardNumber();
	
	public  String rupayCreditCardNumber();
	
	public  String pulseCreditCardNumber() ;
	
	public String transactionID();
	
	public String[] splitCardNumber(String cardNumber) ;

}
