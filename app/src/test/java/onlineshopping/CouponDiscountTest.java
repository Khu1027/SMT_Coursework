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
    private Product monitor;
    private CartItem monitorItem;

    @BeforeEach
    public void setUp() {
        discountService = new DiscountService();
        monitor = new Product("Monitor", 500.0, 10);
        monitorItem = new CartItem(monitor, 1);

        cartItems = new ArrayList<>();

        cartItems.add(monitorItem);
    }

    @Test
    public void testCouponDiscount_NoCoupon() {
        assertEquals(500.0, discountService.applyDiscount(500.0, CustomerType.REGULAR, cartItems, null));
    }

    @Test
    public void testCouponDiscount_EmptyCoupon() {
        assertEquals(500.0, discountService.applyDiscount(500.0, CustomerType.REGULAR, cartItems, ""));
    }

    @Test
    public void testCouponDiscount_InvalidCoupon() {
        assertEquals(500.0, discountService.applyDiscount(500.0, CustomerType.REGULAR, cartItems, "INVALID"));
    }

    // Failed Test: Invalid Coupons should raise an error
    // @Disabled("Failing test: DiscountService allows invalid coupon codes without exception.")
    @Test
    public void testCouponDiscount_InvalidError() {
        try {
            discountService.applyDiscount(500.0, CustomerType.REGULAR, cartItems, "INVALID");
            fail("Expected IllegalArgumentException for invalid coupon code");
        } catch (IllegalArgumentException e) {
            String expectedMessage = "Invalid coupon code";
            String actualMessage = e.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    // Failed Test: Invalid Coupons should raise an error
    // @Disabled("Failing test: DiscountService allows invalid coupon codes without exception.")
    @Test
    public void testCouponDiscount_lowercaseDISCOUNT10() {
        try {
            discountService.applyDiscount(500.0, CustomerType.REGULAR, cartItems, "discount10");
            fail("Expected IllegalArgumentException for invalid coupon code");
        } catch (IllegalArgumentException e) {
            String expectedMessage = "Invalid coupon code";
            String actualMessage = e.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }    
    }

    // Failed Test: Invalid Coupons should raise an error
    // @Disabled("Failing test: DiscountService allows invalid coupon codes without exception.")
    @Test
    public void testCouponDiscount_lowercaseSAVE50() {
        try {
            discountService.applyDiscount(500.0, CustomerType.REGULAR, cartItems, "save50");
            fail("Expected IllegalArgumentException for invalid coupon code");
        } catch (IllegalArgumentException e) {
            String expectedMessage = "Invalid coupon code";
            String actualMessage = e.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    public void testCouponDiscount_ValidDISCOUNT10() {
        assertEquals(450.0, discountService.applyDiscount(500.0, CustomerType.REGULAR, cartItems, "DISCOUNT10")); // 10% off
    }

    @Test
    public void testCouponDiscount_ValidSAVE50() {
        assertEquals(450.0, discountService.applyDiscount(500.0, CustomerType.REGULAR, cartItems, "SAVE50")); // $50 off
    }
    
}
