package test;

import java.sql.SQLException;
import java.time.LocalDate;

import controller.ShipmentController;
import model.ItemLine;
import model.Shipment;

public class ShipmentControllerTest {
	public static void main(String args[]) throws SQLException {
		ShipmentController sc = new ShipmentController();
		
		Shipment s = null;
		
		s = sc.findShipment(1);
		
		System.out.println(s.getParty().getName());
		
//		System.out.println("Creating a shipment for the phone number '11223344' and employee number '1'.");
//		
//		try {
//			s = sc.createShipment(1, "11223344", LocalDate.now());
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("Something went wrong");
//		}
//		
//		System.out.println("Shipment succesfully created with shipment number " + s.getShipmentNo());
//		
//		System.out.println("Adding product with barcode 'AAA123' and a quantity of 10 to the shipment");
//	
//		ItemLine i = null;
//		
//		try {
//			i = sc.addItemLine("AAA123", 10, s.getShipmentNo());
//			System.out.println("Succesfully added " + i.getQuantity() + " of " + i.getProduct().getType() + " " + i.getProduct().getColour());
//		} catch(SQLException e) {
//			e.printStackTrace();
//			System.out.println("Something went wrong.");
//		}
	}
}