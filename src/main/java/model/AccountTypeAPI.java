package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


@WebServlet("/AccountTypeAPI")
public class AccountTypeAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    AccountType accTypeObj = new AccountType();
	
    public AccountTypeAPI() {
        super();
        
    }
    
    private static Map getParasMap(HttpServletRequest request) 
   	{ 
   		 Map<String, String> map = new HashMap<String, String>(); 
   		 try
   		 { 
   			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
   			 String queryString = scanner.hasNext() ? 
   			 scanner.useDelimiter("\\A").next() : ""; 
   			 
   			 scanner.close(); 
   			 
   			 String[] params = queryString.split("&"); 
   			 for (String param : params) 
   			 { 
   				 String[] p = param.split("="); 
   				 map.put(p[0], p[1]); 
   			 } 
   		 } 
   		catch (Exception e) 
   		{ 
       	} 
   		return map; 
   	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = accTypeObj.insertAccountType(request.getParameter("type"), 
				 request.getParameter("fixedCharge"), 
				request.getParameter("chargeForBlock1"), 
				request.getParameter("chargeForBlock2"),
				request.getParameter("chargeForBlock3")); 
		
		response.getWriter().write(output);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		String output = accTypeObj.updateAccountType(paras.get("hidAccTIDSave").toString(), 
										    paras.get("type").toString(), 
											paras.get("fixedCharge").toString(), 
											paras.get("chargeForBlock1").toString(), 
											paras.get("chargeForBlock2").toString(),
											paras.get("chargeForBlock3").toString());
		
		response.getWriter().write(output);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request); 
		
		String output = accTypeObj.deleteAccountTypes(paras.get("accountTypeID").toString()); 
		
		response.getWriter().write(output);
	}

}
