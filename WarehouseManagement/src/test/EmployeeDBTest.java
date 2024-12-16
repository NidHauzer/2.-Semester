package test;

import db.*;
import model.Employee;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class EmployeeDBTest {
	
	static EmployeeDBIF edb;
	
	@BeforeAll 
	static public void beforeAll() {
		edb = new EmployeeDB();
	}
	
	@Test
	void findEmployeeTest() throws SQLException {
		Employee e = edb.findByEmployeeNo(2);
		assertEquals(e.getName(), "Niels Christian");
	}

}