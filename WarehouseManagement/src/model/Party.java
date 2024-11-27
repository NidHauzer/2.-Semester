package model;

public class Party {
	private String name;
	private String phoneNo;
	private Address address;
	
	public Party(String name, String phoneNo, Address address) {
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
