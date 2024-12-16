package model;

public class Address {
	private String streetName;
	private int houseNo;
	private String zip;
	private String city;
	private String country;
	
	public Address(String streetName, int houseNo, String zip, String city, String country) {
		this.streetName = streetName;
		this.houseNo = houseNo;
		this.zip = zip;
		this.city = city;
		this.country = country;
	}


	
	public String getStreetName() {
		return streetName;
	}
	public int getHouseNo() {
		return houseNo;
	}
	public String getZip() {
		return zip;
	}
	public String getCity() {
		return city;
	}
	public String getCountry() {
		return country;
	}
}
