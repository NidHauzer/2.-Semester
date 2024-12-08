package test;

import java.sql.SQLException;

import db.LocationDB;
import model.Location;

public class LocationDBTest {
	public static void main (String args[]) throws SQLException {
		LocationDB ldb = new LocationDB();
		
//		Location l = ldb.findByLocationCode("A1");
//		
//		System.out.println(l.getLocationCode() + " contains " + l.getProduct().getType() + " " + l.getProduct().getColour());
	
		for(Location l : ldb.findAll()) {
			System.out.println(l.getLocationCode());
		}
	}
}
