package com.chainsys.creditcard.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chainsys.creditcard.model.User;

public class IdMapper implements RowMapper<Integer> {

	@Override
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		
		int id=rs.getInt("id");
		
//		userDetails.setCustomerID(id);
//		System.out.println("in ID mapper"+userDetails.getCustomerID());
		return id;
	}

}
