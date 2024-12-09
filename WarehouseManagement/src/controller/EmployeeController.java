package controller;

import java.sql.SQLException;

import db.EmployeeDB;
import model.Employee;

public class EmployeeController {
	EmployeeDB edb;
	
	public Employee findEmployeeByEmployeeNo(int employeeNo) throws SQLException {
		edb = new EmployeeDB();
		return edb.findByEmployeeNo(employeeNo);
	}
}
