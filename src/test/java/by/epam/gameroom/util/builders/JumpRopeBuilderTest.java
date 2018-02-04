package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.sport.JumpRope;
import by.epam.gameroom.exceptions.IncorrectValueException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class JumpRopeBuilderTest {

    private final static JumpRopeBuilder JUMP_ROPE_BUILDER = new JumpRopeBuilder();

    @DataProvider
    public static Object[][] validResults() {
        String[] validValuesFirst = {"type=JumpRope", "price=20.5", "numLength=2"};
        String[] validValuesSecond = {"type=JumpRope", "price=22.5", "numLength=3"};
        String[] validValuesThird = {"type=JumpRope", "price=25.5", "numLength=5"};

        JumpRope first = new JumpRope(20.5, 2);
        JumpRope second = new JumpRope(22.5, 3);
        JumpRope third = new JumpRope(25.5, 5);

        return new Object[][]{
                {validValuesFirst, first},
                {validValuesSecond, second},
                {validValuesThird, third}
        };
    }

    @DataProvider
    public static Object[][] notValidResults() {
        String[] notValidValuesFirst = {"type=JumpRope", "price=20.5", "numLength=2"};
        String[] notValidValuesSecond = {"type=JumpRope", "price=22.5", "numLength=3"};
        String[] notValidValuesThird = {"type=JumpRope", "price=25.5", "numLength=5"};

        JumpRope first = new JumpRope(20.5, 4);
        JumpRope second = new JumpRope(22.5, 6);
        JumpRope third = new JumpRope(25.5, 7);

        return new Object[][]{
                {notValidValuesFirst, first},
                {notValidValuesSecond, second},
                {notValidValuesThird, third}
        };
    }

    @DataProvider
    public static Object[][] exceptionResults() {
        String[] notValidParametersFirst = {"type=JumpRope", "price=-20.5", "numLength=2"};
        String[] notValidParametersSecond = {"type=JumpRope", "price=20.5", "numLength=Two"};

        return new Object[][]{
                {notValidParametersFirst},
                {notValidParametersSecond}
        };
    }

    @Test
    @UseDataProvider("validResults")
    public void shouldBuiltObjectBeCorrect(String[] values, JumpRope expectedToy) throws IncorrectValueException {
        JumpRope actualToy = JUMP_ROPE_BUILDER.createToy(values);

        Assert.assertEquals(expectedToy, actualToy);
    }

    @Test
    @UseDataProvider("notValidResults")
    public void shouldBuiltObjectBeNotSimilar(String[] values, JumpRope expectedToy) throws IncorrectValueException {
        JumpRope actualToy = JUMP_ROPE_BUILDER.createToy(values);

        Assert.assertNotEquals(expectedToy, actualToy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldIncorrectValueCauseIllegalArgumentException() throws IncorrectValueException {
        String[] nullArray = null;

        JUMP_ROPE_BUILDER.createToy(nullArray);
    }

    @Test(expected = IncorrectValueException.class)
    @UseDataProvider("exceptionResults")
    public void shouldIncorrectParametersCauseIncorrectArgumentException(String[] parameters) throws IncorrectValueException {
        JUMP_ROPE_BUILDER.createToy(parameters);
    }

}
