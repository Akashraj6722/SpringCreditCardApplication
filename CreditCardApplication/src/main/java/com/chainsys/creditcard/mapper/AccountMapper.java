package com.chainsys.creditcard.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.creditcard.model.Account;

public class AccountMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Account account = new Account();
		
		account.setId(rs.getInt("customer_id"));
		account.setAccountType(rs.getString("account_type"));
		account.setAccountNumber(rs.getString("account_number"));
		account.setIfsc(rs.getString("ifsc_code"));
		account.setBalance(rs.getInt("account_balance"));
		
		
		
		return account;
	}

}
