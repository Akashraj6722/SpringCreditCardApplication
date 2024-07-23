package com.chainsys.creditcard.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.creditcard.model.User;

public class ProfileMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
User user=new User();
		
		user.setCustomerID(rs.getInt("id"));
		user.setfName(rs.getString("first_name"));
		user.setlName(rs.getString("last_name"));
		user.setDob(rs.getString("dob"));
		user.setAadhaar(rs.getString("aadhaar_number"));
		user.setPan(rs.getString("pan_number"));
		user.setMail(rs.getString("email_id"));
		user.setPhone(rs.getString("phone_number"));

	
		return user;
	}

}
