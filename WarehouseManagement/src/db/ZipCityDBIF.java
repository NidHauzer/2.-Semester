package db;

import java.sql.SQLException;

public interface ZipCityDBIF {
	public String findCity(String zip) throws SQLException;
}
