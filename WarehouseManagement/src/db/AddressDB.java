package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Address;

public class AddressDB implements AddressDBIF {
	
	DBConnection dbc = DBConnection.getInstance();
	ZipCityDBIF zipcity;

	@Override
	public List<Address> findAll() throws SQLException {
		Connection con = dbc.getConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * From Address");
		return buildObjects(rs);
	}

	@Override
	public Address findByAddressId(int addressId) throws SQLException {
		Address address = null;
		
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("SELECT * FROM Address WHERE addressId = ?");
		p.setInt(1, addressId);
		ResultSet rs = p.executeQuery();
		
		if(rs.next()) address = buildObject(rs);
		
		return address;
	}
	
	// public Address(String streetName, int houseNo, 
	// String zip, String city, String country)
	private Address buildObject(ResultSet rs) throws SQLException {
		zipcity = new ZipCityDB();
		
		String streetName = rs.getString("streetName");
		int houseNo = rs.getInt("houseNo");
		String zip = rs.getString("zip");
		String city = zipcity.findCity(zip);
		String country = rs.getString("country");
		
		return new Address(streetName, houseNo, zip, city, country);
	}
	
	private List<Address> buildObjects(ResultSet rs) throws SQLException {
		ArrayList<Address> list = new ArrayList();
		while(rs.next()) {
			list.add(buildObject(rs));
		}
		return list;
	}

}