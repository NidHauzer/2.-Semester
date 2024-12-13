package test;

import java.sql.SQLException;

import db.ProductDB;
import exception.NotEnoughStockException;
import model.Product;

public class ProductDBTest {
	public static void main(String args[]) throws SQLException, NotEnoughStockException {
		ProductDB pdb = new ProductDB();
		
		Product p = pdb.findByBarcode("AAA123");
		
		System.out.println(p.getType() + " " + p.getColour() + p.getLength() + "cm");
	
		for(Product product : pdb.findAll()) {
			System.out.println(product.getColour());
		}
		
		Product pUpdate = pdb.updateStock(p, -100);
		System.out.println(pUpdate.getType() + " " + pUpdate.getQuantityInStock());
	}
}
