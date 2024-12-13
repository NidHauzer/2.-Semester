package test;
import java.sql.SQLException;

import db.*;
import model.*;

public class PartyDBTest2 {

	public static void main(String args[]) throws SQLException {
//		ZipCityDB zdb = new ZipCityDB();
//		System.out.println(zdb.findCity("9300"));
//		
//		AddressDB adb = new AddressDB();
//		System.out.println(adb.findByAddressId(1).getStreetName());
		
		String phoneNo = "11223344";
		
		PartyDB pdb = new PartyDB();		
		System.out.println(pdb.findByPhoneNo(phoneNo).getName());
		System.out.println(pdb.findByPhoneNo(phoneNo).getAddress().getCity());
		System.out.println(pdb.findByPhoneNo(phoneNo).getAddress().getCountry());
		
	}
}
