package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.electronic.GameConsole;
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
    public static Object[][] incorrectValuesIllegalArgument() {
        String[] notValidValuesFirst = {"name=Xbox360", "price=45.0", "size=25.0", "isInternetAvailable=+"};
        String[] notValidValuesSecond = {"type=GameConsole", "name=Dendi", "price=60.0", "size=10.0"};
        String[] notValidValuesThird = {"type=GameConsole", "name=PlayStation4", "size=15.0", "isInternetAvailable=+"};
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
        String[] notValidValuesFirst = {"type=GameConsole", "nameXbox360", "price=45.0", "size=25.0", "isInternetAvailable=+"};
        String[] notValidValuesSecond = {"type=GameConsole", "name=Dendi", "price60.0", "size=10.0", "isInternetAvailable=-"};
        String[] notValidValuesThird = {"type=GameConsole", "name=PlayStation4", "price=45.0", "s15.0", "isInternetAvailable=+"};

        return new Object[][]{
                {notValidValuesFirst},
                {notValidValuesSecond},
                {notValidValuesThird}
        };
    }

    @DataProvider
    public static Object[][] incorrectValuesNumberFormat() {
        String[] notValidValuesFirst = {"type=GameConsole", "name=Xbox360", "price=45.z0", "size=25.0", "isInternetAvailable=+"};
        String[] notValidValuesSecond = {"type=GameConsole", "name=Dendi", "price=O", "size=10.0", "isInternetAvailable=-"};
        String[] notValidValuesThird = {"type=GameConsole", "name=PlayStation4", "price=45.0", "size=15.0A", "isInternetAvailable=+"};

        return new Object[][]{
                {notValidValuesFirst},
                {notValidValuesSecond},
                {notValidValuesThird}
        };
    }

    @Test
    @UseDataProvider("validResults")
    public void shouldBuiltObjectBeCorrect(String[] values, GameConsole expectedToy) {
        GameConsole actualToy = GAME_CONSOLE_BUILDER.createToy(values);

        Assert.assertEquals(expectedToy, actualToy);
    }

    @Test
    @UseDataProvider("notValidResults")
    public void shouldBuiltObjectBeNotSimilar(String[] values, GameConsole expectedToy) {
        GameConsole actualToy = GAME_CONSOLE_BUILDER.createToy(values);

        Assert.assertNotEquals(expectedToy, actualToy);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("incorrectValuesIllegalArgument")
    public void shouldIncorrectValuesCauseIllegalArgumentException(String[] values) {
        GAME_CONSOLE_BUILDER.createToy(values);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    @UseDataProvider("incorrectValuesArrayIndexOutOfBound")
    public void shouldIncorrectValuesCauseArrayIndexOutOfBoundsException(String[] values) {
        GAME_CONSOLE_BUILDER.createToy(values);
    }

    @Test(expected = NumberFormatException.class)
    @UseDataProvider("incorrectValuesNumberFormat")
    public void shouldIncorrectValuesCauseIncorrectValuesNumberFormatException(String[] values) {
        GAME_CONSOLE_BUILDER.createToy(values);
    }

}
