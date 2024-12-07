package db;

import java.sql.SQLException;
import java.util.List;

import model.Shipment;

public interface ShipmentDBIF {
	public Shipment create(Shipment s) throws SQLException;
	public List<Shipment> findAll() throws SQLException;
	public Shipment findByShipmentNo(int shipmentNo) throws SQLException;
}
 