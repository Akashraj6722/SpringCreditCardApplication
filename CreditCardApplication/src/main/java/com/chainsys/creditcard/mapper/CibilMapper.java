package com.chainsys.creditcard.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.creditcard.model.Account;

public class CibilMapper implements RowMapper<Integer>{

	@Override
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		   
//		Account account = new Account();
		int cibil=rs.getInt("cibil_score"); 
		
		return cibil;
		
		
		
		
	}
	
	
	

}
