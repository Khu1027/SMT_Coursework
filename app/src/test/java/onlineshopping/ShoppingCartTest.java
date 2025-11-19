package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import static org.mockito.Mockito.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/*
 * Unit tests for ShoppingCart class.
 */
public class ShoppingCartTest {
    private DiscountService discountService;
    private ShoppingCart shoppingCart;
    private Customer customer;

    private Product monitor;
    private CartItem monitorItem;

    private Product mouse;
    private CartItem mouseItem;

    @BeforeEach
    public void setUp() {
        discountService = mock(DiscountService.class);
        customer = new Customer("Jane Doe", CustomerType.REGULAR);
        shoppingCart = new ShoppingCart(customer, discountService);

        monitor = new Product("Monitor", 500.0, 10);
        monitorItem = new CartItem(monitor, 1);

        mouse = new Product("Mouse", 50.0, 20);
        mouseItem = new CartItem(mouse, 2);
    }

    @Test // Test adding item to cart
    public void testShoppingCart_AddItem() {
        shoppingCart.addItem(monitorItem);
        
        assertEquals(1, shoppingCart.getItems().size());
        assertEquals(monitorItem, shoppingCart.getItems().get(0));
    }

    @Test // Test removing item from cart
    public void testShoppingCart_RemoveItem() {
        shoppingCart.addItem(monitorItem);
        shoppingCart.removeItem(monitorItem);

        assertEquals(0, shoppingCart.getItems().size());
    }

    @Test // Test getting items from cart
    public void testShoppingCart_GetItems() {
        shoppingCart.addItem(monitorItem);
        shoppingCart.addItem(mouseItem);

        assertEquals(2, shoppingCart.getItems().size());
        assertTrue(shoppingCart.getItems().contains(monitorItem));
        assertTrue(shoppingCart.getItems().contains(mouseItem));
    }

    @Test // Test calculating total price
    public void testShoppingCart_CalculateTotal() {
        shoppingCart.addItem(monitorItem);
        shoppingCart.addItem(mouseItem);

        assertEquals(600.0, shoppingCart.calculateTotal());
    }

    @Test // Test calculating final price without discounts
    public void testShoppingCart_CalculateFinalPrice() {
        shoppingCart.addItem(monitorItem);

        when(discountService.applyDiscount(500.0, CustomerType.REGULAR, shoppingCart.getItems(), null))
            .thenReturn(500.0);

        assertEquals(500.0, shoppingCart.calculateFinalPrice());
        verify(discountService, times(1))
            .applyDiscount(500.0, CustomerType.REGULAR, shoppingCart.getItems(), null);
        verify(discountService, never()).applyPromotionDiscount(anyDouble());
    }

    @Test // Test percentage coupon code logic
    public void testShoppingCart_ApplyPercentageCouponCode() {
        shoppingCart.addItem(monitorItem);
        shoppingCart.applyCouponCode("DISCOUNT10");

        when(discountService.applyDiscount(eq(500.0), eq(CustomerType.REGULAR), anyList(), eq("DISCOUNT10")))
            .thenReturn(450.0);

        assertEquals(450.0, shoppingCart.calculateFinalPrice());
        verify(discountService, times(1))
            .applyDiscount(eq(500.0), eq(CustomerType.REGULAR), anyList(), eq("DISCOUNT10"));
    }

    @Test // Test fixed coupon code logic
    public void testShoppingCart_ApplyFixedCouponCode() {
        shoppingCart.addItem(monitorItem);
        shoppingCart.applyCouponCode("SAVE50");

        when(discountService.applyDiscount(eq(500.0), eq(CustomerType.REGULAR), anyList(), eq("SAVE50")))
            .thenReturn(450.0);

        assertEquals(450.0, shoppingCart.calculateFinalPrice());
        verify(discountService, times(1))
            .applyDiscount(eq(500.0), eq(CustomerType.REGULAR), anyList(), eq("SAVE50"));
    }

    @Test // Test active promotion logic
    public void testShoppingCart_PromotionActive() {
        shoppingCart.addItem(monitorItem);
        shoppingCart.setPromotionActive(true);

        when(discountService.applyPromotionDiscount(500.0)).thenReturn(375.0);

        assertEquals(375.0, shoppingCart.calculateFinalPrice());
        verify(discountService, times(1))
            .applyPromotionDiscount(500.0);
    }

    @Test // Test printing receipt
    public void testShoppingCart_PrintReceipt() {
        shoppingCart.addItem(monitorItem);
        shoppingCart.addItem(mouseItem);
        shoppingCart.setPromotionActive(true); // 25% off on total

        when(discountService.applyPromotionDiscount(600.0)).thenReturn(450.0);

        // Capture the print output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        shoppingCart.printReceipt();

        System.setOut(originalOut);
        String receiptOutput = outputStream.toString();

        assertTrue(receiptOutput.contains("----- Shopping Cart Receipt -----"));
        assertTrue(receiptOutput.contains("Monitor - 1 x $500.0"));
        assertTrue(receiptOutput.contains("Mouse - 2 x $50.0"));
        assertTrue(receiptOutput.contains("Total before discount: $600.0"));
        assertTrue(receiptOutput.contains("Final price after discounts: $450.0"));
    }

}
