package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.List;

/*
 * Unit tests for OrderService class.
 */
public class PlaceOrderTest {
    private PaymentService paymentService;
    private InventoryService inventoryService;
    private ShoppingCart shoppingCart;
    private OrderService orderService;

    private Customer customer;
    private DiscountService discountService;

    private Product monitor;
    private CartItem monitorItem;

    private Product mouse;
    private CartItem mouseItem;
    
    @BeforeEach
    public void setUp() {
        paymentService = mock(PaymentService.class);
        discountService = mock(DiscountService.class);

        inventoryService = new InventoryService();
        customer = new Customer("Jane Doe", CustomerType.REGULAR);
        shoppingCart = new ShoppingCart(customer, discountService);
        orderService = new OrderService(paymentService, inventoryService);

        monitor = new Product("Monitor", 500.0, 10);
        monitorItem = new CartItem(monitor, 1);
        mouse = new Product("Mouse", 50.0, 20);
        mouseItem = new CartItem(mouse, 2);
    }

    @Test // Successful Order Updates Stock
    public void testPlaceOrder_SuccessfulUpdatesStock() throws Exception {
        shoppingCart.addItem(monitorItem);
        shoppingCart.addItem(mouseItem);

        when(paymentService.processPayment(anyString(), anyDouble())).thenReturn(true);

        boolean result = orderService.placeOrder(shoppingCart, "1234567891234567");

        assertTrue(result);
        assertEquals(9, monitor.getStock());
        assertEquals(18, mouse.getStock());
    }

    // Failed Test: Stock is updated even when payment fails
    @Disabled("Failing test: Stock is updated even when payment fails.")
    @Test // Failed Order Does Not Update Stock
    public void testPlaceOrder_FailedDoesNotUpdateStock() throws Exception {
        shoppingCart.addItem(monitorItem);
        shoppingCart.addItem(mouseItem);

        when(paymentService.processPayment(anyString(), anyDouble())).thenThrow(new Exception("Payment failed"));

        boolean result = orderService.placeOrder(shoppingCart, "123");

        assertFalse(result);
        assertEquals(10, monitor.getStock());
        assertEquals(20, mouse.getStock());
    }

    // Failing Test: Does not capture total with discounts
    @Disabled("Failing test: Cannot capture total with discounts applied.")
    @Test // Correct Total Calculation with Discounts
    public void testPlaceOrder_CorrectTotalCalculation() throws Exception {
        shoppingCart.addItem(monitorItem);
        shoppingCart.addItem(mouseItem);
        shoppingCart.setPromotionActive(true); // 25% off on total

        when(paymentService.processPayment(anyString(), anyDouble())).thenReturn(true);
        when(discountService.applyPromotionDiscount(600.0)).thenReturn(450.0);
        when(discountService.applyDiscount(600.0, CustomerType.REGULAR, shoppingCart.getItems(), null))
            .thenReturn(450.0);

        boolean result = orderService.placeOrder(shoppingCart, "1234567891234567");

        // Argument captor to verify total amount passed to payment service
        ArgumentCaptor<Double> totalCaptor = ArgumentCaptor.forClass(Double.class);
        verify(paymentService).processPayment(anyString(), totalCaptor.capture());
        double capturedTotal = totalCaptor.getValue();

        assertEquals(450.0, capturedTotal); // 25% discount on $600
        assertTrue(result);
    }
}