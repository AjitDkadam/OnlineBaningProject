<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String successMsg =(String) request.getAttribute("successMsg");
	String errorMsg =(String) request.getAttribute("errorMsg");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="images/aklogo.ico" />
<title>JIJAU Banking</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<script language="javascript" src="js/script.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<%
if(successMsg != null && !successMsg.equals("")){
%>
	alert("<%=successMsg%>");
<%
}else if(errorMsg != null && !errorMsg.equals("")){
%>
	alert("<%=errorMsg%>");
<%
}
%>
function reqField(){
	var isValid = false;
	var accountNumber = document.getElementById('accountno').value;
	var userName = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	if(accountNumber != "" || userName != "" || password != ""){
		isValid = true;
	}else{
		alert("Please Enter required data to proceed.");
		changeStyleForTextFiled(document.getElementById('accountno'),false);
		changeStyleForTextFiled(document.getElementById('username'),false);
		changeStyleForTextFiled(document.getElementById('password'),false);
		var isValid = false;
	}
	return isValid;
}

function closeAccount(){
	if(reqField()){
		document.closeAccountForm.submit();
	}
}

</SCRIPT>
<body>

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
    
    <td width="1200" valign="top">
    	<div id="welcome" style="border-right:#666666 1px dotted;"><h1>CLOSE ACCOUNT FORM</h1><br>
    	    <table  align="center" bgcolor="white">
		<tr>
				
		</tr>
		<tr>
			<td>
				<form name="closeAccountForm" id="closeAccountForm" action="NewAccountController" method="post" >
				  <input type="hidden" name="docmd" value="closeAccount">
				  <table cellspacing="5" cellpadding="3">
				    <tr>
				    		<td><span class="labelHeading">Account No:</span></td>
				    		<td> <input type="text" name="accountno" id="accountno"></td>
				    </tr>
					<tr>
							<td><span class="labelHeading">User Name:</span></td>
							<td> <input type="text" name="username" id="username"></td>
					</tr>
					<tr>
							<td><span class="labelHeading">Password:</span></td>
							<td> <input type="password" name="password" id="password"></td>
					</tr>
					<tr>
							<td></td>
							<td>
							<input type="button" class="btn01" value="Submit" onclick="javascript:closeAccount();">
							<INPUT TYPE=RESET class="btn01"  VALUE="CLEAR">
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
   
            	
    
</tr></table>
    
	<div id="footer_top">
  		<div id="footer_copyright" >
 			 <p>Each people plan their site layouts depending upon their business type. Here comes a free designer template which provides you with a selection of different kinds of webdesign   starting from business template, fashion template, media template, Science template, Arts template and much more.</p>
	  		   Copyright © <span class="labelHeading">AKSoftware Solutions PVT.LTD Manjari,Hadapsar,Pune.</span>
	  	</div>
	</div>

</div>

</body>
</html>
