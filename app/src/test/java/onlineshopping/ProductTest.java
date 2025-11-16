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
        product = new Product("Phone", 500.0, 5);
    }

    @Test
    public void testProductGetName() {
        assertEquals("Phone", product.getName());
    }

    @Test
    public void testProductGetPrice() {
        assertEquals(500.0, product.getPrice());
    }

    @Test
    public void testProductGetStock() {
        assertEquals(5, product.getStock());
    }

    @Test
    public void testProductSetStock() {
        product.setStock(10);
        assertEquals(10, product.getStock());
    }

    @Test
    public void testProductReduceStock() {
        product.reduceStock(2);
        assertEquals(3, product.getStock());
    }

    @Test
    public void testProductReduceStockToZero() {
        product.reduceStock(5);
        assertEquals(0, product.getStock());
    }

    @Test
    public void testProductReduceStockException() {
        try {
            product.reduceStock(6);
        } catch (IllegalArgumentException e) {
            assertEquals("Not enough stock available", e.getMessage());
        }
    }

}
