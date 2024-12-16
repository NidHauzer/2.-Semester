package test;

import db.ProductDB;
import db.ProductDBIF;
import model.Product;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ProductDBTest {
	
	static ProductDBIF pdb;

	@BeforeAll
	public static void setUp() {
		pdb = new ProductDB();
	}
	
	@Test
	void findProductTest() throws SQLException {
		String barcode = "AAA123";
		String type = "Tape Extension";
		Product p = pdb.findByBarcode(barcode);
		assertEquals(p.getType(), type);
	}

}
