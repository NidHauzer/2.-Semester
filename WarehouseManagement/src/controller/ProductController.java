package controller;

import java.sql.SQLException;

import db.ItemLineDB;
import db.ProductDB;
import model.ItemLine;
import model.Product;

public class ProductController {
	
	ProductDB pdb;
	
	public Product findProductByBarcode(String barcode) throws SQLException {
		pdb = new ProductDB();
		return pdb.findByBarcode(barcode);
	}
	
	public ItemLine createItemLine(Product p, int quantity) {
		return new ItemLine(quantity, p);
	}
	
	public Product updateStock(Product p, int quantity) throws SQLException {
		pdb = new ProductDB();
		return pdb.updateStock(p, quantity);
	}
}
