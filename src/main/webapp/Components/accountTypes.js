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
	var status = validateAccountTypeForm(); 
	if (status != true) 
	 { 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
	 }

	// If valid------------------------
 	var type = ($("#hidAccTIDSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
	 { 
		 url : "AccountTypeAPI", 
		 type : type, 
		 data : $("#formAccountType").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 	onAccountSaveComplete(response.responseText, status); 
	 	 }  
	 }); 

});

$(document).on("click", ".btnUpdate", function(event) 
{ 
 	 $("#hidAccTIDSave").val($(this).data("accounttypeid")); 
	 $("#type").val($(this).closest("tr").find('td:eq(0)').text()); 
	 $("#fixedCharge").val($(this).closest("tr").find('td:eq(1)').text()); 
	 $("#chargeForBlock1").val($(this).closest("tr").find('td:eq(2)').text()); 
	 $("#chargeForBlock2").val($(this).closest("tr").find('td:eq(3)').text()); 
	 $("#chargeForBlock3").val($(this).closest("tr").find('td:eq(4)').text());
});

$(document).on("click", ".btnRemove", function(event) 
{ 
	 $.ajax( 
	 { 
		 url : "AccountTypeAPI", 
		 type : "DELETE", 
		 data : "accountTypeID=" + $(this).data("accounttypeid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 	onAccountTSaveComplete(response.responseText, status); 
		 } 
	 }); 
});


// CLIENT-MODEL================================================================
function validateAccountTypeForm() 
{ 
	// Type
	if ($("#type").val().trim() == "") 
	 { 
	 	return "Insert Account Type."; 
	 } 
	// Fixed Charge
	if ($("#fixedCharge").val().trim() == "") 
	 { 
	 	return "Insert fixed charge."; 
	 } 
	// charge for block 1
	if ($("#chargeForBlock1").val().trim() == "") 
	 { 
	 	return "Insert charge for block 1."; 
	 }
	 // charge for block 2
	if ($("#chargeForBlock2").val().trim() == "") 
	 { 
	 	return "Insert charge for block 2. If no charges then type 0.00"; 
	 }
	 // charge for block 3
	if ($("#chargeForBlock3").val().trim() == "") 
	 { 
	 	return "Insert charge for block 3. If no charges then type 0.00"; 
	 } 
	 
	 
	// is numerical value
	var fCharge = $("#fixedCharge").val().trim(); 
	if (!$.isNumeric(fCharge)) 
	 { 
	 	return "Insert a numerical value for fixed charge."; 
	 }
	 var fCharge = $("#fixedCharge").val().trim(); 
	if (!$.isNumeric(fCharge)) 
	 { 
	 	return "Insert a numerical value for fixed charge."; 
	 }
	 var chargeB1 = $("#chargeForBlock1").val().trim(); 
	if (!$.isNumeric(chargeB1)) 
	 { 
	 	return "Insert a numerical value for charge foe block 1."; 
	 } 
	 var chargeB2 = $("#chargeForBlock2").val().trim(); 
	if (!$.isNumeric(chargeB2)) 
	 { 
	 	return "Insert a numerical value for charge foe block 2."; 
	 } 
	 var chargeB3 = $("#chargeForBlock3").val().trim(); 
	if (!$.isNumeric(chargeB3)) 
	 { 
	 	return "Insert a numerical value for charge foe block 3."; 
	 } 

	 
	// convert to decimal price
	 $("#fixedCharge").val(parseFloat(fCharge).toFixed(2));
	 $("#chargeForBlock1").val(parseFloat(chargeB1).toFixed(2));
	 $("#chargeForBlock2").val(parseFloat(chargeB2).toFixed(2));
	 $("#chargeForBlock2").val(parseFloat(chargeB2).toFixed(2)); 
	 
	/*// DESCRIPTION------------------------
	if ($("#accountTypeID").val().trim() == "") 
	 { 
	 	return "Insert Account Type ID."; 
	 } */
	return true; 
}

function onAccountTSaveComplete(response, status) 
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
	$("#hidAccTIDSave").val(""); 
	$("#formAccountType")[0].reset();
}

function onAccountTDeleteComplete(response, status) 
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