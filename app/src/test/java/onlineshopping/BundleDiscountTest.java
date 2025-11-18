package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

/*
    * Test bundle discount
    * 1. one mouse laptop pair
    * 2. two mouse laptop pairs
    * 3. one laptop, two mice
*/
public class BundleDiscountTest {
    private DiscountService discountService;
    private List<CartItem> cartItems;
    private CustomerType customerType;
    private CartItem laptop;
    private CartItem mouse;

    @BeforeEach
    public void setUp() {
        discountService = new DiscountService();
        cartItems = new ArrayList<>();
        customerType = CustomerType.REGULAR;
    }

    @Test
    public void testOneMouseLaptopPair() {
        laptop = new CartItem("Laptop", 1000.0, 1);
        mouse = new CartItem("Mouse", 50.0, 1);
        cartItems.add(laptop);
        cartItems.add(mouse);
        double discount = discountService.calculateBundleDiscount(cartItems, customerType);
        assertEquals(50.0, discount, 0.01);


}
