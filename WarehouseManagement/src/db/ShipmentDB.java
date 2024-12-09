package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Address;
import model.Employee;
import model.Party;
import model.Shipment;

public class ShipmentDB implements ShipmentDBIF {

	DBConnection dbc = DBConnection.getInstance();
	PartyDB pdb;
	
	//Method to get ID from INSERT statement taken from:
	//https://stackoverflow.com/questions/1915166/how-to-get-the-insert-id-in-jdbc/1915197#1915197
	
	public Shipment create(Shipment s) throws SQLException {
		Shipment shipment = s;
		
		String phoneNo = s.getParty().getPhoneNo();
		LocalDate date = s.getDate();
		int employeeNo = s.getEmployee().getEmployeeNo();
		
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("INSERT INTO Shipment VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		p.setString(1, phoneNo);
		p.setDate(2, Date.valueOf(date));
		p.setInt(3, employeeNo);
		
		//gets the number of affected rows of the statement
		int affectedRows = p.executeUpdate();
		
		//if the number of affected rows is 0, no shipment was created
		if(affectedRows == 0) throw new SQLException("Creating shipment failed, no rows affected.");
		
		//get the newly made shipmentNo and set it on the shipment object that is to be returned
		//if no shipmentNo was found, nothing was created in the database
		try(ResultSet rs = p.getGeneratedKeys()){
			if(rs.next()) shipment.setShipmentNo(rs.getInt(1)); 
			else {
				throw new SQLException("Creating shipment failed, no shipment number obtained.");
			}
		}
		
		return shipment;
	}

	@Override
	public List<Shipment> findAll() throws SQLException {
		List<Shipment> list;
		
		Connection con = dbc.getConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT *, Employee.name AS EmployeeName, Party.name AS PartyName "
				+ "FROM Shipment "
				+ "JOIN Employee ON Shipment.employeeNo = Employee.employeeNo "
				+ "JOIN Party ON Shipment.phoneNo = Party.phoneNo "
				+ "JOIN Address ON Party.addressId = Address.addressId "
				+ "JOIN ZipCity ON Address.zip = ZipCity.zip");
		if(rs.next()) list = buildObjects(rs);
		else {
			throw new SQLException("No shipments were found.");
		}
		
		return list;
	}

	@Override
	public Shipment findByShipmentNo(int shipmentNo) throws SQLException {
		Shipment shipment = null;
		
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("SELECT *, Employee.name AS EmployeeName, Party.name AS PartyName "
				+ "FROM Shipment "
				+ "JOIN Employee ON Shipment.employeeNo = Employee.employeeNo "
				+ "JOIN Party ON Shipment.phoneNo = Party.phoneNo "
				+ "JOIN Address ON Party.addressId = Address.addressId "
				+ "JOIN ZipCity ON Address.zip = ZipCity.zip "
				+ "WHERE shipmentNo = ?");
		p.setInt(1, shipmentNo);
		
		try(ResultSet rs = p.executeQuery()) {
			if(rs.next()) shipment = buildObject(rs);
			else {
				throw new SQLException("No shipment was found.");
			}
		}
		
		return shipment;
	}
	
	public Shipment buildObject(ResultSet rs) throws SQLException {
		pdb = new PartyDB();
		
		String employeeName = rs.getString("EmployeeName");
		int employeeNo = rs.getInt("employeeNo");
		
		Employee employee = new Employee(employeeName, employeeNo);

		String country = rs.getString("country");
		String streetName = rs.getString("streetName");
		int houseNo = rs.getInt("houseNo");
		String zip = rs.getString("zip");
		String city = rs.getString("city");
		
		Address address = new Address(streetName, houseNo, zip, city, country);
		
		String partyName = rs.getString("PartyName");
		String phoneNo = rs.getString("phoneNo");
		
		Party party = new Party(partyName, phoneNo, address);
		
		int shipmentNo = rs.getInt("shipmentNo");
		LocalDate date = rs.getDate("date").toLocalDate();
		
		return new Shipment(party, date, employee, shipmentNo);
	}
	
	public List<Shipment> buildObjects(ResultSet rs) throws SQLException {
		List<Shipment> list = new ArrayList<Shipment>();
		do {
			list.add(buildObject(rs));
		} while(rs.next());
		return list;
	}

}
