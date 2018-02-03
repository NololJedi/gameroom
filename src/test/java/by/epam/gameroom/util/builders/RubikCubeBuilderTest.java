package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.educational.RubikCube;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class RubikCubeBuilderTest {

    private final static RubikCubeBuilder RUBIK_CUBE_BUILDER = new RubikCubeBuilder();

    @DataProvider
    public static Object[][] validResults() {
        String[] validValuesFirst = {"type=RubikCube", "price=20.5", "isAssembled=+"};
        String[] validValuesSecond = {"type=RubikCube", "price=22.5", "isAssembled=-"};
        String[] validValuesThird = {"type=RubikCube", "price=25.5", "isAssembled=+"};

        RubikCube first = new RubikCube(20.5, true);
        RubikCube second = new RubikCube(22.5, false);
        RubikCube third = new RubikCube(25.5, true);

        return new Object[][]{
                {validValuesFirst, first},
                {validValuesSecond, second},
                {validValuesThird, third}
        };
    }

    @DataProvider
    public static Object[][] notValidResults() {
        String[] notValidValuesFirst = {"type=RubikCube", "price=20.5", "isAssembled=+"};
        String[] notValidValuesSecond = {"type=RubikCube", "price=22.5", "isAssembled=-"};
        String[] notValidValuesThird = {"type=RubikCube", "price=25.5", "isAssembled=+"};

        RubikCube first = new RubikCube(22.5, true);
        RubikCube second = new RubikCube(2.5, true);
        RubikCube third = new RubikCube(5.5, false);

        return new Object[][]{
                {notValidValuesFirst, first},
                {notValidValuesSecond, second},
                {notValidValuesThird, third}
        };
    }

    @DataProvider
    public static Object[][] incorrectValuesIllegalArgument() {
        String[] notValidValuesFirst = {"type=RubikCube", "isAssembled=+"};
        String[] notValidValuesSecond = {"type=RubikCube", "price=22.5", "size=2.0", "isAssembled=-"};
        String[] notValidValuesThird = {"type=RubikCube", "price=25.5", ""};
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
        String[] notValidValuesFirst = {"type=RubikCube", "price=", "isAssembled=+"};
        String[] notValidValuesSecond = {"type=RubikCube", "price=22.5", "isAssembled="};
        String[] notValidValuesThird = {"type=RubikCube", "price", "isAssembled=+"};

        return new Object[][]{
                {notValidValuesFirst},
                {notValidValuesSecond},
                {notValidValuesThird}
        };
    }

    @DataProvider
    public static Object[][] incorrectValuesNumberFormat() {
        String[] notValidValuesFirst = {"type=RubikCube", "price=..5", "isAssembled=+"};
        String[] notValidValuesSecond = {"type=RubikCube", "price=22O.5", "isAssembled=-"};
        String[] notValidValuesThird = {"type=RubikCube", "price=2!5.5", "isAssembled=+"};

        return new Object[][]{
                {notValidValuesFirst},
                {notValidValuesSecond},
                {notValidValuesThird}
        };
    }

    @Test
    @UseDataProvider("validResults")
    public void shouldBuiltObjectBeCorrect(String[] values, RubikCube expectedToy) {
        RubikCube actualToy = RUBIK_CUBE_BUILDER.createToy(values);

        Assert.assertEquals(expectedToy, actualToy);
    }

    @Test
    @UseDataProvider("notValidResults")
    public void shouldBuiltObjectBeNotSimilar(String[] values, RubikCube expectedToy) {
        RubikCube actualToy = RUBIK_CUBE_BUILDER.createToy(values);

        Assert.assertNotEquals(expectedToy, actualToy);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("incorrectValuesIllegalArgument")
    public void shouldIncorrectValuesCauseIllegalArgumentException(String[] values) {
        RUBIK_CUBE_BUILDER.createToy(values);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    @UseDataProvider("incorrectValuesArrayIndexOutOfBound")
    public void shouldIncorrectValuesCauseArrayIndexOutOfBoundsException(String[] values) {
        RUBIK_CUBE_BUILDER.createToy(values);
    }

    @Test(expected = NumberFormatException.class)
    @UseDataProvider("incorrectValuesNumberFormat")
    public void shouldIncorrectValuesCauseIncorrectValuesNumberFormatException(String[] values) {
        RUBIK_CUBE_BUILDER.createToy(values);
    }

}
