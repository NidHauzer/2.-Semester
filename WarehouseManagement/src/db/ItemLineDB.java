package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ItemLine;
import model.Product;

public class ItemLineDB implements ItemLineDBIF {

	DBConnection dbc = DBConnection.getInstance();
	
	public ItemLine create(ItemLine itemLine, int shipmentNo) throws SQLException {
		String barcode = itemLine.getProduct().getBarcode();
		int quantity = itemLine.getQuantity();
		
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("INSERT INTO ItemLine VALUES ("
				+ "?, " //quantity
				+ "?, " //shipmentNo
				+ "?)"); //barcode
		p.setInt(1, quantity);
		p.setInt(2, shipmentNo);
		p.setString(3, barcode);
		int affectedRows = p.executeUpdate();
		
		if(affectedRows == 0) throw new SQLException("No ItemLine was created.");
		return itemLine;
	}

	public List<ItemLine> findByShipmentNo(int shipmentNo) throws SQLException {
		List<ItemLine> list = new ArrayList();
		
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("SELECT * FROM ItemLine "
				+ "JOIN Product ON ItemLine.barcode = Product.barcode "
				+ "WHERE shipmentNo = ?");
		p.setInt(1, shipmentNo);
		try(ResultSet rs = p.executeQuery()){
			if(rs.next()) list = buildObjects(rs);
			else {
				throw new SQLException("No ItemLines were found.");
			}
		}
		
		return list;
	}

	public ItemLine findById(int id) throws SQLException {
		ItemLine itemLine = null;
		
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("SELECT * FROM ItemLine "
				+ "JOIN Product ON ItemLine.barcode = Product.barcode "
				+ "WHERE id = ?");
		p.setInt(1, id);
		try(ResultSet rs = p.executeQuery()){
			if(rs.next()) itemLine = buildObject(rs);
			else {
				throw new SQLException("No ItemLine was found.");
			}
		}
		
		return itemLine;
	}

	public List<ItemLine> findAll() throws SQLException {
		List<ItemLine> list = new ArrayList();
		
		Connection con = dbc.getConnection();
		PreparedStatement p = con.prepareStatement("SELECT * FROM ItemLine "
				+ "JOIN Product ON ItemLine.barcode = Product.Barcode");
		try(ResultSet rs = p.executeQuery()){
			if(rs.next()) list = buildObjects(rs);
			else {
				throw new SQLException("No ItemLines were found.");
			}
		}
		
		return list;
	}

	private ItemLine buildObject(ResultSet rs) throws SQLException {
		String barcode = rs.getString("barcode");
		int quantityInStock = rs.getInt("quantityInStock");
		int minStock = rs.getInt("minStock");
		String colour = rs.getString("colour");
		String type = rs.getString("type");
		int length = rs.getInt("length");
		int amount = rs.getInt("amount");
		
		Product product = new Product(barcode, quantityInStock, minStock, type, colour, length, amount);
		
		return new ItemLine(rs.getInt("quantity"), product);
	}
	
	private List<ItemLine> buildObjects(ResultSet rs) throws SQLException {
		List<ItemLine> list = new ArrayList();
		
		do {
			list.add(buildObject(rs));
		} while (rs.next());
		
		return list;
	}

}
