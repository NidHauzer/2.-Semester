package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Address;
import model.Location;
import model.Product;
import model.Warehouse;

public class LocationDB implements LocationDBIF {
	
	DBConnection dbc = DBConnection.getInstance();

	@Override
	public List<Location> findAll() throws SQLException {
		List<Location> list = new ArrayList();
		
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("SELECT * "
				+ "FROM Location "
				+ "JOIN Product ON Location.barcode = Product.barcode");
		try(ResultSet rs = p.executeQuery()){
			if(rs.next()) list = buildObjects(rs);
			else {
				throw new SQLException("No locations were found.");
			}
		}
		return list;
	}

	@Override
	public Location findByLocationCode(String locationCode) throws SQLException {
		Location location = null;
		
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("SELECT * "
				+ "FROM Location "
				+ "JOIN Product ON Location.barcode = Product.barcode "
				+ "WHERE locationCode = ?");
		p.setString(1, locationCode);
		try(ResultSet rs = p.executeQuery()){
			if(rs.next()) location = buildObject(rs);
			else {
				throw new SQLException("No location found.");
			}
		} 
		
		return location;
	}

	private Location buildObject(ResultSet rs) throws SQLException {
		String barcode = rs.getString("barcode");
		int quantityInStock = rs.getInt("quantityInStock");
		int minStock = rs.getInt("minStock");
		String colour = rs.getString("colour");
		String type = rs.getString("type");
		int length = rs.getInt("length");
		int amount = rs.getInt("amount");
		
		Product product = new Product(barcode, quantityInStock, minStock, colour, type, length, amount);
	
		String locationCode = rs.getString("locationCode");
		
		return new Location(locationCode, product);
	}
	
	private List<Location> buildObjects(ResultSet rs) throws SQLException{
		List<Location> list = new ArrayList();
		
		do {
			list.add(buildObject(rs));
		} while (rs.next());
		
		return list;
	}
}
