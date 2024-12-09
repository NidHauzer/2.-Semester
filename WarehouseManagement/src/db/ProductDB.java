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
		List<Product> list = new ArrayList();
		
		Connection con = dbc.getConnection();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM Product");
		
		if(rs.next()) list = buildObjects(rs);
		else {
			throw new SQLException("No products were found.");
		}
		
		return list;
	}

	// Returns a specific product with the given barcode
	// Throws SQLException
	public Product findByBarcode(String barcode) throws SQLException {
		Product product = null;
		
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("SELECT * FROM Product "
				+ "WHERE barcode = ?");
		p.setString(1, barcode);
		
		try(ResultSet rs = p.executeQuery()) {
			if(rs.next()) product = buildObject(rs);
			else {
				throw new SQLException("No product was found.");
			}
		}
		
		return product;
	}
	
	public Product updateStock(Product product, int quantity) throws SQLException {
		Product newProduct = product;
		int newStock;
		Connection con = dbc.getConnection();
		PreparedStatement pGet = con.prepareStatement("SELECT * "
				+ "FROM Product "
				+ "WHERE barcode = ?");
		pGet.setString(1, product.getBarcode());
		try(ResultSet rs = pGet.executeQuery()){
			if(rs.next()) newStock = rs.getInt("quantityInStock") + quantity;
			else {
				throw new SQLException("No product was found.");
			}
		}
		PreparedStatement pUpdate = con.prepareStatement("UPDATE Product "
				+ "SET quantityInStock = ? "
				+ "WHERE barcode = ?");
		pUpdate.setInt(1, newStock);
		pUpdate.setString(2, product.getBarcode());
		int affectedRows = pUpdate.executeUpdate();
		if(affectedRows == 0) throw new SQLException("No update was made.");
		newProduct.setQuantityInStock(newStock);
		return newProduct;
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
		
		do {
			list.add(buildObject(rs));
		} while(rs.next());
		
		return list;
	}


	
}
