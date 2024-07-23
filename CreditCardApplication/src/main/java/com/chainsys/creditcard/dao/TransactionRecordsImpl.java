package com.chainsys.creditcard.dao;


import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.creditcard.model.CreditCard;
import com.chainsys.creditcard.model.Transactions;
import com.chainsys.creditcard.mapper.TransactionMapper;


@Configuration
@Repository("transactionRecordsDAO")

public class TransactionRecordsImpl implements TransactionRecordsDAO  {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void insert(Transactions transactions) {
		String query = "INSERT INTO transactions VALUES(?,?,?,?,?,?) ";
		
		Object[] parameters= {transactions.getId(),
				transactions.getCardNumber(),transactions.getTranscationId(),
				transactions.getDateTime(),transactions.getAmount(),transactions.getDescription()};
				
		jdbcTemplate.update(query, parameters);

	}

	public List<Transactions> read(Transactions transactions) {


		String query = "SELECT card_number,transaction_id,date_time,amount,description FROM transactions WHERE id=? AND card_number=?   ";
		
		System.out.println("id in transactions read"+transactions.getId());
		System.out.println("cardNumber in transactions read"+transactions.getCardNumber());

		
		List<Transactions> list = jdbcTemplate.query(query,
				new TransactionMapper(),
				transactions.getId(),transactions.getCardNumber());
		
		return list;

	}
	
//	(rs,rowNum)->new Transactions(rs.getString("card_number"),
//	rs.getString("transaction_id"),rs.getInt("amount"),
//	rs.getString("date_time"),
//			rs.getString("description"))
	public boolean checkCardNumber(String cardNumber, int id) {
		
		String query="SELECT id,card_number  FROM transactions WHERE card_number=? AND id=? ";

		
	    List<Transactions> results = jdbcTemplate.query(query, 
	            (rs, rowNum) -> new Transactions( rs.getInt("id"),rs.getString("card_number")), 
	            cardNumber,id);
	    
		return !results.isEmpty();		
		
	}
}
