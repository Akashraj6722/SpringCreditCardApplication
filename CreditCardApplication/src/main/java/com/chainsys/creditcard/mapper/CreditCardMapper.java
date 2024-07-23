package com.chainsys.creditcard.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.creditcard.model.CreditCard;

public class CreditCardMapper implements RowMapper<CreditCard>{

	@Override
	public CreditCard mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CreditCard creditCard=new CreditCard();
		
		
		creditCard.setId(rs.getInt("customer_id"));
		creditCard.setAccountNumber(rs.getString("account_number"));
		creditCard.setCardNumber(rs.getString("credit_card_number"));
		creditCard.setCardType(rs.getString("credit_card_type"));
		creditCard.setCardAppliedDate(rs.getString("credit_card_issue_date"));
		creditCard.setCardStatus(rs.getString("credit_card_status"));
		creditCard.setCardApproval(rs.getString("credit_card_approval"));
	
				
		return creditCard;
	}

}
