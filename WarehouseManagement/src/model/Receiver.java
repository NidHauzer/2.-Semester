package model;

public class Receiver {
	private String name;
	private String phoneNo;
	private Address address;
	
	public Receiver(String name, String phoneNo, Address address) {
		this.name = name;
		this.phoneNo = phoneNo;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public Address getAddress() {
		return address;
	}
}
