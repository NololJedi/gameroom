package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.sport.JumpRope;
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
    public static Object[][] incorrectValuesIllegalArgument() {
        String[] notValidValuesFirst = {"type=JumpRope"};
        String[] notValidValuesSecond = {"type=JumpRope", "price=22.5", "numLength=3", "numWeight=5"};
        String[] notValidValuesThird = {"type=JumpRope", "price=25.5", ""};
        String[] nullArray = null;
        String[] emptyArray = {};

        return new Object[][]{
                {notValidValuesFirst},
                {notValidValuesSecond},
                {notValidValuesThird},
                {nullArray},
                {emptyArray}
        };
    }

    @DataProvider
    public static Object[][] incorrectValuesArrayIndexOutOfBound() {
        String[] notValidValuesFirst = {"type=JumpRope", "price20.5", "numLength=2"};
        String[] notValidValuesSecond = {"type=JumpRope", "price=22.5", "i"};
        String[] notValidValuesThird = {"type=JumpRope", "price=25.5", "numLength5"};

        return new Object[][]{
                {notValidValuesFirst},
                {notValidValuesSecond},
                {notValidValuesThird}
        };
    }

    @DataProvider
    public static Object[][] incorrectValuesNumberFormat() {
        String[] notValidValuesFirst = {"type=JumpRope", "price=20O.5", "numLength=2"};
        String[] notValidValuesSecond = {"type=JumpRope", "price=22.5", "numLength=.02"};
        String[] notValidValuesThird = {"type=JumpRope", "price=2.2.5", "numLength=5"};

        return new Object[][]{
                {notValidValuesFirst},
                {notValidValuesSecond},
                {notValidValuesThird}
        };
    }

    @Test
    @UseDataProvider("validResults")
    public void shouldBuiltObjectBeCorrect(String[] values, JumpRope expectedToy) {
        JumpRope actualToy = JUMP_ROPE_BUILDER.createToy(values);

        Assert.assertEquals(expectedToy, actualToy);
    }

    @Test
    @UseDataProvider("notValidResults")
    public void shouldBuiltObjectBeNotSimilar(String[] values, JumpRope expectedToy) {
        JumpRope actualToy = JUMP_ROPE_BUILDER.createToy(values);

        Assert.assertNotEquals(expectedToy, actualToy);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("incorrectValuesIllegalArgument")
    public void shouldIncorrectValuesCauseIllegalArgumentException(String[] values) {
        JUMP_ROPE_BUILDER.createToy(values);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    @UseDataProvider("incorrectValuesArrayIndexOutOfBound")
    public void shouldIncorrectValuesCauseArrayIndexOutOfBoundsException(String[] values) {
        JUMP_ROPE_BUILDER.createToy(values);
    }

    @Test(expected = NumberFormatException.class)
    @UseDataProvider("incorrectValuesNumberFormat")
    public void shouldIncorrectValuesCauseIncorrectValuesNumberFormatException(String[] values) {
        JUMP_ROPE_BUILDER.createToy(values);
    }

}
