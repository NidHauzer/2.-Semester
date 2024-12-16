package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
	
public class Shipment {
    private Receiver receiver; 
    private int shipmentNo; 
    private LocalDate date; 
    private Employee employee;
    private List<ItemLine> items; 

    // Constructor with and without shipmentNo
    // With shipmentNo is used for returning the object from the database
    // Without shipmentNo is used for making a database object from a Shipment object
    public Shipment(Receiver receiver, LocalDate date, Employee employee) {
    	this.receiver = receiver;
    	this.date = date;
    	this.employee = employee;
    	this.items = new ArrayList<>();
    }
    
    public Shipment(Receiver receiver, LocalDate date, Employee employee, int shipmentNo) {
        this.receiver = receiver;
        this.shipmentNo = shipmentNo;
        this.date = date;
        this.employee = employee;
        this.items = new ArrayList<>(); 
    }

    // Getter for party
    public Receiver getReceiver() {
        return receiver;
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
    
    // Setter for shipmentNo
    public void setShipmentNo(int shipmentNo) {
    	this.shipmentNo = shipmentNo;
    }

    // Method - Adding Item
    public void addItem(ItemLine itemLine) {
        this.items.add(itemLine);
    }
}