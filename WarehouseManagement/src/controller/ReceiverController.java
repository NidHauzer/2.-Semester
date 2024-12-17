package controller;

import java.sql.SQLException;

import db.ReceiverDB;
import model.Receiver;

public class ReceiverController {
	ReceiverDB pdb;
	
	public Receiver findReceiverByPhoneNo(String phoneNo) throws SQLException {
		pdb = new ReceiverDB();
		return pdb.findByPhoneNo(phoneNo);
	}
}
