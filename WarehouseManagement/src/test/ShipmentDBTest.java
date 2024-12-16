package test;

import db.EmployeeDB;
import db.ReceiverDB;
import db.ShipmentDB;
import db.ShipmentDBIF;
import model.Employee;
import model.Receiver;
import model.Shipment;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class ShipmentDBTest {
	
	static ShipmentDBIF sdb;

	@BeforeAll
	public static void setUp() {
		sdb = new ShipmentDB();
	}
	
 	@Test
 	@Order(1)
	void createShipmentTest() throws SQLException {
 		Receiver r = new ReceiverDB().findByPhoneNo("11223344");
 		Employee e = new EmployeeDB().findByEmployeeNo(1);
 		Shipment s = new Shipment(r, LocalDate.now(), e);
 		Shipment sReturn = sdb.create(s);
 		assertEquals(sReturn.getShipmentNo(), 1);
 	}
 	
 	@Test
 	@Order(2)
 	void findShipmentTest() throws SQLException {
 		Shipment sTest = sdb.findByShipmentNo(1);
 		assertEquals(sTest.getShipmentNo(), 1);
 	}

}
