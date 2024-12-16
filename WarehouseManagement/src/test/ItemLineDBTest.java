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
		Product p = new Product("AAA123", 100, 10, "Light Beige Blonde Mix 16B/60B", "Tape Extension", 50, 50);
		ItemLine il = new ItemLine(1, p);
		ItemLine ilReturn = idb.create(il, 1);
		assertEquals(ilReturn.getQuantity(), il.getQuantity());
	}

}
