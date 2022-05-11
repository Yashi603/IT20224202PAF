package com;

import model.AccountType;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/AccountTypes") 
public class AccountTypeService {
	
	AccountType accTypeObj = new AccountType(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	{ 
		return accTypeObj.readIAccountTypes(); 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertAccountType(@FormParam("type") String type, 
							 @FormParam("fixedCharge") String fixedCharge, 
							 @FormParam("chargeForBlock1") String chargeForBlock1, 
							 @FormParam("chargeForBlock2") String chargeForBlock2,
							 @FormParam("chargeForBlock3") String chargeForBlock3) 
	{ 
		String output = accTypeObj.insertAccountType(type, fixedCharge, chargeForBlock1, chargeForBlock2,chargeForBlock3); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateAccountType(String accountTypeData) 
	{ 
		 //Convert the input string to a JSON object 
		 JsonObject accTypeObject = new JsonParser().parse(accountTypeData).getAsJsonObject(); 
		 
		 //Read the values from the JSON object
		 String accountTypeID = accTypeObject.get("accountTypeID").getAsString(); 
		 String type = accTypeObject.get("type").getAsString(); 
		 String fixedCharge = accTypeObject.get("fixedCharge").getAsString(); 
		 String chargeForBlock1 = accTypeObject.get("chargeForBlock1").getAsString(); 
		 String chargeForBlock2 = accTypeObject.get("chargeForBlock2").getAsString(); 
		 String chargeForBlock3 = accTypeObject.get("chargeForBlock3").getAsString(); 
		 
		 String output = accTypeObj.updateAccountType(accountTypeID, type, fixedCharge, chargeForBlock1, chargeForBlock2,chargeForBlock3); 
		 return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteAccountTypes(String accTypeData) 
	{ 
		 //Convert the input string to an XML document
		 Document doc = Jsoup.parse(accTypeData, "", Parser.xmlParser()); 
		 
		 //Read the value from the element <itemID>
		 String accountTypeID = doc.select("accountTypeID").text(); 
		 
		 String output = accTypeObj.deleteAccountTypes(accountTypeID); 
		 return output; 
	}
}
