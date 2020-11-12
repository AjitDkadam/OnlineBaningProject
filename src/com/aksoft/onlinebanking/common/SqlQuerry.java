package com.aksoft.onlinebanking.common;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SqlQuerry {

	/*######################################## NewAccountDAO ####################################################################################################*/
	
	public static final String createNewAccount = "INSERT INTO NEW_ACCOUNT (ACCOUNT_NUMBER,USER_NAME,PASSWORD,REPASSWORD,AMOUNT,ADDRESS,CONTACT,EMAIL,STATUS,DATE_CREATED)VALUES(?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)";
	
	public static final String updateAccount = "UPDATE NEW_ACCOUNT SET USER_NAME=?,PASSWORD=?,AMOUNT=?,ADDRESS=?,CONTACT=?,EMAIL=?,STATUS=?,DATE_MODIFIED=CURRENT_TIMESTAMP WHERE ACCOUNT_NUMBER=?";
	
	public static final String getMaxAccountNumber = "SELECT max(ACCOUNT_NUMBER) AS ACCOUNT_NUMBER FROM NEW_ACCOUNT";
	
	public static final String findByAccountNumber = "SELECT * FROM NEW_ACCOUNT WHERE ACCOUNT_NUMBER=? AND STATUS=1";

	public static final String findByAccountNumberAndU1serNameAndPswd = "SELECT * FROM NEW_ACCOUNT WHERE ACCOUNT_NUMBER=? AND EMAIL=? AND PASSWORD=? AND STATUS=1";
	
	public static final String updateAmount = "UPDATE new_account SET AMOUNT=? ,DATE_MODIFIED=CURRENT_TIMESTAMP WHERE ACCOUNT_NUMBER=? AND EMAIL=? AND PASSWORD=?";
	
	public static final String findByAccountNo = "select * from NEW_ACCOUNT where ACCOUNT_NUMBER=?";
	/*######################################## AccountDetaDAO ####################################################################################################*/
	public static final String insertAccountDetails = "INSERT INTO account_details(ACCOUNT_NUMBER,AMOUNT,DATE_CREATED,amount_action)VALUES(?,?,CURRENT_TIMESTAMP,?)";
	
	public static final String findByAccountNoAcntDtl = "select * from account_details where ACCOUNT_NUMBER=?";
	
/*######################################## MSDefinitonDAO ####################################################################################################*/
	
	public Date DateConversion()throws Exception{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String todaysDate = dtf.format(now);
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(todaysDate);
		return date1;
		
	}
}
