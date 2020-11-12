package com.aksoft.onlinebanking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.aksoft.onlinebanking.common.SqlQuerry;
import com.aksoft.onlinebanking.dto.AccountDetailsEntity;
import com.aksoft.onlinebanking.util.DataConnection;

import javafx.scene.chart.PieChart.Data;

public class AccountDetailsDAO {
	private static AccountDetailsDAO instance = null;
	String message = "";
	public static AccountDetailsDAO getInstance(){
		if(instance == null){
			instance = new AccountDetailsDAO();
		}
		return instance;
	}
	
	public String insertAccountDetails(AccountDetailsEntity accounDtlEntity)throws Exception{
		
		Connection con = DataConnection.getConnection();
		PreparedStatement pst = con.prepareStatement(SqlQuerry.insertAccountDetails);
		pst.setInt(1,accounDtlEntity.getAccountNumber());
		pst.setDouble(2, accounDtlEntity.getAmount());
		pst.setInt(3, accounDtlEntity.getAmountAction());
		int insertedRow = pst.executeUpdate();
		if(insertedRow >0){
			message = "success";
		}else{
			message="error";
		}
		return message;
	}
	
	public List findByAccountNoAcntDtl(int accountNo)throws Exception{
		List transactionDtl = new ArrayList();
		Connection con = DataConnection.getConnection();
		PreparedStatement pst = con.prepareStatement(SqlQuerry.findByAccountNoAcntDtl);
		pst.setInt(1, accountNo);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			AccountDetailsEntity accountDtlEntity = new AccountDetailsEntity();
			accountDtlEntity.setDateCreated(rs.getString("DATE_CREATED"));
			accountDtlEntity.setAmount(rs.getDouble("AMOUNT"));
			accountDtlEntity.setAmountAction(rs.getInt("amount_action"));
			transactionDtl.add(accountDtlEntity);
		}
		return transactionDtl;
		
	}
	
	
	
	
	
	
	
	
}
