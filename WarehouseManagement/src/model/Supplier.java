package model;

public class Supplier extends Receiver {
	
	private String cvrNumber;

	public Supplier(String name, String phoneNo, Address address, String cvrNumber) {
		super(name, phoneNo, address);
		this.cvrNumber = cvrNumber;
	}
	
	public String getCvrNumber() {
		return cvrNumber;
	}

}
