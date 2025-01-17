package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Address;
import model.Receiver;

public class ReceiverDB implements ReceiverDBIF {

	DBConnection dbc = DBConnection.getInstance();
	
	public List<Receiver> findAll() throws SQLException {
		Connection con = dbc.getConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM Party");
		return buildObjects(rs);
	}

	public Receiver findByPhoneNo(String phoneNo) throws SQLException {
		Receiver receiver = null;
		
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("SELECT * FROM Receiver "
				+ "JOIN Address ON Receiver.addressId = Address.addressId "
				+ "JOIN ZipCity on Address.zip = ZipCity.zip "
				+ "JOIN Country on ZipCity.countryID = Country.countryID "
				+ "WHERE phoneNo = ?");
		p.setString(1, phoneNo);
		
		try(ResultSet rs = p.executeQuery()) {
			if(rs.next()) receiver = buildObject(rs);
			else {
				throw new SQLException("Receiver was not found.");
			}
		}
		
		return receiver;
	}
	
	private Receiver buildObject(ResultSet rs) throws SQLException {
		String country = rs.getString("country");
		String city = rs.getString("city");
		String zip = rs.getString("zip");
		int houseNo = rs.getInt("houseNo");
		String streetName = rs.getString("streetName");
		
		Address address = new Address(streetName, houseNo, zip, city, country);
		
		String name = rs.getString("name");
		String phoneNo = rs.getString("phoneNo");
		
		return new Receiver(name, phoneNo, address);
	}
	
	private List<Receiver> buildObjects(ResultSet rs) throws SQLException {
		ArrayList<Receiver> list = new ArrayList<Receiver>();
		while(rs.next()) {
			list.add(buildObject(rs));
		}
		return list;
	}
	
}
