package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.sport.BasketballSet;
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
    public static Object[][] incorrectValuesIllegalArgument() {
        String[] notValidValuesFirst = {"type=BasketBallSet", "name=Ghetto_Wars", "numBallDiameter=25", "numBasketHeight=200"};
        String[] nullArray = null;
        String[] emptyArray = {};

        return new Object[][]{
                {notValidValuesFirst},
                {nullArray},
                {emptyArray}
        };
    }

    @DataProvider
    public static Object[][] incorrectValuesArrayIndexOutOfBound() {
        String[] notValidValuesFirst = {"type=BasketBallSet", "name=Ghetto_Wars", "price=45.0", "25", "numBasketHeight=200"};
        String[] notValidValuesSecond = {"type=BasketBallSet", "name=", "price=60.0", "numBallDiameter=10", "numBasketHeight=220"};
        String[] notValidValuesThird = {"type=BasketBallSet", "name=Little_girls_set", "price", "numBallDiameter=15", "numBasketHeight=100"};

        return new Object[][]{
                {notValidValuesFirst},
                {notValidValuesSecond},
                {notValidValuesThird}
        };
    }

    @DataProvider
    public static Object[][] incorrectValuesNumberFormat() {
        String[] notValidValuesFirst = {"type=BasketBallSet", "name=Ghetto_Wars", "price=ss", "numBallDiameter=25", "numBasketHeight=200"};
        String[] notValidValuesSecond = {"type=BasketBallSet", "name=Bad_boy", "price=60.0", "numBallDiameter=as", "numBasketHeight=220"};
        String[] notValidValuesThird = {"type=BasketBallSet", "name=Little_girls_set", "price=45.0", "numBallDiameter=15", "numBasketHeight=1e2"};

        return new Object[][]{
                {notValidValuesFirst},
                {notValidValuesSecond},
                {notValidValuesThird}
        };
    }

    @Test
    @UseDataProvider("validResults")
    public void shouldBuiltObjectBeCorrect(String[] values, BasketballSet expectedToy) {
        BasketballSet actualToy = BASKET_BALL_SET_BUILDER.createToy(values);

        Assert.assertEquals(expectedToy, actualToy);
    }

    @Test
    @UseDataProvider("notValidResults")
    public void shouldBuiltObjectBeNotSimilar(String[] values, BasketballSet expectedToy) {
        BasketballSet actualToy = BASKET_BALL_SET_BUILDER.createToy(values);

        Assert.assertNotEquals(expectedToy, actualToy);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("incorrectValuesIllegalArgument")
    public void shouldIncorrectValuesCauseIllegalArgumentException(String[] values) {
        BASKET_BALL_SET_BUILDER.createToy(values);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    @UseDataProvider("incorrectValuesArrayIndexOutOfBound")
    public void shouldIncorrectValuesCauseArrayIndexOutOfBoundsException(String[] values) {
        BASKET_BALL_SET_BUILDER.createToy(values);
    }

    @Test(expected = NumberFormatException.class)
    @UseDataProvider("incorrectValuesNumberFormat")
    public void shouldIncorrectValuesCauseIncorrectValuesNumberFormatException(String[] values) {
        BASKET_BALL_SET_BUILDER.createToy(values);
    }

}
