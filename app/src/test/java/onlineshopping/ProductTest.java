package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

/*
 * Unit tests for the Product class.
 */
public class ProductTest {
    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product("Laptop", 1000.0, 10);
    }

    @Test
    public void testProductGetName() {
        assertEquals("Laptop", product.getName());
    }

    @Test
    public void testProductGetPrice() {
        assertEquals(1000.0, product.getPrice());
    }

    @Test
    public void testProductGetStock() {
        assertEquals(10, product.getStock());
    }

    @Test
    public void testProductSetStock() {
        product.setStock(20);
        assertEquals(20, product.getStock());
    }

    @Test
    public void testProductReduceStock() {
        product.reduceStock(3);
        assertEquals(7, product.getStock());
    }

    @Test
    public void testProductReduceStockToZero() {
        product.reduceStock(10);
        assertEquals(0, product.getStock());
    }

    @Test
    public void testProductReduceStockException() {
        try {
            product.reduceStock(11);
        } catch (IllegalArgumentException e) {
            assertEquals("Not enough stock available", e.getMessage());
        }
    }

}
