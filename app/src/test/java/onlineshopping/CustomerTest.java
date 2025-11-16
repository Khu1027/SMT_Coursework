package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class CustomerTest {
    private Customer regularCustomer;
    private Customer premiumCustomer;
    private Customer vipCustomer;

    @BeforeEach
    public void setUp() {
        regularCustomer = new Customer("Raoul", CustomerType.REGULAR);
        premiumCustomer = new Customer("Priya", CustomerType.PREMIUM);
        vipCustomer = new Customer("Vicky", CustomerType.VIP);
    }

    @Test
    public void testCustomerGetName() {
        assertEquals("Raoul", regularCustomer.getName());
        assertEquals("Priya", premiumCustomer.getName());
        assertEquals("Vicky", vipCustomer.getName());
    }

    @Test
    public void testCustomerGetCustomerType() {
        assertEquals(CustomerType.REGULAR, regularCustomer.getCustomerType());
        assertEquals(CustomerType.PREMIUM, premiumCustomer.getCustomerType());
        assertEquals(CustomerType.VIP, vipCustomer.getCustomerType());
    }

    @Test
    public void testCustomerSetName() {
        regularCustomer.setName("Ravi");
        assertEquals("Ravi", regularCustomer.getName());
    }

    @Test
    public void testCustomerSetCustomerType() {
        regularCustomer.setCustomerType(CustomerType.PREMIUM);
        assertEquals(CustomerType.PREMIUM, regularCustomer.getCustomerType());
    }

    
}
