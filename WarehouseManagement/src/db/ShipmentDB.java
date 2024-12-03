package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import model.Party;
import model.Shipment;

public class ShipmentDB implements ShipmentDBIF {

	@Override
	public Shipment create(Shipment s) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Shipment s) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Shipment update(Shipment s) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shipment> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shipment findByShipmentNo(int shipmentNo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	// public Shipment(Party party, int shipmentNo, LocalDate date)
	public Shipment buildObject(ResultSet rs) {
		
		// Needs PartyDB to be implemented
		
		return null;
	}
	
	public List<Shipment> buildObjects(ResultSet rs) {
		return null;
	}

}
