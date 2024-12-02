package model;

public class ItemLine {
	private int quantity;
	private Product product;
	
	public ItemLine(int quantity, Product product) {
		this.quantity = quantity;
		this.product = product;
	}
	
	public Product getProduct() {
		return product;
	}
	public int getQuantity() {
		return quantity;
	}
}
