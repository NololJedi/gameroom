package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.electronic.GameConsole;
import by.epam.gameroom.exceptions.IncorrectValueException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class GameConsoleBuilderTest {

    private static final GameConsoleBuilder GAME_CONSOLE_BUILDER = new GameConsoleBuilder();

    @DataProvider
    public static Object[][] validResults() {
        String[] validValuesFirst = {"type=GameConsole", "name=Xbox360", "price=45.0", "size=25.0", "isInternetAvailable=+"};
        String[] validValuesSecond = {"type=GameConsole", "name=Dendi", "price=60.0", "size=10.0", "isInternetAvailable=-"};
        String[] validValuesThird = {"type=GameConsole", "name=PlayStation4", "price=45.0", "size=15.0", "isInternetAvailable=+"};

        GameConsole first = new GameConsole("Xbox360", 45.0, 25.0, true);
        GameConsole second = new GameConsole("Dendi", 60.0, 10.0, false);
        GameConsole third = new GameConsole("PlayStation4", 45.0, 15.0, true);

        return new Object[][]{
                {validValuesFirst, first},
                {validValuesSecond, second},
                {validValuesThird, third}
        };
    }

    @DataProvider
    public static Object[][] notValidResults() {
        String[] notValidValuesFirst = {"type=GameConsole", "name=Xbox360", "price=45.0", "size=25.0", "isInternetAvailable=+"};
        String[] notValidValuesSecond = {"type=GameConsole", "name=Dendi", "price=60.0", "size=10.0", "isInternetAvailable=-"};
        String[] notValidValuesThird = {"type=GameConsole", "name=PlayStation4", "price=45.0", "size=15.0", "isInternetAvailable=+"};

        GameConsole first = new GameConsole("Xbox360", 45.0, 25.0, false);
        GameConsole second = new GameConsole("Sega", 60.0, 10.0, false);
        GameConsole third = new GameConsole("PlayStation3", 23.0, 15.0, true);

        return new Object[][]{
                {notValidValuesFirst, first},
                {notValidValuesSecond, second},
                {notValidValuesThird, third}
        };
    }

    @DataProvider
    public static Object[][] exceptionResults() {
        String[] notValidParametersFirst = {"type=GameConsole", "name=Xbox!360", "price=45.0", "size=25.0", "isInternetAvailable=+"};
        String[] notValidParametersSecond = {"type=GameConsole", "name=Xbox360", "price=-45.0", "size=25.0", "isInternetAvailable=+"};
        String[] notValidParametersThird = {"type=GameConsole", "name=Xbox360", "price=45.0", "size=25.0", "isInternetAvailable=false"};

        return new Object[][]{
                {notValidParametersFirst},
                {notValidParametersSecond},
                {notValidParametersThird}
        };
    }

    @Test
    @UseDataProvider("validResults")
    public void shouldBuiltObjectBeCorrect(String[] values, GameConsole expectedToy) throws IncorrectValueException {
        GameConsole actualToy = GAME_CONSOLE_BUILDER.createToy(values);

        Assert.assertEquals(expectedToy, actualToy);
    }

    @Test
    @UseDataProvider("notValidResults")
    public void shouldBuiltObjectBeNotSimilar(String[] values, GameConsole expectedToy) throws IncorrectValueException {
        GameConsole actualToy = GAME_CONSOLE_BUILDER.createToy(values);

        Assert.assertNotEquals(expectedToy, actualToy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldIncorrectParametersCauseIllegalArgumentException() throws IncorrectValueException {
        String[] nullArray = null;

        GAME_CONSOLE_BUILDER.createToy(nullArray);
    }

    @Test(expected = IncorrectValueException.class)
    @UseDataProvider("exceptionResults")
    public void shouldIncorrectParametersCauseIncorrectArgumentException(String[] parameters) throws IncorrectValueException {
        GAME_CONSOLE_BUILDER.createToy(parameters);
    }

}
