package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
	/*
	 * USE BELOW METHOD FOR YOUR DATABASE CONNECTION FOR BOTH SINGLE AND MULTIPLE
	 * SQL SERVER INSTANCE(s) DO NOT EDIT THE BELOW METHOD, YOU MUSE USE ONLY THIS
	 * ONE FOR YOUR DATABASE CONNECTION
	 */
	public Connection getConnection () throws Exception {
		String url = "jdbc:mysql://" + serverName + ":" + portNumber + "\\" + instance + "/" + dbName;
		if (instance==null || instance.trim().isEmpty())
			url = "jdbc:mysql://" + serverName+ ":" + portNumber + "/" + dbName;
		Class.forName("com.mysql.jdbc.Driver");  
		return DriverManager.getConnection(url,userID,password);
	}
	
	
//	public Connection getConnection () throws Exception {
//		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName+";encrypt=false";
//		if (instance==null || instance.trim().isEmpty())
//			url = "jdbc:sqlserver://" + serverName+ ":" + portNumber + ";databaseName=" + dbName + ";user=" +userID+ ";password=" +password+";encrypt=false";
//		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
//		return DriverManager.getConnection(url,userID,password);
//	}
	/* Insert your other code right after this comment */
	
	
	
	
	/*
	 * Change/update information of your database connection, DO NOT change name of
	 * instance variables in this class
	 */
//	private final String serverName = "localhost";
//	private final String dbName = "ShoppingDB";
//	private final String portNumber = "1433";
//	private final String instance = ""; 	//LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
//	private final String userID="sa";
//	private final String password="123456";
	
	private final String serverName = "remotemysql.com";
	private final String dbName = "4I7eTt6Ueo";
	private final String portNumber = "3306";
	private final String instance = ""; 	//LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
	private final String userID="4I7eTt6Ueo";
	private final String password="YK2CecR2Qm";
}
