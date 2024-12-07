package test;

import java.sql.SQLException;

import db.ProductDB;
import model.Product;

public class ProductDBTest {
	public static void main(String args[]) throws SQLException {
		ProductDB pdb = new ProductDB();
		
		Product p = pdb.findByBarcode("AAA123");
		
		System.out.println(p.getType() + " " + p.getColour() + p.getLength() + "cm");
	
		for(Product product : pdb.findAll()) {
			System.out.println(product.getColour());
		}
	}
}
