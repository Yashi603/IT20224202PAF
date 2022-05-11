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

@WebServlet("/AccountAPI")
public class AccountAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

    Account accObj = new Account();
    
    public AccountAPI() {
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
		
		String output = accObj.insertAccount(request.getParameter("accountNo"), 
				 request.getParameter("areaOffice"), 
				request.getParameter("customerID"), 
				request.getParameter("accountTypeID")); 
		
		response.getWriter().write(output);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		String output = accObj.updateAccount(paras.get("hidAccIDSave").toString(), 
										    paras.get("accountNo").toString(), 
											paras.get("areaOffice").toString(), 
											paras.get("customerID").toString(), 
											paras.get("accountTypeID").toString());
		
		response.getWriter().write(output); 
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request); 
		
		String output = accObj.deleteAccounts(paras.get("accountID").toString()); 
		
		response.getWriter().write(output);
	}

}
