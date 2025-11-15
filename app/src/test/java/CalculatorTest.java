import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testAdd() {
        Calculator c = new Calculator();
        assertEquals(7, c.add(3, 4));
    }

    @Test
    void testMultiply() {
        Calculator c = new Calculator();
        assertEquals(12, c.multiply(3, 4));
    }
}
