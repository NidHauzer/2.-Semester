package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Product;

class ProductTest {
	static Product p;

	@BeforeAll
	static void init() throws Exception {
		p = new Product("AAA11", 10, 1, "Clip in", "Blonde", 50, 100);
	}

	@Test
	void test1() {
		assertEquals(p.getQuantityInStock(), 10);
	}
	
	@Test
	void test2() {
		p.removeQuantityInStock(10);
		assertEquals(p.getQuantityInStock(), 0);
	}
	
	@Test
	void test3() {
		p.removeQuantityInStock(10);
		assertEquals(p.getQuantityInStock(), 0);
	}
	
	@Test
	void test4() {
		p.addQuantityInStock(10);
		assertEquals(p.getQuantityInStock(), 10);
	}

}
