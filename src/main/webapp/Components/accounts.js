$(document).ready(function() 
{ 
	if ($("#alertSuccess").text().trim() == "") 
	 { 
	 	$("#alertSuccess").hide(); 
	 } 
	 $("#alertError").hide(); 
});

//Save
$(document).on("click", "#btnSave", function(event) 
{ 
 	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide();
	 
	 // Form validation-------------------
	var status = validateAccountForm(); 
	if (status != true) 
	 { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
	 }

	// If valid------------------------
 	var type = ($("#hidAccIDSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
	 { 
		 url : "AccountAPI", 
		 type : type, 
		 data : $("#formAccount").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 	onAccountSaveComplete(response.responseText, status); 
	 	 } 
	 }); 

});

$(document).on("click", ".btnUpdate", function(event) 
{ 
 	 $("#hidAccIDSave").val($(this).data("accountid")); 
	 $("#accountNo").val($(this).closest("tr").find('td:eq(0)').text()); 
	 $("#areaOffice").val($(this).closest("tr").find('td:eq(1)').text()); 
	 $("#customerID").val($(this).closest("tr").find('td:eq(2)').text()); 
	 $("#accountTypeID").val($(this).closest("tr").find('td:eq(3)').text()); 
});

$(document).on("click", ".btnRemove", function(event) 
{ 
	 $.ajax( 
	 { 
		 url : "AccountAPI", 
		 type : "DELETE", 
		 data : "accountID=" + $(this).data("accountid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 	onAccountDeleteComplete(response.responseText, status); 
		 } 
	 }); 
});


// CLIENT-MODEL================================================================
function validateAccountForm() 
{ 
	// ACCOUNTNO
	if ($("#accountNo").val().trim() == "") 
	 { 
	 	return "Insert Account No."; 
	 } 
	// AREAOFFICE
	if ($("#areaOffice").val().trim() == "") 
	 { 
	 	return "Insert Area Office."; 
	 } 
	// CUSTOMERID-------------------------------
	if ($("#customerID").val().trim() == "") 
	 { 
	 	return "Insert customer ID."; 
	 } 
	 
	/*// is numerical value
	var tmpPrice = $("#itemPrice").val().trim(); 
	if (!$.isNumeric(tmpPrice)) 
	 { 
	 	return "Insert a numerical value for Item Price."; 
	 } 
	 
	// convert to decimal price
	 $("#itemPrice").val(parseFloat(tmpPrice).toFixed(2)); */
	 
	// DESCRIPTION------------------------
	if ($("#accountTypeID").val().trim() == "") 
	 { 
	 	return "Insert Account Type ID."; 
	 } 
	return true; 
}

function onAccountSaveComplete(response, status) 
{ 
	var resultSet = JSON.parse(response); 
	
	if (resultSet.status.trim() == "success") 
	{ 
	 	$("#alertSuccess").text("Successfully saved."); 
	 	$("#alertSuccess").show(); 
		$("#divItemsGrid").html(resultSet.data); 
	} else if (resultSet.status.trim() == "error") 
	{ 
		 $("#alertError").text(resultSet.data); 
		 $("#alertError").show(); 
	}
	else if (status == "error") 
	{ 
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
	} else
	{ 
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
	}
	$("#hidAccIDSave").val(""); 
	$("#formAccount")[0].reset();
}

function onAccountDeleteComplete(response, status) 
{ 
	if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
		 { 
			 $("#alertSuccess").text("Successfully deleted."); 
			 $("#alertSuccess").show(); 
			 $("#divItemsGrid").html(resultSet.data); 
		 } else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
	 } else if (status == "error") 
	 { 
		 $("#alertError").text("Error while deleting."); 
		 $("#alertError").show(); 
	 } else
	 { 
		 $("#alertError").text("Unknown error while deleting.."); 
		 $("#alertError").show(); 
	 } 
}