package com.bocxy.landDigit.core.landDigitV2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Loggedinimp {
	
	 String myUrl ="jdbc:mysql://tnhb-landdigit.cvk3pspk8bzn.ap-south-1.rds.amazonaws.com/landdigit_db";
	 String root="root";
	 String pswd="yazhini1998";
	
	public String Updateloggedin(String username) throws Exception
	
	{		 
		Connection conc = DriverManager.getConnection(myUrl, root, pswd);
		String query =  "UPDATE landdigit_db.users SET loggedin = CASE loggedin  WHEN "+"'"+0+"'" +" THEN "+"'"+1+"'" +" ELSE "+"'"+2+"'" +" END  where username = "+"'"+username+"'";
		Statement stm = conc.createStatement();
		int rset=stm.executeUpdate(query);
		return username;
		}
	
	public String Updateloggedout(String username) throws Exception
	{
		Connection conc = DriverManager.getConnection(myUrl, root, pswd);
	    String query = "Update landdigit_db.users set loggedin= "+"'"+0+"'" +" where username="+"'"+username+"'";
	    Statement stm = conc.createStatement();
	    int rset=stm.executeUpdate(query);
		return username;
	}

}