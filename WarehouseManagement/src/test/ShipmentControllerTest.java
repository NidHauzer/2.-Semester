package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.ShipmentController;
import exception.NotEnoughStockException;
import model.Shipment;

class ShipmentControllerTest {
	
	static ShipmentController sc;

	@BeforeAll
	public static void setUp() {
		sc = new ShipmentController();
	}
	
	@Test
	void createShipmentTest() throws SQLException {
		String phoneNo = "11223344";
		int employeeNo = 1;
		Shipment s = sc.createShipment(employeeNo, phoneNo, LocalDate.now());
		assertEquals(s.getShipmentNo(), 1);
	}
	
	@Test
	void findShipmentTest() throws SQLException {
		int expectedShipmentNo = 1;
		Shipment s = sc.findShipment(expectedShipmentNo);
		assertEquals(s.getShipmentNo(), expectedShipmentNo);
	}
	
	@Test
	void addItemLineTest() throws SQLException, IllegalArgumentException, NotEnoughStockException {
		String barcode = "AAA123";
		int quantity = 1;
		int shipmentNo = 1;
		boolean succeded = sc.addItemLine(barcode, quantity, shipmentNo);
		assertEquals(succeded, true);
	}
	
	

}
