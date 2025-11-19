package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

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
    public void testProduct_GetName() {
        assertEquals("Laptop", product.getName());
    }

    @Test
    public void testProduct_GetPrice() {
        assertEquals(1000.0, product.getPrice());
    }

    @Test
    public void testProduct_GetStock() {
        assertEquals(10, product.getStock());
    }

    @Test
    public void testProduct_SetStock() {
        product.setStock(20);
        assertEquals(20, product.getStock());
    }

    @Test
    public void testProduct_ReduceStock() {
        product.reduceStock(3);
        assertEquals(7, product.getStock());
    }

    @Test
    public void testProduct_ReduceStockToZero() {
        product.reduceStock(10);
        assertEquals(0, product.getStock());
    }

    @Test
    public void testProduct_ReduceStockException() {
        try {
            product.reduceStock(11);
        } catch (IllegalArgumentException e) {
            assertEquals("Not enough stock available", e.getMessage());
        }
    }

}
