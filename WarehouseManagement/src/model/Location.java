package model;

public class Location {
	private String locationCode;
	private Product product;
	
	public Location(String locationCode) {
		this.locationCode = locationCode;
		product = null;
	}
	
	public Location(String locationCode, Product product) {
		this.locationCode = locationCode;
		this.product = product;
	}
	
	public String getLocationCode() {
		return locationCode;
	}
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product p) {
		this.product = p;
	}
}
