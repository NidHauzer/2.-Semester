package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
	
public class Shipment {
    private Party party; 
    private int shipmentNo; 
    private LocalDate date; 
    private Employee employee;
    private List<ItemLine> items; 

    // Constructor
    public Shipment(Party party, int shipmentNo, LocalDate date, Employee employee) {
        this.party = party;
        this.shipmentNo = shipmentNo;
        this.date = date;
        this.employee = employee;
        this.items = new ArrayList<>(); 
    }

    // Getter for party
    public Party getParty() {
        return party;
    }

    // Getter for shipmentNo
    public int getShipmentNo() {
        return shipmentNo;
    }

    // Getter for date
    public LocalDate getDate() {
        return date;
    }

    // Getter for items
    public List<ItemLine> getItems() {
        return items;
    }
    
    public Employee getEmployee() {
    	return employee;
    }

    // Method - Adding Item
    public void addItem(ItemLine itemLine) {
        this.items.add(itemLine);
    }
}