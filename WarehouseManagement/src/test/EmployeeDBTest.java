package test;

import java.sql.SQLException;

import db.EmployeeDB;
import model.Employee;

public class EmployeeDBTest {
	public static void main(String args[]) throws SQLException {
		EmployeeDB edb = new EmployeeDB();
		
		Employee employee = edb.findByEmployeeNo(1);
		
		System.out.println(employee.getName());
		
		for(Employee e : edb.findAll()) {
			System.out.println(e.getName());
		}
	}
}
