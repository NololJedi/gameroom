package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.educational.Lego;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class LegoBuilderTest {

    private final static LegoBuilder LEGO_BUILDER = new LegoBuilder();

    @DataProvider
    public static Object[][] validResults() {
        String[] validValuesFirst = {"type=Lego", "name=Pirates", "price=45.0", "size=25.0",
                "numCountOfToyMen=20", "numCountOfPeaces=2000"};
        String[] validValuesSecond = {"type=Lego", "name=Space_Wars", "price=55.0", "size=200.0",
                "numCountOfToyMen=200", "numCountOfPeaces=20000"};
        String[] validValuesThird = {"type=Lego", "name=Home", "price=5.0", "size=10.0",
                "numCountOfToyMen=5", "numCountOfPeaces=200"};

        Lego first = new Lego("Pirates", 45.0, 25.0, 20, 2000);
        Lego second = new Lego("Space_Wars", 55.0, 200.0, 200, 20000);
        Lego third = new Lego("Home", 5.0, 10.0, 5, 200);

        return new Object[][]{
                {validValuesFirst, first},
                {validValuesSecond, second},
                {validValuesThird, third}
        };
    }

    @DataProvider
    public static Object[][] notValidResults() {
        String[] notValidValuesFirst = {"type=Lego", "name=Pirates", "price=45.0", "size=25.0",
                "numCountOfToyMen=20", "numCountOfPeaces=2000"};
        String[] notValidValuesSecond = {"type=Lego", "name=Space_Wars", "price=55.0", "size=200.0",
                "numCountOfToyMen=200", "numCountOfPeaces=20000"};
        String[] notValidValuesThird = {"type=Lego", "name=Home", "price=5.0", "size=10.0",
                "numCountOfToyMen=5", "numCountOfPeaces=200"};

        Lego first = new Lego("Super_Heroes", 45.0, 25.0, 2, 200);
        Lego second = new Lego("Space_Wars", 55.0, 200.0, 20, 200);
        Lego third = new Lego("Home", 25.0, 10.0, 5, 20220);

        return new Object[][]{
                {notValidValuesFirst, first},
                {notValidValuesSecond, second},
                {notValidValuesThird, third}
        };
    }

    @DataProvider
    public static Object[][] incorrectValuesIllegalArgument() {
        String[] notValidValuesFirst = {"type=Lego", "size=25.0",
                "numCountOfToyMen=20", "numCountOfPeaces2000"};
        String[] notValidValuesSecond = {"type=Lego", "price=55.0", "size=200.0",
                "numCountOfToyMen=200", "numCountOfPeaces=20000"};
        String[] notValidValuesThird = {"type=Lego", "", "price=5.0", "size=10.0",
                "numCountOfToyMen=5", "numCountOfPeaces=200"};
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
        String[] notValidValuesFirst = {"type=Lego", "name", "price=45.0", "size=25.0",
                "numCountOfToyMen=20", ""};
        String[] notValidValuesSecond = {"type=Lego", "name=Space_Wars", "price=55.0", "size=200.0",
                "numCountOfToyMen=200", "20000"};
        String[] notValidValuesThird = {"type=Lego", "name=", "price=5.0", "size=10.0",
                "numCountOfToyMen=5", "numCountOfPeaces=200"};

        return new Object[][]{
                {notValidValuesFirst},
                {notValidValuesSecond},
                {notValidValuesThird}
        };
    }

    @DataProvider
    public static Object[][] incorrectValuesNumberFormat() {
        String[] notValidValuesFirst = {"type=Lego", "name=Pirates", "price=45.0", "size=25.s0",
                "numCountOfToyMen=20", "numCountOfPeaces=2000"};
        String[] notValidValuesSecond = {"type=Lego", "name=Space_Wars", "price=55.0", "size=2as",
                "numCountOfToyMen=200", "numCountOfPeaces=20000"};
        String[] notValidValuesThird = {"type=Lego", "name=Home", "price=5.0", "size=10.0",
                "numCountOfToyMen=5", "numCountOfPeaces=20ds0"};

        return new Object[][]{
                {notValidValuesFirst},
                {notValidValuesSecond},
                {notValidValuesThird}
        };
    }

    @Test
    @UseDataProvider("validResults")
    public void shouldBuiltObjectBeCorrect(String[] values, Lego expectedToy) {
        Lego actualToy = LEGO_BUILDER.createToy(values);

        Assert.assertEquals(expectedToy, actualToy);
    }

    @Test
    @UseDataProvider("notValidResults")
    public void shouldBuiltObjectBeNotSimilar(String[] values, Lego expectedToy) {
        Lego actualToy = LEGO_BUILDER.createToy(values);

        Assert.assertNotEquals(expectedToy, actualToy);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("incorrectValuesIllegalArgument")
    public void shouldIncorrectValuesCauseIllegalArgumentException(String[] values) {
        LEGO_BUILDER.createToy(values);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    @UseDataProvider("incorrectValuesArrayIndexOutOfBound")
    public void shouldIncorrectValuesCauseArrayIndexOutOfBoundsException(String[] values) {
        LEGO_BUILDER.createToy(values);
    }

    @Test(expected = NumberFormatException.class)
    @UseDataProvider("incorrectValuesNumberFormat")
    public void shouldIncorrectValuesCauseIncorrectValuesNumberFormatException(String[] values) {
        LEGO_BUILDER.createToy(values);
    }

}
