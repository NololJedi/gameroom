package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.sport.BasketballSet;
import by.epam.gameroom.exceptions.IncorrectValueException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class BasketBallSetBuilderTest {

    private final static BasketBallSetBuilder BASKET_BALL_SET_BUILDER = new BasketBallSetBuilder();

    @DataProvider
    public static Object[][] validResults() {
        String[] validValuesFirst = {"type=BasketBallSet", "name=Ghetto_Wars", "price=45.0",
                "numBallDiameter=25", "numBasketHeight=200"};
        String[] validValuesSecond = {"type=BasketBallSet", "name=Bad_boy", "price=60.0",
                "numBallDiameter=10", "numBasketHeight=220"};
        String[] validValuesThird = {"type=BasketBallSet", "name=Little_girls_set", "price=45.0",
                "numBallDiameter=15", "numBasketHeight=100"};

        BasketballSet firstSet = new BasketballSet("Ghetto_Wars", 45.0, 25, 200);
        BasketballSet secondSet = new BasketballSet("Bad_boy", 60.0, 10, 220);
        BasketballSet thirdSet = new BasketballSet("Little_girls_set", 45.0, 15, 100);

        return new Object[][]{
                {validValuesFirst, firstSet},
                {validValuesSecond, secondSet},
                {validValuesThird, thirdSet}
        };


    }

    @DataProvider
    public static Object[][] notValidResults() {
        String[] notValidValuesFirst = {"type=BasketBallSet", "name=Ghetto_Wars", "price=45.0",
                "numBallDiameter=25", "numBasketHeight=200"};
        String[] notValidValuesSecond = {"type=BasketBallSet", "name=Bad_boy", "price=60.0",
                "numBallDiameter=10", "numBasketHeight=220"};
        String[] notValidValuesThird = {"type=BasketBallSet", "name=Little_girls_set", "price=45.0",
                "numBallDiameter=15", "numBasketHeight=100"};

        BasketballSet firstSet = new BasketballSet("Shakil_Onill", 100.0, 25, 200);
        BasketballSet secondSet = new BasketballSet("Fire_ball", 610.0, 25, 220);
        BasketballSet thirdSet = new BasketballSet("Hells_basket", 25.0, 15, 120);

        return new Object[][]{
                {notValidValuesFirst, firstSet},
                {notValidValuesSecond, secondSet},
                {notValidValuesThird, thirdSet}
        };
    }

    @DataProvider
    public static Object[][] exceptionResults() {
        String[] notValidParametersFirst = {"type=BasketBallSet", "name=Ghetto Wars", "price=45.0",
                "numBallDiameter=25", "numBasketHeight=200"};
        String[] notValidParametersSecond = {"type=BasketBallSet", "name=Bad_boy", "price=60x.0",
                "numBallDiameter=10", "numBasketHeight=220"};
        String[] notValidParametersThird = {"type=BasketBallSet", "name=Little_girls_set", "price=45.0",
                "numBallDiameter=15Aas", "numBasketHeight=100"};
        String[] notValidParametersFourth = {"type=BasketBallSet", "name=Little_girls_set", "price=45.0",
                "numBallDiameter=15Aas", "numBasketHeight=1,00"};

        return new Object[][]{
                {notValidParametersFirst},
                {notValidParametersSecond},
                {notValidParametersThird},
                {notValidParametersFourth}
        };
    }

    @Test
    @UseDataProvider("validResults")
    public void shouldBuiltObjectBeCorrect(String[] values, BasketballSet expectedToy) throws IncorrectValueException {
        BasketballSet actualToy = BASKET_BALL_SET_BUILDER.createToy(values);

        Assert.assertEquals(expectedToy, actualToy);
    }

    @Test
    @UseDataProvider("notValidResults")
    public void shouldBuiltObjectBeNotSimilar(String[] values, BasketballSet expectedToy) throws IncorrectValueException {
        BasketballSet actualToy = BASKET_BALL_SET_BUILDER.createToy(values);

        Assert.assertNotEquals(expectedToy, actualToy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldIncorrectParametersCauseIllegalArgumentException() throws IncorrectValueException {
        String[] nullArray = null;

        BASKET_BALL_SET_BUILDER.createToy(nullArray);
    }

    @Test(expected = IncorrectValueException.class)
    @UseDataProvider("exceptionResults")
    public void shouldIncorrectParametersCauseIncorrectArgumentException(String[] parameters) throws IncorrectValueException {
        BASKET_BALL_SET_BUILDER.createToy(parameters);
    }

}
