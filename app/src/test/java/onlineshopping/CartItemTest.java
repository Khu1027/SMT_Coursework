package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

/*
    * Unit tests for the CartItem class.
    * Test price and quantity of cart items
 */
public class CartItemTest {
    private Product product;
    private CartItem cartItem;

    @BeforeEach
    public void setUp() {
        product = new Product("Laptop", 1000.0, 10);
        cartItem = new CartItem(product, 2);
    }

    @Test
    public void testCartItem_GetProduct() {
        assertEquals(product, cartItem.getProduct());
    }

    @Test
    public void testCartItem_GetQuantity() {
        assertEquals(2, cartItem.getQuantity());
    }

    @Test
    public void testCartItem_GetTotalPrice() {
        assertEquals(2000.0, cartItem.getTotalPrice());
    }

    @Test
    public void testCartItem_OneItem() {
        CartItem singleItem = new CartItem(product, 1);

        assertEquals(1, singleItem.getQuantity());
        assertEquals(1000.0, singleItem.getTotalPrice());
    }

    // This test fails because CartItem does not throw an exception for zero quantity.
    // @Disabled("Failing test: CartItem allows zero quantity without exception.")
    @Test
    public void testCartItem_NoItemException() {
        CartItem noItem = new CartItem(product, 0);
        try {
            noItem.getTotalPrice();
            fail("Expected Exception for zero quantity");
        } catch (IllegalArgumentException e) {
            String expectedMessage = "Quantity must be at least 1";
            String actualMessage = e.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}
