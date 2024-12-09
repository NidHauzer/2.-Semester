package controller;

import java.sql.SQLException;

import db.PartyDB;
import model.Party;

public class PartyController {
	PartyDB pdb;
	
	public Party findPartyByPhoneNo(String phoneNo) throws SQLException {
		pdb = new PartyDB();
		return pdb.findByPhoneNo(phoneNo);
	}
}
