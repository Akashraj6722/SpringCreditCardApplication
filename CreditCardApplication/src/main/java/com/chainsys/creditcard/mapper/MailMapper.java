package com.chainsys.creditcard.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MailMapper implements RowMapper<String> {

	@Override
	public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		String mail=rs.getString("email_id");
		
		return mail;
	}

}
