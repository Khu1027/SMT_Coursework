package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import static org.mockito.Mockito.*;
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

    private Product monitor;
    private CartItem monitorItem;

    private Product mouse;
    private CartItem mouseItem;
    
    @BeforeEach
    public void setUp() {
        paymentService = mock(PaymentService.class);
        inventoryService = mock(InventoryService.class);
        shoppingCart = mock(ShoppingCart.class);
        orderService = new OrderService(paymentService, inventoryService);

        monitor = new Product("Monitor", 500.0, 10);
        monitorItem = new CartItem(monitor, 1);
        mouse = new Product("Mouse", 50.0, 20);
        mouseItem = new CartItem(mouse, 2);

        when(shoppingCart.getItems()).thenReturn(List.of(monitorItem, mouseItem));
        when(shoppingCart.calculateTotal()).thenReturn(600.0);
    }
}