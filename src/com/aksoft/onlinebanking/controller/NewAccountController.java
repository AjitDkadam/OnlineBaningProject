package com.aksoft.onlinebanking.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aksoft.onlinebanking.common.SendEmail;
import com.aksoft.onlinebanking.dao.AccountDetailsDAO;
import com.aksoft.onlinebanking.dao.NewAccountDAO;
import com.aksoft.onlinebanking.dto.AccountDetailsEntity;
import com.aksoft.onlinebanking.dto.Email;
import com.aksoft.onlinebanking.dto.NewAccountEntity;

/**
 * Servlet implementation class NewAccountController
 */
@WebServlet("/NewAccountController")
public class NewAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		String docmd = request.getParameter("docmd");
		Email email=new Email();
		HttpSession pasSec=request.getSession();
		pasSec.setMaxInactiveInterval(60*15);
		if(docmd.equalsIgnoreCase("createAccount")){
			
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");
			String amount = request.getParameter("amount");
			String address = request.getParameter("adderess");
			String contact = request.getParameter("phone");
			String emaill = request.getParameter("email");
			
			NewAccountEntity accountEntity = new NewAccountEntity();
			accountEntity.setUserName(userName);
			accountEntity.setPassword(password);
			accountEntity.setRepassword(repassword);
			accountEntity.setAmount(Double.parseDouble(amount));
			accountEntity.setAddress(address);
			accountEntity.setContact(Long.parseLong(contact));
			accountEntity.setEmail(emaill);
			try {
				int acntNum = NewAccountDAO.getInstance().createNewAccount(accountEntity);
				if(acntNum > 0){
					NewAccountEntity  accountEntityy = NewAccountDAO.getInstance().findByAccountNumber(acntNum);

					request.setAttribute("accountNumber", acntNum);
					request.setAttribute("successMsg", "Congratulations your account has been Created Successfully.");
					nextPage="createAccount.jsp";
					email.setTo(emaill);
					email.setSubject("JIJAU BANK New Account Created.");
					email.setMessage("<h2>Congratulations</h2><br/>Dear "+accountEntityy.getUserName()+",<br/>Your Account has been created successfully.<br/>Account Number is : "+accountEntityy.getAccountNo()+"<br/>User Name is :"+accountEntityy.getEmail()+"<br/>Passowrd is :"+accountEntityy.getPassword()+"<br/>Current Balance is :"+accountEntityy.getAmount()+"<br/><img src='images/JijauBankPvtLtd.png' height='30' width='30'>");
				    pasSec.setAttribute("sendEmail",email);
					SendEmail obj=new SendEmail();
					String ss = obj.processRequest(request, response);
				}
			} catch (Exception e) {
				request.setAttribute("errorMsg", "Error Occured while creating account.");
				e.printStackTrace();
				nextPage="createAccount.jsp";
			}
		}else if(docmd.equalsIgnoreCase("depositeAmount")){
			String accountNumber = request.getParameter("accountno");
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			String amount = request.getParameter("amount");
			try {
				NewAccountEntity newAccountEntity = NewAccountDAO.getInstance().findByAccountNumberAndUserNameAndPswd(Integer.parseInt(accountNumber),userName,password);
				if(newAccountEntity != null && newAccountEntity.getUserName() != null && !newAccountEntity.getUserName().equals("")){
					double newAmount = Double.parseDouble(amount) + newAccountEntity.getAmount();
					String message = NewAccountDAO.getInstance().depositeAmount(Integer.parseInt(accountNumber),userName,password,newAmount);
					if(message.equals("success")){
						AccountDetailsEntity accountDetailEntity = new AccountDetailsEntity();
						accountDetailEntity.setAccountNumber(Integer.parseInt(accountNumber));
						accountDetailEntity.setAmount(Double.parseDouble(amount));
						accountDetailEntity.setAmountAction(0);

						message = AccountDetailsDAO.getInstance().insertAccountDetails(accountDetailEntity);
						
						if(message.equals("success")){
							request.setAttribute("successMsg", "Your Amount:"+amount+" Deposited Successfully in your account: "+accountNumber+"on "+ new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
							nextPage="depositBalance.jsp";
							email.setTo(userName);
							email.setSubject("JIJAU BANK Amount Deposited.");
							email.setMessage("Dear "+newAccountEntity.getUserName()+",<b> Amount : </b>"+amount+" Deposited in your account : <b>"+accountNumber+"</b><br/>"+"<b>Total Available Balance :</b>"+newAmount +"<br>on "+new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
						    pasSec.setAttribute("sendEmail",email);
							SendEmail obj=new SendEmail();
							String ss = obj.processRequest(request, response);
						}else{
							request.setAttribute("errorMsg", "Error Occured while doposite Amount.");
							nextPage="depositBalance.jsp";
						}
					}
				}else{
					request.setAttribute("errorMsg", "Username or Password wrong.Please try again.");
					nextPage="depositBalance.jsp";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(docmd!= null && docmd.equalsIgnoreCase("withdrawAmount")){
			String accountNumber = request.getParameter("accountno");
			String password = request.getParameter("password");
			String userName = request.getParameter("username");
			String amount = request.getParameter("amount");
			try {
				NewAccountEntity newAccountEntity = NewAccountDAO.getInstance().findByAccountNumberAndUserNameAndPswd(Integer.parseInt(accountNumber),userName,password);
				if(newAccountEntity != null && newAccountEntity.getUserName() != null && !newAccountEntity.getUserName().equals("")){
					double newAmount = Double.parseDouble(amount)- newAccountEntity.getAmount();
					String message = NewAccountDAO.getInstance().withdrawAmount(Integer.parseInt(accountNumber),userName,password,newAmount);
					if(message.equals("success")){
						AccountDetailsEntity accountDetailEntity = new AccountDetailsEntity();
						accountDetailEntity.setAccountNumber(Integer.parseInt(accountNumber));
						accountDetailEntity.setAmount(Double.parseDouble(amount));
						accountDetailEntity.setAmountAction(1);
						message = AccountDetailsDAO.getInstance().insertAccountDetails(accountDetailEntity);
						
						if(message.equals("success")){
							request.setAttribute("successMsg", "Your Amount:"+amount+" Withdraw Successfully in your account: "+accountNumber +"on "+ new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
							nextPage="withdrawAmount.jsp";
							email.setTo(userName);
							email.setSubject("JIJAU BANK Amount Withdraw.");
							email.setMessage("Dear "+newAccountEntity.getUserName()+",<b> Amount : </b>"+amount+" Withdraw in your account : <b>"+accountNumber+"</b><br/>"+"<b>Total Available Balance :</b>"+newAmount+"<br/>on "+ new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
						    pasSec.setAttribute("sendEmail",email);
							SendEmail obj=new SendEmail();
							String ss = obj.processRequest(request, response);
						}else{
							request.setAttribute("errorMsg", "Error Occured while withdraw Amount.");
							nextPage="withdrawAmount.jsp";
						}
					}
				}else{
					request.setAttribute("errorMsg", "Username or Password wrong.Please try again.");
					nextPage="withdrawAmount.jsp";
				}	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(docmd.equalsIgnoreCase("closeAccount")){
			String accountNumber = request.getParameter("accountno");
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			try {
				NewAccountEntity newAccountEntity = NewAccountDAO.getInstance().findByAccountNumberAndUserNameAndPswd(Integer.parseInt(accountNumber),userName,password);
				if(newAccountEntity != null && newAccountEntity.getUserName() != null && !newAccountEntity.getUserName().equals("")){
					newAccountEntity.setStatus(0);
					String message = NewAccountDAO.getInstance().updateNewAccount(newAccountEntity);
					if(message!= null && message.equals("success")){
						request.setAttribute("successMsg","Your Account "+newAccountEntity.getAccountNo()+" Closed Successfully.");
						nextPage="closeAccount.jsp";
					}
				}else{
					request.setAttribute("errorMsg","Username or Password worng.Please try again.");
					nextPage="closeAccount.jsp";
				}
				
			} catch (Exception e) {
				request.setAttribute("errorMsg","Error Occured while closing account.");
				nextPage="closeAccount.jsp";
				e.printStackTrace();
			}

		}else if(docmd != null && docmd.equalsIgnoreCase("transferAmount")){
			String accountNumber = request.getParameter("accountno");
			String taccountNumber = request.getParameter("taccountno");
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			String amount = request.getParameter("amount");
			try {
				NewAccountEntity newAccountEntity = NewAccountDAO.getInstance().findByAccountNumberAndUserNameAndPswd(Integer.parseInt(accountNumber),userName,password);
				if(newAccountEntity != null && newAccountEntity.getUserName() != null && !newAccountEntity.getUserName().equals("")){
					double newAmount = Double.parseDouble(amount)- newAccountEntity.getAmount();
					String message = NewAccountDAO.getInstance().withdrawAmount(Integer.parseInt(accountNumber),userName,password,newAmount);
					if(message != null && message.equals("success")){
						AccountDetailsEntity accountDetailEntity = new AccountDetailsEntity();
						accountDetailEntity.setAccountNumber(Integer.parseInt(accountNumber));
						accountDetailEntity.setAmount(Double.parseDouble(amount));
						accountDetailEntity.setAmountAction(1);
						message = AccountDetailsDAO.getInstance().insertAccountDetails(accountDetailEntity);
						if(message != null && message.equals("success")){
							email.setTo(userName);
							email.setSubject("JIJAU BANK Amount Withdraw.");
							email.setMessage("Dear "+newAccountEntity.getUserName()+",<b> Amount : </b>"+amount+" Withdraw in your account : <b>"+accountNumber+"</b><br/>"+"<b>Total Available Balance :</b>"+newAmount+"<br/>on "+ new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
						    pasSec.setAttribute("sendEmail",email);
							SendEmail obj=new SendEmail();
							String ss = obj.processRequest(request, response);
							NewAccountEntity targetAccountEntity = NewAccountDAO.getInstance().findByAccountNumber(Integer.parseInt(taccountNumber));
							double tamount = targetAccountEntity.getAmount();
							targetAccountEntity.setAmount(Double.parseDouble(amount)+tamount);
							message = NewAccountDAO.getInstance().updateNewAccount(targetAccountEntity);
							if(message != null && message.equals("success")){
								AccountDetailsEntity taAccountDetailEntity = new AccountDetailsEntity();
								taAccountDetailEntity.setAccountNumber(Integer.parseInt(taccountNumber));
								taAccountDetailEntity.setAmount(Double.parseDouble(amount));
								taAccountDetailEntity.setAmountAction(0);
								message = AccountDetailsDAO.getInstance().insertAccountDetails(taAccountDetailEntity);
								if(message != null && message.equals("success")){
									request.setAttribute("successMsg","amount :"+amount+" transfer Successfully From account :"+accountNumber);
									nextPage="transferAmount.jsp";
									email.setTo(userName);
									email.setSubject("JIJAU BANK Amount Deposited.");
									email.setMessage("Dear "+newAccountEntity.getUserName()+",<b> Amount : </b>"+amount+" Deposited in your account : <b>"+taccountNumber+"</b><br/>"+"<b>Total Available Balance :</b>"+newAmount +"<br>on "+new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
								    pasSec.setAttribute("sendEmail",email);
								    obj=new SendEmail();
									ss = obj.processRequest(request, response);
								}
							}
						}
					}else{
						request.setAttribute("errorMsg","Error Occured While transfer amount.Please try again.");
						nextPage="transferAmount.jsp";
					}
				}else{
					request.setAttribute("errorMsg","Username or Password worng.Please try again.");
					nextPage="transferAmount.jsp";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		request.getRequestDispatcher(nextPage).forward(request, response);
	}

}
