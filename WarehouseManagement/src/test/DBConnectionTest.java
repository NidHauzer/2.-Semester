package test;
import db.DBConnection;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DBConnectionTest {
	
	static DBConnection dbc;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dbc = DBConnection.getInstance();
	}

	//Test if connection is null:
	//	If it is null the connection has failed.
	@Test
	void testConnection() {
		Connection con = dbc.getConnection();
		assertNotNull(con);
	}
	
	//Simple test for testing the "assertNotNull" function. Should fail
	@Test
	void testConnectionNull() {
		Connection con = null;
		assertNotNull(con);
	}

}
