package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import java.util.ArrayList;

/*
    * Unit tests for customer-type discounts in the DiscountService class.
    *
    * Test Customer-type Discounts
    * 1. REGULAR customer = no discount
    * 2. PREMIUM customer = 10% discount
    * 3. VIP customer = 15% discount
*/
public class CustomerTypeDiscountTest {
    private DiscountService discountService;
    private ArrayList<CartItem> cartItems;
    private Product monitor;
    private CartItem monitorItem;

    @BeforeEach
    public void setUp() {
        discountService = new DiscountService();
        monitor = new Product("Monitor", 500.0, 10);
        cartItems = new ArrayList<>();

        monitorItem = new CartItem(monitor, 1);
        cartItems.add(monitorItem);
    }

    @Test
    public void testCustomerTypeDiscount_Regular() {
        assertEquals(500.0, discountService.applyDiscount(500.0, CustomerType.REGULAR, cartItems, null));
    }

    // Failed Test: Premium customers receive 20% discount instead of 10%
    // @Disabled("Failing test: Premium customers receive 20% discount instead of 10%.")
    @Test // Premium customer = 10% discount
    public void testCustomerTypeDiscount_Premium() {
        assertEquals(450.0, discountService.applyDiscount(500.0, CustomerType.PREMIUM, cartItems, null));
    }

    @Test // VIP customer = 15% discount
    public void testCustomerTypeDiscount_VIP() {
        assertEquals(425.0, discountService.applyDiscount(500.0, CustomerType.VIP, cartItems, null));
    }
    
}
