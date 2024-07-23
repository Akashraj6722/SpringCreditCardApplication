package com.chainsys.creditcard.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CibilScoreMapper implements RowMapper<Integer> {

	@Override
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		   
		
		int cibil=rs.getInt("cibil_score"); 
		
		return cibil;
	}

}
