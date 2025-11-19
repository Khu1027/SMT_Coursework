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
    private CustomerType customerType;
    private Product monitor;
    private CartItem monitorItem;

    private ShoppingCart shoppingCart;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        discountService = new DiscountService();
        monitor = new Product("Monitor", 500.0, 10);
        customerType = CustomerType.REGULAR;
        monitorItem = new CartItem(monitor, 1);

        customer = new Customer("Jane Doe", customerType);
        shoppingCart = new ShoppingCart(customer, discountService);
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
        double beforeDiscount = shoppingCart.calculateTotal();
        double finalPrice = shoppingCart.calculateFinalPrice();
        assertEquals(375.0, finalPrice); // 25% discount applied
        assertTrue(finalPrice < beforeDiscount); // Final price should be less than total price
    }
    
}
