package com.aksoft.onlinebanking.dto;

public class AccountDetailsEntity {
	
	private int accountDetailId = 0;
	private int accountNumber = 0;
	private double amount = 0;
	private String dateCreated = null;
	private int amountAction = 0;
	public int getAccountDetailId() {
		return accountDetailId;
	}
	public void setAccountDetailId(int accountDetailId) {
		this.accountDetailId = accountDetailId;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getAmount() {
		return amount;
	}
	
	public int getAmountAction() {
		return amountAction;
	}
	public void setAmountAction(int amountAction) {
		this.amountAction = amountAction;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
}
