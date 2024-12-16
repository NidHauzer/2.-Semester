package test;

import java.sql.SQLException;
import java.time.LocalDate;

import db.ShipmentDB;
import db.ReceiverDB;
import model.*;

public class ShipmentDBTest {
	public static void main(String args[]) throws SQLException {
		
		
		ShipmentDB sdb = new ShipmentDB();
		ReceiverDB pdb = new ReceiverDB();
		
		
		Shipment s = sdb.findByShipmentNo(10);
		
		System.out.println(s.getEmployee().getName());
		System.out.println(s.getParty().getName());
		
		Employee employee = new Employee("Thea", 1);
		
		Receiver receiver = pdb.findByPhoneNo("26191604");
		
		Shipment shipment = new Shipment(receiver, LocalDate.now(), employee);
		
//		if(sdb.create(shipment)) System.out.println("Success!");
//		else {
//			System.out.println("Fail");
//		}
		
		System.out.println("Created shipment with shipment number " + sdb.create(shipment).getShipmentNo());
	
		for(Shipment i : sdb.findAll()) {
			System.out.println(i.getShipmentNo());
		}
	}
}
