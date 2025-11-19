package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

/*
    * Unit tests for the Customer class.
 */
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
    public void testCustomer_GetName() {
        assertEquals("Raoul", regularCustomer.getName());
        assertEquals("Priya", premiumCustomer.getName());
        assertEquals("Vicky", vipCustomer.getName());
    }

    @Test
    public void testCustomer_GetCustomerType() {
        assertEquals(CustomerType.REGULAR, regularCustomer.getCustomerType());
        assertEquals(CustomerType.PREMIUM, premiumCustomer.getCustomerType());
        assertEquals(CustomerType.VIP, vipCustomer.getCustomerType());
    }

    @Test
    public void testCustomer_SetName() {
        regularCustomer.setName("Ravi");

        assertEquals("Ravi", regularCustomer.getName());
    }

    @Test
    public void testCustomer_SetCustomerType() {
        regularCustomer.setCustomerType(CustomerType.PREMIUM);

        assertEquals(CustomerType.PREMIUM, regularCustomer.getCustomerType());
    }
    
}
