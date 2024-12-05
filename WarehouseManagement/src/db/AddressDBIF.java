package db;

import java.sql.SQLException;
import java.util.List;

import model.Address;

public interface AddressDBIF {
	public List<Address> findAll() throws SQLException;
	public Address findByAddressId(int addressId) throws SQLException;
}
