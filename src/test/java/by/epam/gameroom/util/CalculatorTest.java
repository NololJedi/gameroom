package by.epam.gameroom.util;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void shouldCalculationBeSuccessful() {
        double expected = 4.0;
        double first = 6.0;
        double second = 2.0;
        double actual = Calculator.calculate(first, second);

        Assert.assertEquals(expected, actual, 0.001);
    }

    @Test
    public void shouldCalculationBeNotSuccessful() {
        double expected = 3.0;
        double first = 7.0;
        double second = 2.0;
        double actual = Calculator.calculate(first, second);

        Assert.assertNotEquals(expected, actual, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCalculationCauseException() {
        double first = 1.0;
        double second = 2.0;
        Calculator.calculate(first, second);
    }
}
