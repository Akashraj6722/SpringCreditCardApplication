package com.chainsys.creditcard.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.creditcard.model.Transactions;

public class TransactionMapper implements RowMapper<Transactions> {

	@Override
	public Transactions mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Transactions transactions = new Transactions();
		
		transactions.setCardNumber(rs.getString("card_number"));
		transactions.setTranscationId(rs.getString("transaction_id"));
		transactions.setDateTime(rs.getString("date_time"));
		transactions.setAmount(rs.getInt("amount"));
		transactions.setDescription(rs.getString("description"));

		
		
		
		
		return transactions;
	}

	
	
}
