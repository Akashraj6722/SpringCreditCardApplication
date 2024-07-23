package com.chainsys.creditcard.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.chainsys.creditcard.mapper.AccountMapper;
import com.chainsys.creditcard.mapper.CibilMapper;
import com.chainsys.creditcard.mapper.CibilScoreMapper;
import com.chainsys.creditcard.model.Account;
import com.chainsys.creditcard.model.User;

@Repository
public class AccountRecordsImpl implements AccountRecordsDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void insert(User user, Account account) {
		String query = "INSERT INTO account_details(customer_id,account_number,ifsc_code)VALUES(?,?,?)";

		Object[] parameter = { user.getCustomerID(), account.getAccountNumber(),
				account.getIfsc() };
         
		int records = jdbcTemplate.update(query, parameter);

	}

	public Account read(Account account,int id) {
		
		String query="SELECT customer_id,account_type,account_number,ifsc_code,account_balance FROM account_details WHERE customer_id=?";
		System.out.println("1234"+id);
         System.out.println("Completed account read method");
         
		return jdbcTemplate.queryForObject(query, new AccountMapper(),id);

	}
	
public List<Account> readProfile() {
		
		String query="SELECT customer_id, account_type,account_number,ifsc_code,account_balance FROM account_details";
		
		List<Account> list =  jdbcTemplate.query(query, new AccountMapper());
         System.out.println("Completed account read method");
		return list;

	}
	
	
	public Integer readCibil(User user) {
		
		String query="SELECT cibil_score FROM account_details WHERE customer_id=? ";
		
		System.out.println("reading id in readCibil"+user.getCustomerID());

//		System.out.println("reading cibil inreadCibil"+account.getCibil());

		return jdbcTemplate.queryForObject(query,new CibilScoreMapper(), user.getCustomerID());

	}

	public void insertCibil(User user, Account account) {
		
		String query="UPDATE account_details SET cibil_score=? WHERE customer_id=?";
		
		jdbcTemplate.update(query,account.getCibil(), user.getCustomerID());
		
		System.out.println("Updated Cibil score");

	}

	public boolean checkCibil(int id) {
		
		String query="SELECT cibil_score FROM account_details WHERE customer_id=? ";
		
		System.out.println("id in checking for cibil"+id);
		
		int cibil=jdbcTemplate.queryForObject(query,new CibilMapper(), id);
		
		System.out.println(cibil);
		if(cibil!=0) {
			
			
			return true;
		}
		

		return false;
	}

	public AccountRecordsImpl() {
		super();
	}
}