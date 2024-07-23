package com.chainsys.creditcard.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.creditcard.model.Employment;

public class IncomeProofMapper implements RowMapper<Employment> {

	@Override
	public Employment mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Employment employment= new Employment();
		
		employment.setIncomeProof(rs.getBytes("income_proof"));

		return employment;
	}

}
