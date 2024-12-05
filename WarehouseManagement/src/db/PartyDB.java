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
	AddressDBIF adb;
	
	public List<Party> findAll() throws SQLException {
		Connection con = dbc.getConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM Party");
		return buildObjects(rs);
	}

	public Party findByPhoneNo(String phoneNo) throws SQLException {
		Party party = null;
		
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("SELECT * FROM Party WHERE phoneNo = ?");
		p.setString(1, phoneNo);
		
		try(ResultSet rs = p.executeQuery()) {
			if(rs.next()) party = buildObject(rs);
		} catch (SQLException e) {
			party = null;
		}
		
		return party;
	}
	
	private Party buildObject(ResultSet rs) throws SQLException {
		adb = new AddressDB();
		
		String name = rs.getString("name");
		String phoneNo = rs.getString("phoneNo");
		Address address = adb.findByAddressId(rs.getInt("addressId"));
		
		return new Party(name, phoneNo, address);
	}
	
	private List<Party> buildObjects(ResultSet rs) throws SQLException {
		ArrayList<Party> list = new ArrayList();
		while(rs.next()) {
			list.add(buildObject(rs));
		}
		return list;
	}
	
}
