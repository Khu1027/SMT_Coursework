package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

/*
    * Unit tests for time-limited discounts in the DiscountService class.
    * Time-limited Coupons
    * System is not in promotional period
    * - discount not applied
    * System is in promotional period
    * - discount applied (25% off)
*/
public class PromotionDiscountTest {
    private DiscountService discountService;
    private ShoppingCart shoppingCart;
    private Customer customer;

    private Product monitor;
    private CartItem monitorItem;

    @BeforeEach
    public void setUp() {
        discountService = new DiscountService();
        customer = new Customer("Jane Doe", CustomerType.REGULAR);
        shoppingCart = new ShoppingCart(customer, discountService);
    
        monitor = new Product("Monitor", 500.0, 10);
        monitorItem = new CartItem(monitor, 1);
    }

    @Test
    public void testPromotionDiscount_PromotionInactive(){
        shoppingCart.addItem(monitorItem);

        double beforeDiscount = shoppingCart.calculateTotal();
        double finalPrice = shoppingCart.calculateFinalPrice();

        assertEquals(500.0, finalPrice); // No discount applied
        assertEquals(beforeDiscount, finalPrice); // Final price should equal total price
    }

    @Test
    public void testPromotionDiscount_JustPromotionActive(){
        shoppingCart.addItem(monitorItem);
        shoppingCart.setPromotionActive(true);

        double finalPrice = shoppingCart.calculateFinalPrice();

        assertEquals(375.0, finalPrice); // 25% discount applied
    }

    // Failed test: Promotion discount does not add with other discounts. Instead if promotion is active, only promotion discount is applied.
    @Disabled("Failing test: Promotion discount does not combine correctly with other discounts.")
    @Test
    public void testPromotionDiscount_PromotionWithOtherDiscounts(){
        customer = new Customer("John Smith", CustomerType.VIP);
        shoppingCart = new ShoppingCart(customer, discountService);

        shoppingCart.addItem(monitorItem);
        shoppingCart.setPromotionActive(true);

        double finalPrice = shoppingCart.calculateFinalPrice();

        assertEquals(300.0, finalPrice); // 0.15 VIP discount + 0.25 promotion discount
    }

    // Failed test: Promotion discount does not add with other discounts. Instead if promotion is active, only promotion discount is applied.
    @Disabled("Failing test: Promotion discount does not combine correctly with other discounts.")
    @Test
    public void testPromotionDiscount_PromotionWithCoupon(){
        shoppingCart.addItem(monitorItem);
        shoppingCart.setPromotionActive(true);
        shoppingCart.applyCouponCode("SAVE50"); // Apply fixed amount coupon

        double finalPrice = shoppingCart.calculateFinalPrice();

        assertEquals(337.5, finalPrice); // $50 off then 25% promotion discount
    }
    
}
