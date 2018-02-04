package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.educational.RubikCube;
import by.epam.gameroom.exceptions.IncorrectValueException;
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
    public static Object[][] exceptionResults() {
        String[] notValidParametersFirst = {"type=RubikCube", "price=-20.5", "isAssembled=+"};
        String[] notValidParametersSecond = {"type=RubikCube", "price=20.5", "isAssembled=+-"};
        return new Object[][]{
                {notValidParametersFirst},
                {notValidParametersSecond}
        };
    }

    @Test
    @UseDataProvider("validResults")
    public void shouldBuiltObjectBeCorrect(String[] values, RubikCube expectedToy) throws IncorrectValueException {
        RubikCube actualToy = RUBIK_CUBE_BUILDER.createToy(values);

        Assert.assertEquals(expectedToy, actualToy);
    }

    @Test
    @UseDataProvider("notValidResults")
    public void shouldBuiltObjectBeNotSimilar(String[] values, RubikCube expectedToy) throws IncorrectValueException {
        RubikCube actualToy = RUBIK_CUBE_BUILDER.createToy(values);

        Assert.assertNotEquals(expectedToy, actualToy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldIncorrectValueCauseIllegalArgumentException() throws IncorrectValueException {
        String[] nullArray = null;

        RUBIK_CUBE_BUILDER.createToy(nullArray);
    }

    @Test(expected = IncorrectValueException.class)
    @UseDataProvider("exceptionResults")
    public void shouldIncorrectParametersCauseIncorrectArgumentException(String[] parameters) throws IncorrectValueException {
        RUBIK_CUBE_BUILDER.createToy(parameters);
    }

}
