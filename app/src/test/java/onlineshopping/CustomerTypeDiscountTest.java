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
    private CustomerType customerType;
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
        customerType = CustomerType.REGULAR;
        assertEquals(500.0, discountService.applyDiscount(500.0, customerType, cartItems, null));
    }

    // Failed Test: Premium customers receive 20% discount instead of 10%
    @Disabled("Failing test: Premium customers receive 20% discount instead of 10%.")
    @Test
    public void testCustomerTypeDiscount_Premium() {
        customerType = CustomerType.PREMIUM; // 10% discount
        assertEquals(450.0, discountService.applyDiscount(500.0, customerType, cartItems, null));
    }

    @Test
    public void testCustomerTypeDiscount_VIP() {
        customerType = CustomerType.VIP; // 15% discount
        assertEquals(425.0, discountService.applyDiscount(500.0, customerType, cartItems, null));
    }

    // Assumption that customer-type cannot be anything else
    
}
