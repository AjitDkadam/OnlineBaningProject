<%@page import="com.aksoft.onlinebanking.dao.NewAccountDAO"%>
<%@page import="com.aksoft.onlinebanking.dto.NewAccountEntity"%>
<%@page import="com.aksoft.onlinebanking.dto.AccountDetailsEntity"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.aksoft.onlinebanking.dao.AccountDetailsDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String accountNo = request.getParameter("accountno");
List transactionDetailsList = null;
NewAccountEntity newAccountEntity = null;
if(accountNo != null && !accountNo.equals("")){
	newAccountEntity = NewAccountDAO.getInstance().findByAccountNumber(Integer.parseInt(accountNo));
	transactionDetailsList = AccountDetailsDAO.getInstance().findByAccountNoAcntDtl(Integer.parseInt(accountNo));
}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="images/aklogo.ico" />
<title>Mini Statement</title>
</head>
<body>
<table width="60%" style="border-collapse:collapse;margin-left: 15%;" border="1">
	<tr>
		<td width="10%" align="center"><img src="images/JijauBankLogo.png" height="40%" width="100%"></td>
		<td width="70%" align="center" colspan="2"><h1>Transaction Details</h1></td>
	</tr>
	<tr>
		<td><b>Branch:</b>Manjari</td>
		<td><b>Account No : </b><%=accountNo != null ? accountNo:"" %></td>
		<td><b>Name : <%=newAccountEntity != null ? newAccountEntity.getUserName():"" %></b></td>
	</tr>
</table>
<table width="60%" style="border-collapse:collapse;margin-left: 15%;" border="1">
	<tr>
			<th>Sr.No</th>
			<th>Amount</th>
			<th>Date</th>
			<th>Action</th>
	</tr>
	<%
		if(transactionDetailsList != null && transactionDetailsList.size()>0){
			Iterator itr = transactionDetailsList.iterator();
			int i=1;
			while(itr.hasNext()){
				AccountDetailsEntity accountDtlEntity = (AccountDetailsEntity)itr.next();
	%>
	<tr>
			<td align="center"><%=i %></td>
			<td align="center"><%=accountDtlEntity != null ? accountDtlEntity.getAmount():""%></td>
			<td align="center"><%=accountDtlEntity != null ? accountDtlEntity.getDateCreated():""%></td>
			<td align="center"><%=accountDtlEntity != null ? accountDtlEntity.getAmountAction()==0 ? "Deposited" :"Withdraw" :""%></td>
	</tr>
	
	<%  i++;
			}
		}
	%>
</table>
</body>
</html>