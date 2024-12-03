package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDB implements ProductDBIF {

	private DBConnection dbc = DBConnection.getInstance();
	
	// Returns a list of all products in the database
	// Throws SQLException
	public List<Product> findAll() throws SQLException {
		Connection con = dbc.getConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM Product");
		return buildObjects(rs);
	}

	// Returns a specific product with the given barcode
	// Throws SQLException
	public Product findByBarcode(String barcode) throws SQLException {
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("SELECT * FROM Product WHERE barcode = ?");
		p.setString(0, barcode);
		ResultSet rs = p.executeQuery();
		return buildObject(rs);
	}
	
	// Builds an object from ResultSet and returns it
	// Throws SQLException if there is something wrong with the connection
	private Product buildObject(ResultSet rs) throws SQLException {
		String barcode = rs.getString("barcode");
		int quantityInStock = rs.getInt("quantityInStock");
		int minStock = rs.getInt("minStock");
		String type = rs.getString("type");
		String colour = rs.getString("colour");
		int length = rs.getInt("length");
		int amount = rs.getInt("amount");
		
		return new Product(barcode, quantityInStock, minStock, type, colour, length, amount);
	}
	
	// Builds multiple objects and returns them
	private List<Product> buildObjects(ResultSet rs) throws SQLException {
		ArrayList<Product> list = new ArrayList();
		
		while(rs.next()) {
			list.add(buildObject(rs));
		}
		
		return list;
	}
	
}
