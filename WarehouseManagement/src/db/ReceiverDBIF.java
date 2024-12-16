package db;

import java.sql.SQLException;
import java.util.List;

import model.Receiver;

public interface ReceiverDBIF {
	public List<Receiver> findAll() throws SQLException;
	public Receiver findByPhoneNo(String phoneNo) throws SQLException;
}
