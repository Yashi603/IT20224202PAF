<%@page import="model.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accounts Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/accounts.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-6">
			<h1>Accounts Management</h1>
			<form  id="formAccount" name="formAccount" >
				 Account No: <input id="accountNo" name="accountNo" type="text" class="form-control form-control-sm"><br> 
				 Area Office: <input id="areaOffice" name="areaOffice" type="text" class="form-control form-control-sm"><br> 
				 Customer ID: <input id="customerID" name="customerID" type="text" class="form-control form-control-sm"><br> 
				 Account Type ID: <input  id="accountTypeID" name="accountTypeID" type="text" class="form-control form-control-sm"><br> 
			 	<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
			 	<input type="hidden" id="hidAccIDSave" name="hidAccIDSave" value="">
			 	
			</form>
			
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>
			
			<div id="divItemsGrid">
				 <%
					 Account accObj = new Account(); 
					 out.print(accObj.readIAccounts()); 
				 %>
			</div>

		</div>
	</div>
</div>
</body>
</html>