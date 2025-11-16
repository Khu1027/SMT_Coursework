package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Get total price of each CartItem (product price * quantity)
 * Every item in cart must be greater or equal to 1.
 * If quantity is less than 1, throw IllegalArgumentException
 */
public class CartItemTest {

    @Test
    public void testGetProduct() {
        Product product = new Product("Laptop", 1000.0, 10);
        CartItem cartItem = new CartItem(product, 2);
        
        assertEquals(product, cartItem.getProduct());
    }

    @Test
    public void testGetQuantity() {
        Product product = new Product("Laptop", 1000.0, 10);
        CartItem cartItem = new CartItem(product, 2);
        
        assertEquals(2, cartItem.getQuantity());
    }
    
}
