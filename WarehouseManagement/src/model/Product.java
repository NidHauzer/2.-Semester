package model;

public class Product {
	private String barcode;
	private int quantityInStock;
	private int minStock;
	private String type;
	private String colour;
	private int length;
	private int amount;
	
	
	//Constructor
	public Product(String barcode, int quantityInStock, int minStock, String type, String colour, int length, int amount) {
		this.barcode = barcode;
		this.quantityInStock = quantityInStock;
		this.minStock = minStock;
		this.type = type;
		this.colour = colour;
		this.length = length;
		this.amount = amount;
	}
	
	
	//Getters
	public String getBarcode() {
		return barcode;
	}
	public int getQuantityInStock() {
		return quantityInStock;
	}
	public int getMinStock() {
		return minStock;
	}
	public String getType() {
		return type;
	}
	public String getColour() {
		return colour;
	}
	public int getLength() {
		return length;
	}
	public int getAmount() {
		return amount;
	}
	
	//Setters
	public void setMinStock(int m) {
		this.minStock = m;
	}
	
	public void setQuantityInStock(int q) {
		this.quantityInStock = q;
	}
}
