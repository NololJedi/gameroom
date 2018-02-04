package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.educational.Lego;
import by.epam.gameroom.exceptions.IncorrectValueException;
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
    public static Object[][] exceptionResults() {
        String[] notValidParametersFirst = {"type=Lego", "name=Pirates!", "price=45.0", "size=25.0",
                "numCountOfToyMen=20", "numCountOfPeaces=2000"};
        String[] notValidParametersSecond = {"type=Lego", "name=Pirates", "price=-45.0", "size=25.0",
                "numCountOfToyMen=20", "numCountOfPeaces=2000"};
        String[] notValidParametersThird = {"type=Lego", "name=Pirates", "price=45.0", "size=25..0",
                "numCountOfToyMen=20", "numCountOfPeaces=2000"};
        String[] notValidParametersFourth = {"type=Lego", "name=Pirates", "price=45.0", "size=25.0",
                "numCountOfToyMen=-20", "numCountOfPeaces=2000"};
        String[] notValidParametersFive = {"type=Lego", "name=Pirates", "price=45.0", "size=25.0",
                "numCountOfToyMen=-20", "numCountOfPeaces=2000!"};

        return new Object[][]{
                {notValidParametersFirst},
                {notValidParametersSecond},
                {notValidParametersThird},
                {notValidParametersFourth},
                {notValidParametersFive}
        };
    }

    @Test
    @UseDataProvider("validResults")
    public void shouldBuiltObjectBeCorrect(String[] values, Lego expectedToy) throws IncorrectValueException {
        Lego actualToy = LEGO_BUILDER.createToy(values);

        Assert.assertEquals(expectedToy, actualToy);
    }

    @Test
    @UseDataProvider("notValidResults")
    public void shouldBuiltObjectBeNotSimilar(String[] values, Lego expectedToy) throws IncorrectValueException {
        Lego actualToy = LEGO_BUILDER.createToy(values);

        Assert.assertNotEquals(expectedToy, actualToy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldIncorrectValueCauseIllegalArgumentException() throws IncorrectValueException {
        String[] nullArray = null;

        LEGO_BUILDER.createToy(nullArray);
    }

    @Test(expected = IncorrectValueException.class)
    @UseDataProvider("exceptionResults")
    public void shouldIncorrectParametersCauseIncorrectArgumentException(String[] parameters) throws IncorrectValueException {
        LEGO_BUILDER.createToy(parameters);
    }

}
