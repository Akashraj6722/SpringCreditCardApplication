package com.chainsys.creditcard.model;

import org.springframework.stereotype.Component;

@Component
public class Employment {
	
	String occupation;
	String companyname;
	String designation;
	
		Long income;
		byte[] incomeProof;
	
		
		
	public byte[] getIncomeProof() {
			return incomeProof;
		}
		public void setIncomeProof(byte[] incomeProof) {
			this.incomeProof = incomeProof;
		}
	public Long getIncome() {
		return income;
	}
	public void setIncome(Long income) {
		this.income = income;
	}
	
	
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	

}
