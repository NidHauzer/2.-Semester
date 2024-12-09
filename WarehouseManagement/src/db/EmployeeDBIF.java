package db;

import java.sql.SQLException;
import java.util.List;

import model.Employee;

public interface EmployeeDBIF {
	public Employee findByEmployeeNo(int employeeNo) throws SQLException;
	public List<Employee> findAll() throws SQLException;
}
