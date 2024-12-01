package model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
	private String id;
	private Address address;
	private List<Location> locations;
	
	public Warehouse(String id, Address address) {
		this.id = id;
		this.address = address;
		this.locations = new ArrayList();
	}
	
	public String getId() {
		return id;
	}
	public Address getAddress() {
		return address;
	}
	public List<Location> getLocations(){
		return locations;
	}
	
	public void addLocation(Location l) {
		locations.add(l);
	}
}
