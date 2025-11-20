package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import java.util.ArrayList;

/*
    * Unit tests for tiered discounts in the DiscountService class.
    * Test Tiered Discounts
    * 1. Cart value 1500 = no discount
    * 2. Cart value 2000 = no discount
    * 3. Cart value 2001 = 15% discount
    * 4. Cart value 2500 = 15% discount
    * 5. Cart value 7000 = 15% discount
    * 6. Cart value 7001 = 20% discount
    * 7. Cart value 10000 = 20% discount
    * 8. Cart value 15000 = 20% discount
    * 9. Cart value 15001 = 25% discount
    * 10. Cart value 20000 = 25% discount
*/
public class TieredDiscountTest {
    private DiscountService discountService;
    private ArrayList<CartItem> cartItems;
    private Product monitor;
    private Product lollipop;
    private CartItem monitorItem;
    private CartItem lollipopItem;

    @BeforeEach
    public void setUp() {
        discountService = new DiscountService();
        monitor = new Product("Monitor", 500.0, 50);
        lollipop = new Product("Lollipop", 1.0, 200);
        cartItems = new ArrayList<>();
    }

    @Test // Cart Value 500.0 = no discount
    public void testTieredDiscount_CartValue500() {
        monitorItem = new CartItem(monitor, 1); // 500
        cartItems.add(monitorItem);

        assertEquals(500.0, discountService.applyDiscount(500.0, CustomerType.REGULAR, cartItems, null));
    }

    // Failed Test: Tiered discounts should start at 2000, not 1000
    // @Disabled("Failing test: Tiered discount starts discounts at 1000 rather than 2000.")
    @Test // Cart value 1500 = no discount
    public void testTieredDiscount_CartValue1500() {
        monitorItem = new CartItem(monitor, 3); // 1500
        cartItems.add(monitorItem);

        assertEquals(1500.0, discountService.applyDiscount(1500.0, CustomerType.REGULAR, cartItems, null));
    }

    // Failed Test: Tiered discounts should start at 2000, not 1000
    // @Disabled("Failing test: Tiered discount starts discounts at 1000 rather than 2000.")
    @Test // Cart value 2000 = no discount
    public void testTieredDiscount_CartValue2000() {
        monitorItem = new CartItem(monitor, 4); // 2000
        cartItems.add(monitorItem);

        assertEquals(2000.0, discountService.applyDiscount(2000.0, CustomerType.REGULAR, cartItems, null));
    }

    @Test // Cart value 2001 = 15% discount
    public void testTieredDiscount_CartValue2001() {
        monitorItem = new CartItem(monitor, 4); // 2000
        lollipopItem = new CartItem(lollipop, 1); // 1

        cartItems.add(monitorItem);
        cartItems.add(lollipopItem);

        assertEquals(1700.85, discountService.applyDiscount(2001.0, CustomerType.REGULAR, cartItems, null));
    }

    @Test // Cart value 2500 = 15% discount
    public void testTieredDiscount_CartValue2500() {
        monitorItem = new CartItem(monitor, 5); // 2500
        cartItems.add(monitorItem);

        assertEquals(2125.0, discountService.applyDiscount(2500.0, CustomerType.REGULAR, cartItems, null));
    }

    @Test // Cart value 7000 = 15% discount
    public void testTieredDiscount_CartValue7000() {
        monitorItem = new CartItem(monitor, 14); // 7000
        cartItems.add(monitorItem);

        assertEquals(5950.0, discountService.applyDiscount(7000.0, CustomerType.REGULAR, cartItems, null));
    }

    @Test // Cart value 7001 = 20% discount
    public void testTieredDiscount_CartValue7001() {
        monitorItem = new CartItem(monitor, 14); // 7000
        lollipopItem = new CartItem(lollipop, 1); // 1

        cartItems.add(monitorItem);
        cartItems.add(lollipopItem);

        assertEquals(5600.8, discountService.applyDiscount(7001.0, CustomerType.REGULAR, cartItems, null));
    }

    @Test // Cart value 10000 = 20% discount
    public void testTieredDiscount_CartValue10000() {
        monitorItem = new CartItem(monitor, 20); // 10000
        cartItems.add(monitorItem);

        assertEquals(8000.0, discountService.applyDiscount(10000.0, CustomerType.REGULAR, cartItems, null));
    }

    @Test // Cart value 15000 = 20% discount
    public void testTieredDiscount_CartValue15000() {
        monitorItem = new CartItem(monitor, 30); // 15000
        cartItems.add(monitorItem);

        assertEquals(12000.0, discountService.applyDiscount(15000.0, CustomerType.REGULAR, cartItems, null));
    }

    @Test // Cart value 15001 = 25% discount
    public void testTieredDiscount_CartValue15001() {
        monitorItem = new CartItem(monitor, 30); // 15000
        lollipopItem = new CartItem(lollipop, 1); // 1

        cartItems.add(monitorItem);
        cartItems.add(lollipopItem);

        assertEquals(11250.75, discountService.applyDiscount(15001.0, CustomerType.REGULAR, cartItems, null));
    }

    @Test // Cart value 20000 = 25% discount
    public void testTieredDiscount_CartValue20000() {
        monitorItem = new CartItem(monitor, 40); // 20000
        cartItems.add(monitorItem);
        
        assertEquals(15000.0, discountService.applyDiscount(20000.0, CustomerType.REGULAR, cartItems, null));
    }
    
}
