package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import java.util.ArrayList;

/*
    * Unit tests for time-limited discounts in the DiscountService class.
    * Time-limited Coupons
    * System is not in promotional period
    * - discount not applied
    * System is in promotional period
    * - discount applied (25% off)
*/
public class TimeLimitedDiscountTest {
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
        customerType = CustomerType.REGULAR;

        monitorItem = new CartItem(monitor, 1);
        cartItems.add(monitorItem);
    }
    
    @Test // No Promotion Period
    public void testTimeLimitedDiscount_NoPromotion() {
        assertEquals(500.0, discountService.applyDiscount(500.0, customerType, cartItems, null));
    }

    @Test // In Promotion Period
    public void testTimeLimitedDiscount_WithPromotion() {
        assertEquals(375.0, discountService.applyPromotionDiscount(500.0));
    }
}
