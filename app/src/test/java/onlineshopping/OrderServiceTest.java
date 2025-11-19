package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import static org.mockito.Mockito.*;
// import java.util.concurrent.locks.Lock;
// import java.util.concurrent.locks.ReentrantLock;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/*
 * Unit tests for OrderService class.
 */
public class OrderServiceTest {
    private PaymentService paymentService;
    private InventoryService inventoryService;
    private ShoppingCart shoppingCart;
    private OrderService orderService;

    private Product monitor;
    private CartItem monitorItem;
    
    @BeforeEach
    public void setUp() {
        paymentService = mock(PaymentService.class);
        inventoryService = mock(InventoryService.class);
        shoppingCart = mock(ShoppingCart.class);
        orderService = new OrderService(paymentService, inventoryService);

        monitor = mock(Product.class);
        monitorItem = mock(CartItem.class);

        when(monitor.getStock()).thenReturn(10);
        when(monitorItem.getProduct()).thenReturn(monitor);
        when(monitorItem.getQuantity()).thenReturn(3);

        when(shoppingCart.getItems()).thenReturn(List.of(monitorItem));
        when(shoppingCart.calculateTotal()).thenReturn(1500.0);
    }

    @Test // Successful Order Placement: updates stock and processes payment
    public void testOrderService_SuccessfulOrderPlacement() throws Exception {
        when(paymentService.processPayment(eq("1234567891234567"), anyDouble())).thenReturn(true);
        
        boolean result = orderService.placeOrder(shoppingCart, "1234567891234567");
        
        assertTrue(result);
        verify(paymentService, times(1)).processPayment(eq("1234567891234567"), anyDouble());
    }

    @Test // Failed Order Placement: payment processing fails
    public void testOrderService_FailedOrderPlacement()throws Exception {
        when(paymentService.processPayment(eq("123"), anyDouble())).thenThrow(new Exception("Payment failed"));

        // assert system.err.println was called with "Order failed: Payment failed"
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(outputStream));

        boolean result = orderService.placeOrder(shoppingCart, "123");

        System.setErr(originalErr);

        assertFalse(result);
        verify(paymentService, times(1)).processPayment(eq("123"), anyDouble());

        // Check error output
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Order failed: Payment failed"));
    }

    // Additional tests can be added here for concurrency and other scenarios
}
