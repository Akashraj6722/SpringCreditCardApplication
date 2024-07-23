package com.chainsys.creditcard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.creditcard.model.CreditCard;

@Repository

public class ApprovalRecordsImpl implements ApprovalRecordsDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public  void approve(CreditCard creditCard) {
		
		String query="UPDATE credit_card_details SET credit_card_approval='Approved',credit_card_status='Active' WHERE customer_id=? AND credit_card_number=? ";

		jdbcTemplate.update(query, creditCard.getId(),creditCard.getCardNumber());
		
		System.out.println("Approval updated query succes"+creditCard.getId()+creditCard.getCardNumber());
	
	}
	public  void reject(CreditCard creditCard)  {
		String query="UPDATE credit_card_details SET credit_card_approval='Rejected',credit_card_status='Not active' WHERE customer_id=? AND credit_card_number=? ";
		
		jdbcTemplate.update(query, creditCard.getId(),creditCard.getCardNumber());
		
		System.out.println("Approval Rejected query succes"+creditCard.getId()+creditCard.getCardNumber());
		
	}
	

	public ApprovalRecordsImpl() {
		super();
	}
	
}
