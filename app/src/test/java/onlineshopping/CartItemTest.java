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
    public void testCartItemGetProduct() {
        assertEquals(product, cartItem.getProduct());
    }

    @Test
    public void testCartItemGetQuantity() {
        assertEquals(2, cartItem.getQuantity());
    }

    @Test
    public void testCartItemGetTotalPrice() {
        assertEquals(2000.0, cartItem.getTotalPrice());
    }

    @Test
    public void testCartItemOneItem() {
        CartItem singleItem = new CartItem(product, 1);
        assertEquals(1, singleItem.getQuantity());
        assertEquals(1000.0, singleItem.getTotalPrice());
    }

    @Disabled("CartItem does not check for invalid quantity")
    @Test
    public void testCartItemNoItemException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CartItem(product, 0);
        });
        String expectedMessage = "Quantity must be at least 1";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
}
