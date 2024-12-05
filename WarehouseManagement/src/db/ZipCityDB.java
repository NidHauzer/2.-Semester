package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ZipCityDB implements ZipCityDBIF {

	DBConnection dbc = DBConnection.getInstance();
	
	@Override
	public String findCity(String zip) throws SQLException {
		String city = null;
		
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("SELECT * FROM ZipCity WHERE zip = ?");
		p.setString(1, zip);
		ResultSet rs = p.executeQuery();
		if(rs.next()) city = rs.getString("city");
		return city;
	}

}
