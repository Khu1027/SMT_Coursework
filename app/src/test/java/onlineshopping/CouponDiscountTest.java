package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import java.util.ArrayList;

/*
    * Unit tests for coupon discounts in the DiscountService class.
    *
    * Test fixed amount coupons
    * 1. DISCOUNT10 coupon is correctly applied
    * 2. SAVE50 coupon is correctly applied
    * 3. No coupon applied
    * 5. invalid coupon code = error
*/
public class CouponDiscountTest {
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

    @Test
    public void testCouponDiscount_NoCoupon() {
        assertEquals(500.0, discountService.applyDiscount(500.0, customerType, cartItems, null));
    }

    @Test
    public void testCouponDiscount_EmptyCoupon() {
        assertEquals(500.0, discountService.applyDiscount(500.0, customerType, cartItems, ""));
    }

    @Test
    public void testCouponDiscount_InvalidCoupon() {
        assertEquals(500.0, discountService.applyDiscount(500.0, customerType, cartItems, "INVALID"));
    }

    // Failed Test: Invalid Coupons should raise an error
    @Disabled("Failing test: DiscountService allows invalid coupon codes without exception.")
    @Test
    public void testCouponDiscount_InvalidError() {
        try {
            discountService.applyDiscount(500.0, customerType, cartItems, "INVALID");
            fail("Expected IllegalArgumentException for invalid coupon code");
        } catch (IllegalArgumentException e) {
            String expectedMessage = "Invalid coupon code";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    // Failed Test:Coupons should be case-sensitive
    @Disabled("Failing test: DiscountService does not treat coupon codes as case-insensitive.")
    @Test
    public void testCouponDiscount_CaseSensitiveDiscount10() {
        try {
            discountService.applyDiscount(500.0, customerType, cartItems, "discount10");
            fail("Expected IllegalArgumentException for invalid coupon code");
        } catch (IllegalArgumentException e) {
            String expectedMessage = "Invalid coupon code";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }    
    }

    // Failed Test: Coupons should be case-sensitive
    @Disabled("Failing test: DiscountService does not treat coupon codes as case-insensitive.")
    @Test
    public void testCouponDiscount_CaseSensitiveSAVE50() {
        try {
            discountService.applyDiscount(500.0, customerType, cartItems, "save50");
            fail("Expected IllegalArgumentException for invalid coupon code");
        } catch (IllegalArgumentException e) {
            String expectedMessage = "Invalid coupon code";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    public void testCouponDiscount_ValidDISCOUNT10() {
        assertEquals(450.0, discountService.applyDiscount(500.0, customerType, cartItems, "DISCOUNT10")); // 10% off
    }

    @Test
    public void testCouponDiscount_ValidSAVE50() {
        assertEquals(450.0, discountService.applyDiscount(500.0, customerType, cartItems, "SAVE50")); // $50 off
    }
}
