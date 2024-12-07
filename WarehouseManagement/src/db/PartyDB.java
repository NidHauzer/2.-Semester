package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Address;
import model.Party;

public class PartyDB implements PartyDBIF {

	DBConnection dbc = DBConnection.getInstance();
	
	public List<Party> findAll() throws SQLException {
		Connection con = dbc.getConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM Party");
		return buildObjects(rs);
	}

	public Party findByPhoneNo(String phoneNo) throws SQLException {
		Party party = null;
		
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("SELECT * FROM Party "
				+ "JOIN Address ON Party.addressId = Address.addressId "
				+ "JOIN ZipCity on Address.zip = ZipCity.zip "
				+ "WHERE phoneNo = ?");
		p.setString(1, phoneNo);
		
		try(ResultSet rs = p.executeQuery()) {
			if(rs.next()) party = buildObject(rs);
			else {
				throw new SQLException("Party was not found.");
			}
		}
		
		return party;
	}
	
	private Party buildObject(ResultSet rs) throws SQLException {
		String country = rs.getString("country");
		String city = rs.getString("city");
		String zip = rs.getString("zip");
		int houseNo = rs.getInt("houseNo");
		String streetName = rs.getString("streetName");
		
		Address address = new Address(streetName, houseNo, zip, city, country);
		
		String name = rs.getString("name");
		String phoneNo = rs.getString("phoneNo");
		
		return new Party(name, phoneNo, address);
	}
	
	private List<Party> buildObjects(ResultSet rs) throws SQLException {
		ArrayList<Party> list = new ArrayList<Party>();
		while(rs.next()) {
			list.add(buildObject(rs));
		}
		return list;
	}
	
}
