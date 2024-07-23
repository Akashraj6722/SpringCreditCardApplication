package com.chainsys.creditcard.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.chainsys.creditcard.mapper.CreditCardMapper;

import com.chainsys.creditcard.model.Account;
import com.chainsys.creditcard.model.CreditCard;
import com.chainsys.creditcard.model.User;
import com.chainsys.creditcard.model.Transactions;


@Repository

public class CardRecordsImpl implements CardRecordsDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
public  void insert(CreditCard creditCard, User user ,Account account)  {
		
		
		String query="INSERT INTO credit_card_details(customer_id,account_number,credit_card_number,credit_card_type,credit_card_cvv,credit_card_issue_date,credit_card_valid_till) "
				+ "VALUES(?,?,?,?,?,?,?)";
		
		jdbcTemplate.update(query, user.getCustomerID(),account.getAccountNumber(),creditCard.getCardNumber(),creditCard.getCardType(),creditCard.getCvvNumber(),creditCard.getCardAppliedDate(),creditCard.getValidity());
	
		
}

  public  void update(CreditCard creditCard,User user)  {
		
		String query="UPDATE credit_card_details SET credit_card_pin=? WHERE customer_id=? AND credit_card_number=?";
		
		System.out.println("to update pin"+creditCard.getPin()+user.getCustomerID()+creditCard.getCardNumber());
		jdbcTemplate.update(query, creditCard.getPin(),user.getCustomerID(),creditCard.getCardNumber());
		
		
		
		
  }
  public  void updateCreditPoints(CreditCard creditCard,Transactions transactions)  {
		
		String query="UPDATE credit_card_details SET credit_points=? WHERE customer_id=? AND credit_card_number=?";
		
		System.out.println("to update points"+creditCard.getCreditPoints()+transactions.getId()+transactions.getCardNumber());
		jdbcTemplate.update(query,+creditCard.getCreditPoints(),transactions.getId(),transactions.getCardNumber());
		
		
		
		
}
  
  public  List<CreditCard> read()  {
		
      new ArrayList<>();

		String query = "SELECT customer_id,account_number,credit_card_number,credit_card_type,credit_card_issue_date,credit_card_status,credit_card_approval FROM credit_card_details";
		 List<CreditCard> list =jdbcTemplate.query(query, new CreditCardMapper());
		
		return list;

  }
  
  public boolean checkCardApproval(String cardNumber) {
	    String query = "SELECT credit_card_number FROM credit_card_details WHERE credit_card_number=? AND credit_card_approval='Approved'";

	    List<String> results = jdbcTemplate.query(query, 
	        (rs, rowNum) -> rs.getString("credit_card_number"), 
	        cardNumber);

	    return !results.isEmpty();
	}
  
  public boolean checkCardPin(String cardNumber) {
	    String query = "SELECT credit_card_pin FROM credit_card_details WHERE credit_card_number=? AND credit_card_approval='Approved'";

	    List<CreditCard> results = jdbcTemplate.query(query, 
	            (rs, rowNum) -> new CreditCard( rs.getInt("credit_card_pin")), 
	            cardNumber);

	    return !results.isEmpty();
	}

 public  boolean checkPayment(Transactions transactions,int cvv,String validity)  {
	  
		String query = "SELECT credit_card_number,credit_card_cvv,credit_card_valid_till,credit_card_approval FROM credit_card_details WHERE credit_card_number=? AND credit_card_cvv=? AND credit_card_valid_till=? AND credit_card_approval='Approved' ";
		
		System.out.println("cardDetails in checkPayment"+ transactions.getCardNumber()+cvv+validity);
		 List<CreditCard> results=jdbcTemplate.query(query ,(rs, rowNum) -> new CreditCard(rs.getString("credit_card_number"), rs.getInt("credit_card_cvv"),rs.getString("credit_card_valid_till"),rs.getString("credit_card_approval")), 
	            transactions.getCardNumber(),cvv,validity);
		 return !results.isEmpty();	
  }
 
 public  boolean checkPoints(int id,String cardNumber)  {
	  
		String query = "SELECT customer_id, credit_card_number FROM credit_card_details WHERE customer_id AND credit_card_number=?   ";
		
		System.out.println("cardDetails in checkPoints"+ id+cardNumber);
		 List<CreditCard> results=jdbcTemplate.query(query ,(rs, rowNum) -> new CreditCard( rs.getInt("customer_id"),rs.getString("credit_card_number")), 
				 cardNumber);
		 
		 return !results.isEmpty();	
}
 
 public  Integer readCreditPoints(int id,String cardNumber)  {
	  
		String query = "SELECT credit_points FROM credit_card_details WHERE customer_id=? AND credit_card_number=?  ";
		
		System.out.println("cardDetails in readCreditpoints"+ id+cardNumber);
				 
		 return jdbcTemplate.queryForObject(query ,Integer.class, 
					id, cardNumber);	
}
 
 public  List<CreditCard> display(CreditCard creditCard) {
		
		ArrayList<CreditCard> list=new ArrayList<CreditCard>();
		creditCard.getHolderName();
		creditCard.getCardNumber();
		creditCard.getCvvNumber();
		creditCard.getCardAppliedDate();
		creditCard.getValidity();
		
		
		list.add(creditCard);
		return list;
		
		
		
	}
  
 public CardRecordsImpl() {
	super();
}



}
  

