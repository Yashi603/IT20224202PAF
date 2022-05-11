<%@page import="model.AccountType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Types Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/accountTypes.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-6">
			<h1>Account Types Management</h1>
			<form  id="formAccountType" name="formAccountType" >
				 
				 Type: <input id="type" name="type" type="text" class="form-control form-control-sm"><br> 
				 Fixed Charge: <input id="fixedCharge" name="fixedCharge" type="text" class="form-control form-control-sm"><br> 
				 Charge For Block1: <input  id="chargeForBlock1" name="chargeForBlock1" type="text" class="form-control form-control-sm"><br> 
				 Charge For Block2: <input  id="chargeForBlock2" name="chargeForBlock2" type="text" class="form-control form-control-sm"><br>
				 Charge For Block3: <input  id="chargeForBlock3" name="chargeForBlock3" type="text" class="form-control form-control-sm"><br>
			 	<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
			 	<input type="hidden" id="hidAccTIDSave" name="hidAccTIDSave" value="">
			 	
			</form>
			
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>
			
			<div id="divItemsGrid">
				 <%
					 AccountType accTypeObj = new AccountType(); 
					 out.print(accTypeObj.readIAccountTypes()); 
				 %>
			</div>

		</div>
	</div>
</div>
</body>
</html>