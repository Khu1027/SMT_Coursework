package onlineshopping;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

/*
 * Unit tests for the PaymentService class.
 * Checks for valid card numbers and payment amounts.
 */
public class PaymentServiceTest {
    private PaymentService paymentService;
    private String validCardNumber;
    private double validAmount;

    @BeforeEach
    public void setUp() {
        paymentService = new PaymentService();
        validCardNumber = "1234567890123456";
        validAmount = 100.0;
    }

    @Test
    public void testPaymentService_ValidPayment() {
        try {
            boolean result = paymentService.processPayment(validCardNumber, validAmount);

            assertTrue(result);
        } catch (Exception e) {
            fail("Payment processing threw an exception for valid input");
        }
    }

    @Test
    public void testPaymentService_InvalidCardNumber() {
        String invalidCardNumber = "abcdefghijklmnop"; // Invalid, non-numeric card number. length is 16
        
        try {
            paymentService.processPayment(invalidCardNumber, validAmount);
            fail("Expected Exception for invalid card number.");
        } catch (Exception e) {
            String expectedMessage = "Payment failed: Invalid card or amount.";
            String actualMessage = e.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    // This test fails because PaymentService only throws an exception if both the card and amount are invalid.
    // @Disabled("Failing test: PaymentService allows invalid card numbers without exception.")
    @Test
    public void testPaymentService_InvalidCardLength() {
        String invalidCardNumber = "1234"; // Invalid length != 16
        
        try {
            paymentService.processPayment(invalidCardNumber, validAmount);
            fail("Expected Exception for invalid card length.");
        } catch (Exception e) {
            String expectedMessage = "Payment failed: Invalid card or amount.";
            String actualMessage = e.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    // This test fails because PaymentService only throws an exception if both the card and amount are invalid.
    // The service also allows zero amounts to be processed without error.
    // @Disabled("Failing test: PaymentService allows zero amounts without exception.")
    @Test
    public void testPaymentService_InvalidAmountZero() {
        double invalidAmount = 0.0; // Invalid amount is zero or negative
        
        try {
            paymentService.processPayment(validCardNumber, invalidAmount);
            fail("Expected Exception for invalid amount Zero");
        } catch (Exception e) {
            String expectedMessage = "Payment failed: Invalid card or amount.";
            String actualMessage = e.getMessage();

            assertTrue(actualMessage.contains(expectedMessage)); 
        }
    }

    //This test fails because PaymentService only throws an exception if both the card and amount are invalid.
    // @Disabled("Failing test: PaymentService allows invalid amounts without exception.")
    @Test
    public void testPaymentService_InvalidAmountNegative() {
        double invalidAmount = -50.0; // Invalid amount is zero or negative
        
        try {
            paymentService.processPayment(validCardNumber, invalidAmount);
            fail("Expected Exception for invalid amount Negative");
        } catch (Exception e) {
            String expectedMessage = "Payment failed: Invalid card or amount.";
            String actualMessage = e.getMessage();
            
            assertTrue(actualMessage.contains(expectedMessage)); 
        }
    }

    @Test
    public void testPaymentService_InvalidCardAndAmount() {
        String invalidCardNumber = "1234"; // Invalid length != 16
        double invalidAmount = -50.0; // Invalid amount is zero or negative
        
        try {
            paymentService.processPayment(invalidCardNumber, invalidAmount);
            fail("Expected Exception for invalid card number and amount");
        } catch (Exception e) {
            String expectedMessage = "Payment failed: Invalid card or amount.";
            String actualMessage = e.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}
