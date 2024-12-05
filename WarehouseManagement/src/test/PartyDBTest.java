package test;
import db.*;
import model.Party;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PartyDBTest {
	
	static PartyDB pdb;
	
	@BeforeAll
	static void setUp() throws Exception {
		pdb = new PartyDB();
	}

	@Test
	void findPartyTest() throws SQLException {
		Party p = pdb.findByPhoneNo("26191604");
		assertEquals(p.getName(), "Jonas Vittrup Biegel");
		assertEquals(p.getAddress().getCountry(), "Denmark");
		assertEquals(p.getAddress().getCity(), "Sæby");
	}

}
