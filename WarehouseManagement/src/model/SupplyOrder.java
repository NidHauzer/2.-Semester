package model;

import java.time.LocalDate;
import java.util.List;

public class SupplyOrder {
	private int orderNo;
	private LocalDate date;
	private Supplier supplier;
	private List<ItemLine> items;
	
	public SupplyOrder(int orderNo, LocalDate date, Supplier supplier) {
		this.orderNo = orderNo;
		this.date = date;
		this.supplier = supplier;
	}
	
	public int getOrderNo() {
		return orderNo;
	}
	public LocalDate getDate() {
		return date;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public List<ItemLine> getItems(){
		return items;
	}
	
	public void addItem(ItemLine i) {
		items.add(i);
	}
}	

