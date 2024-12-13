package controller;

import java.sql.SQLException;

import db.ItemLineDB;
import db.ProductDB;
import exception.NotEnoughStockException;
import model.ItemLine;
import model.Product;

public class ProductController {
	
	Product p;
	ProductDB pdb;
	
	public Product findProductByBarcode(String barcode) throws SQLException {
		pdb = new ProductDB();
		this.p = pdb.findByBarcode(barcode);
		return p;
	}
	
	public Product getProduct() {
		return this.p;	
	}
	
	public ItemLine createItemLine(Product p, int quantity) {
		return new ItemLine(quantity, p);
	}
	
	public Product updateStock(Product p, int quantity) throws SQLException, NotEnoughStockException {
		pdb = new ProductDB();
		return pdb.updateStock(p, quantity);
	}
}
