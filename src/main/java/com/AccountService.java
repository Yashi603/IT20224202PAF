package com;

import model.Account;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Accounts") 
public class AccountService {
	
	Account accObj = new Account(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	{ 
		return accObj.readIAccounts(); 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertAccount(@FormParam("accountNo") String accountNo, 
							 @FormParam("areaOffice") String areaOffice, 
							 @FormParam("customerID") String customerID, 
							 @FormParam("accountTypeID") String accountTypeID) 
	{ 
		String output = accObj.insertAccount(accountNo, areaOffice, customerID, accountTypeID); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateAccountType(String accountData) 
	{ 
		 //Convert the input string to a JSON object 
		 JsonObject accObject = new JsonParser().parse(accountData).getAsJsonObject(); 
		 
		 //Read the values from the JSON object
		 String accountID = accObject.get("accountID").getAsString(); 
		 String accountNo = accObject.get("accountNo").getAsString(); 
		 String areaOffice = accObject.get("areaOffice").getAsString(); 
		 String customerID = accObject.get("customerID").getAsString(); 
		 String accountTypeID = accObject.get("accountTypeID").getAsString();
		 
		 String output = accObj.updateAccount(accountID, accountNo, areaOffice, customerID, accountTypeID); 
		 return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteAccounts(String accData) 
	{ 
		 //Convert the input string to an XML document
		 Document doc = Jsoup.parse(accData, "", Parser.xmlParser()); 
		 
		 //Read the value from the element <itemID>
		 String accountID = doc.select("accountID").text(); 
		 
		 String output = accObj.deleteAccounts(accountID); 
		 return output; 
	}
}