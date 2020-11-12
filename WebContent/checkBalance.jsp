<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.aksoft.onlinebanking.dao.NewAccountDAO"%>
<%@page import="com.aksoft.onlinebanking.dto.NewAccountEntity"%>
<html>
<%
	String successMsg =(String) request.getAttribute("successMsg");
	String errorMsg =(String) request.getAttribute("errorMsg");
	String docmd = request.getParameter("docmd");
	NewAccountEntity accountEntity = null;
	if(docmd != null && docmd.equalsIgnoreCase("checkBalance")){
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String accountNumber = request.getParameter("accountno");
		try{
			accountEntity = NewAccountDAO.getInstance().findByAccountNumberAndUserNameAndPswd(Integer.parseInt(accountNumber), userName, password);
			if(accountEntity != null && accountEntity.getUserName() != null && accountEntity.getAmount() >0 && accountEntity.getAccountNo() > 0){
				successMsg = "Your Account Details is..";
			}else{
				errorMsg = "Please Enter Correct Details to proceed.";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="images/aklogo.ico" />
<title>JIJAU Banking</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<script language="javascript">

	function requiredFiled(){
		var uname=document.getElementById('username').value;
		var password=document.getElementById('password').value;
		var accountNo=document.getElementById('accountno').value;
		var isValid = false;
		if(uname == "" || password == "" || accountNo == ""){
			alert("Please Enter required data to proceed");
			document.getElementById('username').style.backgroundColor = "#a0d5de";
			document.getElementById('password').style.backgroundColor = "#a0d5de";
			document.getElementById('accountno').style.backgroundColor = "#a0d5de";
		}else{
			isValid = true;
		}
		return isValid;
	}
	
	
	function checkBalance(){
		if(requiredFiled()){
			document.checkBalanceForm.submit();
		}
	}
	
	function getTransactionDetails(acntNo){
		alert("acntNo-----"+acntNo);
		document.getAccountDtlForm.accountno.value = acntNo;
		document.getAccountDtlForm.submit();
	}
	
</script>
<body>
<form name="getAccountDtlForm" id="getAccountDtlForm" action="ForwardActionController" method="post" target="_new">
	<input type="hidden" name="accountno" value="">
	<input type="hidden" name="nextPage" value="miniStatement.jsp">
</form>
<div id="top_links">
  
<jsp:include page="header.jsp"></jsp:include>
<table style="width:897px; background:#FFFFFF; margin:0 auto;">
<tr >
	<td width="300" valign="top" style="border-right:#666666 1px dotted;">
    	<div id="services"><h1>Services</h1><br>
		    <ul>
        		<li><a href="#">Business loans.</a></li>
	            <li><a href="#">Checking accounts. </a></li>
	            <li><a href="#">Savings accounts.</a></li>
	            <li><a href="#">Debit and credit cards.</a></li>
	            <li><a href="#">Cash management.</a></li>
	            <li><a href="#">Merchant services </a></li>
            </ul>
			<span style="font-size: 10px;">(credit card processing, reconciliation and reporting, check collection).</span>
			
       </div>
	</td>
    <%
    	if(accountEntity != null){
    %>
    
    <td valign="top">
    	<table width="100%" style="border-collapse:collapse;" border="1">
    			<thead>
    				<tr bgcolor="#eeeeee">
    					<th>User Name</th>
    					<th>Account Number</th>
    					<th>Available Balance</th>
    				</tr>
    				<tr><td align="center" colspan="3"><span class="labelHeading"><%=errorMsg!= null ? errorMsg :"* Account Information *" %></span></td></tr>
    			</thead>
    			<tbody>
    				<tr>
    					<td align="center"><%=accountEntity!= null && accountEntity.getUserName() != null  ? accountEntity.getUserName():"" %></td>
    					<td align="center"><%=accountEntity!= null  && accountEntity.getAccountNo() > 0 ? accountEntity.getAccountNo():"" %></td>
    					<td align="right"><%=accountEntity!= null && accountEntity.getAmount() >0 ? accountEntity.getAmount(): "" %></td>
    				</tr>
    				<tr>
    					<td colspan="3" align="center"><INPUT TYPE=button class="btn01" VALUE="Get Transaction Details" onclick="javascript:getTransactionDetails('<%=accountEntity!= null  && accountEntity.getAccountNo() > 0 ? accountEntity.getAccountNo():"" %>');"></td>
    				</tr>
    			</tbody>
    	</table>
    </td>
    
    <%}else{ %>
    <td width="1200" valign="top">
    	<div id="welcome" style="border-right:#666666 1px dotted;"><h1>BALANCE FORM</h1><br>
    	    <table  align="center" bgcolor="white">
		<tr>
				
		</tr>
		<tr>
			<td>
				
				<form name="checkBalanceForm" id="checkBalanceForm" action="ForwardActionController" method="post">
				   <input type="hidden" name="docmd" value="checkBalance">
				     <input type="hidden" name="nextPage" value="checkBalance.jsp">
				   <table cellspacing="5" cellpadding="3">	
						  <tr>
						  	<td>
						  		<span class="labelHeading">Account No:</span>
						  	</td>
						  	<td> <input type="text" name="accountno" id="accountno" /></td>
						  </tr>
						  <tr>
						  		<td><span class="labelHeading">User Name:</span></td>
						  		<td> <input type="text" name="username" id="username" /></td>
						 </tr>
						 <tr>
						 		<td><span class="labelHeading">Password:</span></td>
						 		<td> <input type="password" name="password" id="password" /></td>
						 </tr>
						 <tr>
						 		<td></td>
						 		<td>
						 			<input type="button" class="btn01" value="Submit" onclick="javascript:checkBalance();"/>
									<INPUT TYPE=RESET class="btn01" VALUE="CLEAR">
								</td>
						</tr>
             	</table>
				</form>
			</td>
		</tr>
	</table>
    	   </div>      
    </td>
    
    <td width="299" valign="top">
    	<div id="welcome" style="border-right:#666666 1px dotted;"><h1>Welcome</h1><br>
    	    <center><img src="images/globe_10.gif" alt="business" width="196" height="106"></center><br>
		    <p>Each people plan their site layouts depending upon their business type. Here comes a free designer template which provides you with a selection of different kinds of webdesign   starting from business template, fashion template, media template, Science template, Arts template and much more.</p>
	    	
	    </div>      
    </td>
            	
    <%} %>
</tr></table>
    
<div id="footer_top">

 	 <div id="footer_copyright" >
 
		    <p>Each people plan their site layouts depending upon their business type. Here comes a free designer template which provides you with a selection of different kinds of webdesign starting from business template, fashion template, media template, Science template, Arts template and much more.</p>
			    Copyright © <span class="labelHeading">AKSoftware Solutions PVT.LTD Manjari,Hadapsar,Pune.</span>
  	</div>
</div>

</div>
</body>
</html>
