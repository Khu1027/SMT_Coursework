package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

/*
 * Unit tests for the InventoryService class.
 */
public class InventoryServiceTest {
    private InventoryService inventoryService;
    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product("Laptop", 1000.0, 10);
        inventoryService = new InventoryService();
    }

    @Test
    public void testInventoryServiceUpdateStockValid() {
        CartItem cartItem = new CartItem(product, 3);
        inventoryService.updateStock(cartItem);
        assertEquals(7, product.getStock());
    }

    @Test
    public void testInventoryServiceUpdateStockExact() {
        CartItem cartItem = new CartItem(product, 10);
        inventoryService.updateStock(cartItem);
        assertEquals(0, product.getStock());
    }

    @Test
    public void testInventoryServiceProductStockSaves() {
        CartItem cartItem = new CartItem(product, 5);
        inventoryService.updateStock(cartItem);
        assertEquals(5, product.getStock());
        inventoryService.updateStock(new CartItem(product, 2));
        assertEquals(3, product.getStock());
    }

    @Test
    public void testInventoryServiceUpdateStockInvalid() {
        CartItem cartItem = new CartItem(product, 15);
        try {
            inventoryService.updateStock(cartItem);
            fail("Expected RuntimeException for insufficient stock");
        } catch (RuntimeException e) {
            String expectedMessage = "Failed to update stock for product: Laptop";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
    
}
