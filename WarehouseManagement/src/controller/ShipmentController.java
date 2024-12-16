package controller;

import java.sql.SQLException;
import java.time.LocalDate;

import db.ItemLineDB;
import db.ShipmentDB;
import exception.NotEnoughStockException;
import model.ItemLine;
import model.Shipment;

public class ShipmentController {
	ProductController productC;
	EmployeeController employeeC;
	ReceiverController partyC;
	
	ShipmentDB sdb;
	
	Shipment shipment;
	
	public Shipment createShipment(int employeeNo, String phoneNo, LocalDate date) throws SQLException {
		employeeC = new EmployeeController();
		partyC = new ReceiverController();
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
	
	public boolean addItemLine(String barcode, int quantity, int shipmentNo) throws SQLException, IllegalArgumentException, NotEnoughStockException {
		sdb = new ShipmentDB();
		productC = new ProductController();
		ItemLineDB idb = new ItemLineDB();
		ItemLine il = null;
		
		productC.updateStock(productC.findProductByBarcode(barcode), -(quantity));

		il = productC.createItemLine(productC.findProductByBarcode(barcode), quantity);

		idb.create(il, shipmentNo);
		
		return true;
	}
	
}
