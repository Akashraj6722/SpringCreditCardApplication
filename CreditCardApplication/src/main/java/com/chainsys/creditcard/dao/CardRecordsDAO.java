package com.chainsys.creditcard.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chainsys.creditcard.model.Account;
import com.chainsys.creditcard.model.CreditCard;
import com.chainsys.creditcard.model.User;
import com.chainsys.creditcard.model.Transactions;


public interface CardRecordsDAO {

	public void insert(CreditCard creditCard, User user, Account account);

	public void update(CreditCard creditCard, User user);
	
	public  void updateCreditPoints(CreditCard creditCard,Transactions transactions) ;

	public List<CreditCard> read();

	 public boolean checkCardApproval(String cardNumber) ;
	 public boolean checkCardPin(String cardNumber);

	 public  boolean checkPayment(Transactions transactions,int cvv,String validity) ;
	 
	 public  boolean checkPoints(int id,String cardNumber) ;
	 
	 public  Object readCreditPoints(int id,String cardNumber) ;

	
	public  List<CreditCard> display(CreditCard CreditCard);

}
