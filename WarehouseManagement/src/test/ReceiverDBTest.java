package test;

import db.ReceiverDB;
import db.ReceiverDBIF;
import model.Receiver;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ReceiverDBTest {
	
	static ReceiverDBIF rdb;
	
	@BeforeAll
	public static void setUp() {
		rdb = new ReceiverDB();
	}

	@Test
	void findReceiverTest() throws SQLException {
		String name = "Roskilde Lager";
		Receiver r = rdb.findByPhoneNo("12345678");
		assertEquals(r.getName(), name);
	}
}
