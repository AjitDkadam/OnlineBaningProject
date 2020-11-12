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
<script LANGUAGE="JavaScript" src="js/script.js"></script>
<script language="javascript">
	
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
	
	function requiredField(){
		var isValid=false;
		var uname=document.getElementById('username').value;
		if(uname == ""){
			alert("Please Enter Required Data to proceed");
			changeStyleForTextFiled(document.getElementById('username'),false);
		}
		return isValid;
	}
	
	function createAccount(){
		var uname=document.getElementById('username').value;
		var password=document.getElementById('password').value;
		var repassword=document.getElementById('repassword').value;
		var amount=document.getElementById('amount').value;
		var address=document.getElementById('adderess').value;
		var phone=document.getElementById('phone').value;
		var email=document.getElementById('email').value;
		if(uname != "" || password != "" || repassword != "" || amount != "" || address != "" || phone != ""|| email != ""){
			if(password == repassword){
				if(amount > 500){
					document.createAccountForm.submit();
				}else{
					alert("The Amount must be greater than 500")
				}
			}else{
				document.getElementById('repassword').value="";
				document.getElementById('repassword').style.backgroundColor = "#a0d8de";
				document.getElementById('repassword').focus();
				alert("Password and Re-Entered Password does not match");
			}
		}else{
			alert("Please Enter required data to proceed");
			document.getElementById('username').style.backgroundColor = "#a0d5de";
			document.getElementById('password').style.backgroundColor = "#a0d5de";
			document.getElementById('repassword').style.backgroundColor = "#a0d5de";
			document.getElementById('amount').style.backgroundColor = "#a0d5de";
			document.getElementById('adderess').style.backgroundColor = "#a0d5de";
			document.getElementById('phone').style.backgroundColor = "#a0d5de";
			document.getElementById('email').style.backgroundColor = "#a0d5de";
		}
	}
	
</script>



<body>

<div id="top_links">
<jsp:include page="header.jsp"></jsp:include>  


<table style="width:890px; background:#FFFFFF; margin:0 auto;" cellpadding="0" cellspacing="0">
<tr align="justify">
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
    	<div id="welcome" style="border-right:#666666 1px dotted;"><h1>OPEN ACCOUNT FORM</h1><br>
    	    <table  align="center" bgcolor="white">
		
					<tr>
						<td>
							<form name="createAccountForm" id="createAccountForm" action="NewAccountController" >
							  
							  <table cellspacing="5" cellpadding="3">	
							
								<tr>
									<td><span class="labelHeading">User Name</span></td>
									<td> 
										<input type="text" name="username" id="username" >
									</td>
								</tr>
								<tr>
									<td><span class="labelHeading">Password</span></td>
									<td> 
										<input type="password" name="password" id="password">
									</td>
								</tr>
								<tr>
									<td><span class="labelHeading">Re-Password</span></td>
									<td> 
										<input type="password" name="repassword" id="repassword">
									</td>
								</tr>
								<tr>
									<td><span class="labelHeading">Amount</span></td>
									<td> 
										<input type="text" name="amount" id="amount">
									</td>
								</tr>
								<tr>
									<td><span class="labelHeading">Address:</span></td>
									<td> 
										<input type="text" name="adderess" id="adderess">
									</td>
								</tr>
								<tr>
									<td><span class="labelHeading">Phone:</span></td>
									<td> 
										<input type="text" name="phone" id="phone">
									</td>
								</tr>
								<tr>
									<td><span class="labelHeading">Email:</span></td>
									<td> 
										<input type="email" name="email" id="email">
									</td>
								</tr>
								<tr>	<td></td>
										<td>
											<input type="hidden" name="docmd" value="createAccount">
											<input type="button" class="btn01" value="Submit" onclick="javascript:createAccount();"/>
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
</tr>
</table>
    
	<div id="footer_top">

 		 <div id="footer_copyright" >
 			<p>Each people plan their site layouts depending upon their business type. Here comes a free designer template which provides you with a selection of different kinds of webdesign   starting from business template, fashion template, media template, Science template, Arts template and much more.</p>
	  		   Copyright © <span class="labelHeading">AKSoftware Solutions PVT.LTD Manjari,Hadapsar,Pune.</span>
 		</div>
	</div>
</div>

</body>
</html>

