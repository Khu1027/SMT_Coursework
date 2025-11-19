package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import static org.mockito.Mockito.*;

/*
 * Unit tests for ShoppingCart class.
 */
public class ShoppingCartTest {
    private DiscountService discountService;
    private ShoppingCart shoppingCart;
    private Customer customer;

    private Product monitor;
    private CartItem monitorItem;

    private Product mouse;
    private CartItem mouseItem;

    @BeforeEach
    public void setUp() {
        discountService = mock(DiscountService.class);
        customer = new Customer("Jane Doe", CustomerType.REGULAR);
        shoppingCart = new ShoppingCart(customer, discountService);

        monitor = new Product("Monitor", 500.0, 10);
        monitorItem = new CartItem(monitor, 1);

        mouse = new Product("Mouse", 50.0, 20);
        mouseItem = new CartItem(mouse, 2);
    }

    @Test
    public void testShoppingCart_AddItem() {
        shoppingCart.addItem(monitorItem);
        
        assertEquals(1, shoppingCart.getItems().size());
        assertEquals(monitorItem, shoppingCart.getItems().get(0));
    }

    @Test
    public void testShoppingCart_RemoveItem() {
        shoppingCart.addItem(monitorItem);
        shoppingCart.removeItem(monitorItem);

        assertEquals(0, shoppingCart.getItems().size());
    }

    @Test
    public void testShoppingCart_CalculateTotal() {
        shoppingCart.addItem(monitorItem);
        shoppingCart.addItem(mouseItem);

        assertEquals(600.0, shoppingCart.calculateTotal());
    }

    @Test // Test coupon code logic
    public void testShoppingCart_ApplyCouponCode() {
        shoppingCart.addItem(monitorItem);
        shoppingCart.applyCouponCode("DISCOUNT10");

        when(discountService.applyDiscount(eq(500.0), eq(CustomerType.REGULAR), anyList(), eq("DISCOUNT10")))
            .thenReturn(450.0);

        assertEquals(450.0, shoppingCart.calculateFinalPrice());
        verify(discountService, times(1))
            .applyDiscount(eq(500.0), eq(CustomerType.REGULAR), anyList(), eq("DISCOUNT10"));
    }
}
