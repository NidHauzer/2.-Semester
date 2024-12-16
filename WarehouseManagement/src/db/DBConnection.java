package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static DBConnection instance;
	private Connection connection;
	
	// Hildur - change username and dbname for your own database before use!
	private static final String DBNAME = "DMA-CSD-V24_10519146";
	private static final String SERVERNAME = "hildur.ucn.dk";
	private static final int PORTNUMBER = 1433;
	private static final String USERNAME = "DMA-CSD-V24_10519146";
	private static final String PASSWORD = "Password1!";

	
	// Local Database
//	private static final String DBNAME = "WarehouseManagement";
//	private static final String SERVERNAME = "localhost";
//	private static final int PORTNUMBER = 1433;
//	private static final String USERNAME = "sa";
//	private static final String PASSWORD = "Password1";

	private DBConnection() {
		String urlString = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;encrypt=false", SERVERNAME, PORTNUMBER, DBNAME);
		
		try {
			connection = DriverManager.getConnection(urlString, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("There was a problem with the connection");
		}
	}
	
	public static synchronized DBConnection getInstance() {
		if(instance == null) instance = new DBConnection();
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
}
