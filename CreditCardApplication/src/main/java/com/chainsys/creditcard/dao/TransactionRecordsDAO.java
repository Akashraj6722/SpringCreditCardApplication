package com.chainsys.creditcard.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chainsys.creditcard.model.Transactions;

@Repository
public interface TransactionRecordsDAO {
	
	public void insert(Transactions transactions);
	
	public List<Transactions> read(Transactions transactions);
	
	public boolean checkCardNumber(String cardNumber, int id);
	

}
