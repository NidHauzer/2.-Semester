package controller;

import java.sql.SQLException;
import java.time.LocalDate;

import db.ItemLineDB;
import db.ShipmentDB;
import model.ItemLine;
import model.Shipment;

public class ShipmentController {
	ProductController productC;
	EmployeeController employeeC;
	PartyController partyC;
	
	ShipmentDB sdb;
	
	Shipment shipment;
	
	public Shipment createShipment(int employeeNo, String phoneNo, LocalDate date) throws SQLException {
		employeeC = new EmployeeController();
		partyC = new PartyController();
		sdb = new ShipmentDB();
		
		Shipment s = new Shipment(
				partyC.findPartyByPhoneNo(phoneNo),
				date,
				employeeC.findEmployeeByEmployeeNo(employeeNo)
		);
		
		return sdb.create(s);
	}
	
	public Shipment getShipment() {
		return this.shipment;
	}
	
	public Shipment findShipment(int shipmentNo) throws SQLException {
		sdb = new ShipmentDB();
		shipment = sdb.findByShipmentNo(shipmentNo);
		return shipment;
	}
	
	public ItemLine addItemLine(String barcode, int quantity, int shipmentNo) throws SQLException {
		sdb = new ShipmentDB();
		productC = new ProductController();
		ItemLineDB idb = new ItemLineDB();
		
		productC.updateStock(productC.findProductByBarcode(barcode), -(quantity));
		
		ItemLine il = productC.createItemLine(productC.findProductByBarcode(barcode), quantity);
	
		return idb.create(il, shipmentNo);
	}
	
}
