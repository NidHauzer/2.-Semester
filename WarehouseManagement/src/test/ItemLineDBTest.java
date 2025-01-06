package test;

import model.ItemLine;
import model.Product;
import db.ItemLineDB;
import db.ItemLineDBIF;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ItemLineDBTest {
	
	static ItemLineDBIF idb;

	@BeforeAll
	static public void setUp() {
		idb = new ItemLineDB();
	}
	
	@Test
	void createItemLineTest() throws SQLException {
		Product p = new Product("AAA123", 100, 10,  "Tape Extension", "Light Beige Blonde Mix 16B/60B", 50, 50);
		ItemLine il = new ItemLine(1, p);
		ItemLine ilReturn = idb.create(il, 1);
		assertEquals(ilReturn.getProduct().getType(), il.getProduct().getType());
	}
	
	@Test
	void findItemLineTest() throws SQLException {
		Product p = new Product("AAA123", 100, 10, "Tape Extension", "Light Beige Blonde Mix 16B/60B", 50, 50);
		ItemLine il = idb.findById(2);
		assertEquals(il.getProduct().getType(), p.getType());
	}

}
