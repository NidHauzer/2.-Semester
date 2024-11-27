package test;
import model.Product;

public class ProductStockTest {
	public static void main(String[] args) {
		Product p = new Product("AAA11", 10, 1, "Clip in", "Blonde", 50, 100);
		
		System.out.println(p.getQuantityInStock());
		
		p.removeQuantityInStock(5);
		
		System.out.println(p.getQuantityInStock());
		
		p.removeQuantityInStock(10);
		
		System.out.println(p.getQuantityInStock());
		
		p.addQuantityInStock(10);
		
		System.out.println(p.getQuantityInStock());
	}
}
