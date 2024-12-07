package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDB implements EmployeeDBIF {
	
	DBConnection dbc = DBConnection.getInstance();

	public Employee findByEmployeeNo(int employeeNo) throws SQLException {
		Employee employee = null;
		
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("SELECT * "
				+ "FROM Employee "
				+ "WHERE employeeNo = ?");
		p.setInt(1, employeeNo);
		try (ResultSet rs = p.executeQuery()){
			if(rs.next()) employee = buildObject(rs);
			else {
				throw new SQLException("No employee was found.");
			}
		}
		
		return employee;
	}

	public List<Employee> findAll() throws SQLException {
		List<Employee> list = new ArrayList();
		
		Connection con = dbc.getConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM Employee");
		
		if(rs.next()) list = buildObjects(rs);
		else {
			throw new SQLException("No employess were found.");
		}
		
		return list;
		
	}
	
	private Employee buildObject(ResultSet rs) throws SQLException {
		String name = rs.getString("name");
		int employeeNo = rs.getInt("employeeNo");
		
		return new Employee(name, employeeNo);
	}
	
	private List<Employee> buildObjects(ResultSet rs) throws SQLException {
		List<Employee> list = new ArrayList();
		
		do {
			list.add(buildObject(rs));
		} while(rs.next());
		
		return list;
	}
	
}
