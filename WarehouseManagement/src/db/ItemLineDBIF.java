package db;

import java.sql.SQLException;
import java.util.List;

import model.ItemLine;

public interface ItemLineDBIF {
	public ItemLine create(ItemLine itemLine, int shipmentNo) throws SQLException;
	public List<ItemLine> findByShipmentNo(int shipmentNo) throws SQLException;
	public ItemLine findById(int id) throws SQLException;
	public List<ItemLine> findAll() throws SQLException;
}
