package db;

import java.sql.SQLException;
import java.util.List;

import model.Party;

public interface PartyDBIF {
	public List<Party> findAll() throws SQLException;
	public Party findByPhoneNo(String phoneNo) throws SQLException;
}
