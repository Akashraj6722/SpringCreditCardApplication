package com.chainsys.creditcard.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.creditcard.model.User;

public class LoginMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		User userDetails=new User();

		userDetails.setMail(rs.getString("email_id"));
		userDetails.setPassword(rs.getString("password"));

		
		return userDetails;
	}

}
