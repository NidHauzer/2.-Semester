package test;

import java.sql.SQLException;

import db.ItemLineDB;
import model.ItemLine;

public class ItemLineDBTest {
	public static void main(String args[]) throws SQLException {
		ItemLineDB idb = new ItemLineDB();
		
		ItemLine il = idb.findById(2);
		
		idb.create(il, 2);
		
		for(ItemLine i : idb.findAll()) {
			System.out.println(i.getProduct().getType() + " " + i.getProduct().getColour() + " x " + i.getQuantity());
		}
		
		for(ItemLine i : idb.findByShipmentNo(2)) {
			System.out.println(i.getProduct().getType() + " " + i.getProduct().getColour() + " x " + i.getQuantity());
		}
		
		System.out.println(idb.findById(2).getProduct().getType());
		
		
	}
}
