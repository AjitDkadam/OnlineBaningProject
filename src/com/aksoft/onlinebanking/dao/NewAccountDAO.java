package com.aksoft.onlinebanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.aksoft.onlinebanking.common.SqlQuerry;
import com.aksoft.onlinebanking.dto.NewAccountEntity;
import com.aksoft.onlinebanking.util.DataConnection;

import javafx.scene.chart.PieChart.Data;

public class NewAccountDAO {
	
	private static NewAccountDAO instance = null;
	private NewAccountDAO(){
		
	}
	
	public static NewAccountDAO getInstance(){
		if(instance == null){
			instance = new NewAccountDAO();
		}
		return instance;
	}
	String message= "";
	public int createNewAccount(NewAccountEntity accontEntity) throws Exception{
		Connection con = DataConnection.getConnection();
		PreparedStatement pst = con.prepareStatement(SqlQuerry.createNewAccount);
		pst.setInt(1,getMaxAccontNo());
		pst.setString(2, accontEntity.getUserName());
		pst.setString(3, accontEntity.getPassword());
		pst.setString(4, accontEntity.getRepassword());
		pst.setDouble(5, accontEntity.getAmount());
		pst.setString(6, accontEntity.getAddress());
		pst.setLong(7, accontEntity.getContact());
		pst.setString(8, accontEntity.getEmail());
		pst.setInt(9, 1);
		int acntNum = getMaxAccontNo();
		int acntnum = pst.executeUpdate();
		if(acntnum > 0){
			acntnum = acntNum;
		}
		return acntnum;
		
	}
	
	public int getMaxAccontNo() throws Exception{
		int acntNo = 0;
		Connection con = DataConnection.getConnection();
		PreparedStatement pst = con.prepareStatement(SqlQuerry.getMaxAccountNumber);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			acntNo = rs.getInt("ACCOUNT_NUMBER");
		}
		if(acntNo == 0){
			acntNo = 788401001;
		}else{
			acntNo++;
		}
		return acntNo;
	}
	
	public NewAccountEntity findByAccountNumber(int accountNumber) throws Exception{
		Connection con = DataConnection.getConnection();
		PreparedStatement pst = con.prepareStatement(SqlQuerry.findByAccountNumber);
		pst.setInt(1, accountNumber);
		ResultSet rs = pst.executeQuery();
		NewAccountEntity accountEntity = new NewAccountEntity();
		while(rs.next()){
			accountEntity.setUserName(rs.getString("USER_NAME"));
			accountEntity.setAccountNo(rs.getInt("ACCOUNT_NUMBER"));
			accountEntity.setAmount(rs.getDouble("AMOUNT"));
			accountEntity.setEmail(rs.getString("EMAIL"));
			accountEntity.setPassword(rs.getString("PASSWORD"));
			accountEntity.setContact(rs.getLong("CONTACT"));
			accountEntity.setStatus(rs.getInt("STATUS"));
			accountEntity.setDateCreated(rs.getString("DATE_CREATED"));
		}
		return accountEntity;
	}
	
	public NewAccountEntity findByAccountNumberAndUserNameAndPswd(int accountNumber,String userName,String pswd) throws Exception{
		Connection con = DataConnection.getConnection();
		PreparedStatement pst = con.prepareStatement(SqlQuerry.findByAccountNumberAndU1serNameAndPswd);
		pst.setInt(1, accountNumber);
		pst.setString(2, userName);
		pst.setString(3, pswd);
		ResultSet rs = pst.executeQuery();
		NewAccountEntity accountEntity = new NewAccountEntity();
		while(rs.next()){
			accountEntity.setUserName(rs.getString("USER_NAME"));
			accountEntity.setAccountNo(rs.getInt("ACCOUNT_NUMBER"));
			accountEntity.setAmount(rs.getDouble("AMOUNT"));
			accountEntity.setEmail(rs.getString("EMAIL"));
			accountEntity.setAddress(rs.getString("ADDRESS"));
			accountEntity.setPassword(rs.getString("PASSWORD"));
			accountEntity.setContact(rs.getLong("CONTACT"));
			accountEntity.setStatus(rs.getInt("STATUS"));
			accountEntity.setDateCreated(rs.getString("DATE_CREATED"));
		}
		return accountEntity;
		
	}
	
	public String depositeAmount(int accountNumber,String email,String pssword,double amount) throws Exception{
		Connection con = DataConnection.getConnection();
		PreparedStatement pst = con.prepareStatement(SqlQuerry.updateAmount);
		pst.setDouble(1, amount);
		pst.setInt(2, accountNumber);
		pst.setString(3, email);
		pst.setString(4, pssword);
		int st = pst.executeUpdate();
		if(st>0){
			message = "success";
		}else{
			message = "error";
		}
		return message;
		
	}
	
	public String withdrawAmount(int accountNumber,String email,String pssword,double amount)throws Exception{
		Connection con = DataConnection.getConnection();
		PreparedStatement pst = con.prepareStatement(SqlQuerry.updateAmount);
		pst.setDouble(1, amount);
		pst.setInt(2, accountNumber);
		pst.setString(3, email);
		pst.setString(4, pssword);
		int st = pst.executeUpdate();
		if(st>0){
			message = "success";
		}else{
			message = "error";
		}
		return message;
	}
	
	public String updateNewAccount(NewAccountEntity accountEntity)throws Exception{
		Connection con = DataConnection.getConnection();
		PreparedStatement pst = con.prepareStatement(SqlQuerry.updateAccount);
		pst.setString(1, accountEntity.getUserName());
		pst.setString(2, accountEntity.getPassword());
		pst.setDouble(3, accountEntity.getAmount());
		pst.setString(4, accountEntity.getAddress());
		pst.setLong(5, accountEntity.getContact());
		pst.setString(6, accountEntity.getEmail());
		pst.setInt(7, accountEntity.getStatus());
		pst.setInt(8, accountEntity.getAccountNo());
		int st = pst.executeUpdate();
		if(st > 0){
			message = "success";
		}else{
			message = "error";
		}
		return message;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public NewAccountEntity findByAccountNo(int accountNumber)throws Exception{
		Connection con = DataConnection.getConnection();
		PreparedStatement pst = con.prepareStatement(SqlQuerry.findByAccountNo);
		pst.setInt(1, accountNumber);
		ResultSet rs = pst.executeQuery();
		NewAccountEntity accountEntity = new NewAccountEntity();
		while(rs.next()){
			accountEntity.setAccountNo(rs.getInt("ACCOUNT_NUMBER"));
			accountEntity.setUserName(rs.getString("USER_NAME"));
			accountEntity.setEmail(rs.getString("EMAIL"));
			accountEntity.setAddress(rs.getString("ADDRESS"));
			accountEntity.setContact(rs.getLong("CONTACT"));
			accountEntity.setAmount(rs.getDouble("AMOUNT"));
			accountEntity.setDateCreated(rs.getString("DATE_CREATED"));
			accountEntity.setStatus(rs.getInt("STATUS"));
			accountEntity.setPassword(rs.getString("PASSWORD"));
		}
		return accountEntity;
	}*/
	
	
	
}
