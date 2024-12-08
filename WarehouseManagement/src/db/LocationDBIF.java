package db;

import java.sql.SQLException;
import java.util.List;

import model.Location;

public interface LocationDBIF {
	public List<Location> findAll() throws SQLException;
	public Location findByLocationCode(String locationCode) throws SQLException;
}
