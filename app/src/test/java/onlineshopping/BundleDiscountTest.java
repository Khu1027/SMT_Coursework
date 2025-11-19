package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import java.util.ArrayList;

/*
    * Unit tests for bundle discounts in the DiscountService class.
    *
    * Test bundle discount
    * 1. one mouse-laptop pair
    * 2. two mouse-laptop pairs
    * 3. one laptop, two mice
    * 4. two laptops, one mouse
*/
public class BundleDiscountTest {
    private DiscountService discountService;
    private ArrayList<CartItem> cartItems;
    private CustomerType customerType;
    private Product laptop;
    private Product mouse;
    private CartItem laptopItem;
    private CartItem mouseItem;

    @BeforeEach
    public void setUp() {
        discountService = new DiscountService();
        laptop = new Product("Laptop", 500.0, 10);
        mouse = new Product("Mouse", 50.0, 50);
        cartItems = new ArrayList<>();
        customerType = CustomerType.REGULAR;
    }

    // ===== All Failed Tests Below: 10% Bundle discount is applied to the laptop instead of the mouse =====


    // @Disabled("Failing test: Discount applied to every laptop regardless of mouse presence.")
    @Test
    public void testBundleDiscount_OneLaptop() {
        laptopItem = new CartItem(laptop, 1);

        cartItems.add(laptopItem);
        double discount = discountService.applyDiscount(500.0, customerType, cartItems, null);

        assertEquals(500.0, discount); // No discount applied
    }

    @Test
    public void testBundleDiscount_OneMouse() {
        mouseItem = new CartItem(mouse, 1);

        cartItems.add(mouseItem);
        double discount = discountService.applyDiscount(50.0, customerType, cartItems, null);

        assertEquals(50.0, discount); // No discount applied
    }

    // @Disabled("Failing test: Bundle discount applied to wrong item.")
    @Test
    public void testBundleDiscount_OneMouseLaptopPair() {
        // Arrange
        laptopItem = new CartItem(laptop, 1);
        mouseItem = new CartItem(mouse, 1);

        // Act
        cartItems.add(laptopItem);
        cartItems.add(mouseItem);
        double discount = discountService.applyDiscount(550.0, customerType, cartItems, null);

        // Assert
        assertEquals(545.0, discount); // 10% off mouse = $5.0 discount
    }

    // Test two mouse-laptop pairs bundle discount
    // @Disabled("Failing test: Bundle discount applied to wrong item.")
    @Test
    public void testBundleDiscount_TwoMouseLaptopPairs() {
        laptopItem = new CartItem(laptop, 2);
        mouseItem = new CartItem(mouse, 2);

        cartItems.add(laptopItem);
        cartItems.add(mouseItem);
        double discount = discountService.applyDiscount(1100.0, customerType, cartItems, null);

        assertEquals(1090.0, discount); // 10% off both mice = $10.0 discount
    }

    // Test one laptop, two mice bundle discount
    // @Disabled("Failing test: Bundle discount applied to wrong item.")
    @Test
    public void testBundleDiscount_TwoMiceOneLaptop() {
        laptopItem = new CartItem(laptop, 1);
        mouseItem = new CartItem(mouse, 2);

        cartItems.add(laptopItem);
        cartItems.add(mouseItem);
        double discount = discountService.applyDiscount(600.0, customerType, cartItems, null);

        assertEquals(595.0, discount); // 10% off one mouse = $5.0 discount
    }

    // Test two laptops, one mouse bundle discount
    // @Disabled("Failing test: Bundle discount applied to wrong item.")
    @Test
    public void testBundleDiscount_TwoLaptopsOneMouse() {
        laptopItem = new CartItem(laptop, 2);
        mouseItem = new CartItem(mouse, 1);

        cartItems.add(laptopItem);
        cartItems.add(mouseItem);
        double discount = discountService.applyDiscount(1050.0, customerType, cartItems, null);
        
     
        assertEquals(1045.0, discount); // 10% off one mouse = $5.0 discount
    }


}