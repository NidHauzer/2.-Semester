package db;
import java.sql.SQLException;
import java.util.List;

import model.Product;

public interface ProductDBIF {
	public List<Product> findAll() throws SQLException;
	public Product findByBarcode(String barcode) throws SQLException;
}
