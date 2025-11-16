package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

/*
 * Unit tests for the CustomerType enum.
 * Test different customer types
 */
public class CustomerTypeTest {

    @Test
    public void testCustomerTypeValues() {
        CustomerType[] types = CustomerType.values();
        assertEquals(3, types.length);
        assertEquals(CustomerType.REGULAR, types[0]);
        assertEquals(CustomerType.PREMIUM, types[1]);
        assertEquals(CustomerType.VIP, types[2]);
    }
    
}
