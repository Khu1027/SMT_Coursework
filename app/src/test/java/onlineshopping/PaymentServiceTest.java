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
    public void testPaymentServiceValidPayment() {
        try {
            boolean result = paymentService.processPayment(validCardNumber, validAmount);
            assertTrue(result);
        } catch (Exception e) {
            fail("Payment processing threw an exception for valid input");
        }
    }

    @Test
    public void testPaymentServiceInvalidCardAndAmount() {
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

    // This test fails because PaymentService only throws an exception if both the card and amount are invalid.
    //@Disabled("Failing test: PaymentService allows invalid card numbers without exception.")
    @Test
    public void testPaymentServiceInvalidCard() {
        String invalidCardNumber = "1234"; // Invalid length != 16
        try {
            boolean result = paymentService.processPayment(invalidCardNumber, validAmount);
            fail("Expected Exception for invalid card number. Result equals: " + result);
        } catch (Exception e) {
            String expectedMessage = "Payment failed: Invalid card or amount.";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    // This test fails because PaymentService only throws an exception if both the card and amount are invalid.
    // The service also allows zero amounts to be processed without error.
    //@Disabled("Failing test: PaymentService allows zero amounts without exception.")
    @Test
    public void testPaymentServiceInvalidAmountZero() {
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
    //@Disabled("Failing test: PaymentService allows invalid amounts without exception.")
    @Test
    public void testPaymentServiceInvalidAmountNegative() {
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
}
