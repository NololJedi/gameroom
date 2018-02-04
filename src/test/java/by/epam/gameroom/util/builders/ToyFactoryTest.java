package by.epam.gameroom.util.builders;

import by.epam.gameroom.util.DataProviderSource;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class ToyFactoryTest {

    private final static String LEGO_TYPE = "Lego";
    private final static String RUBIK_CUBE_TYPE = "RubikCube";
    private final static String GAME_CONSOLE_TYPE = "GameConsole";
    private final static String RADIO_CAR_TYPE = "RadioCar";
    private final static String BASKET_BALL_SET_TYPE = "BasketBallSet";
    private final static String JUMP_ROPE_TYPE = "JumpRope";
    private static ToyFactory toyFactory;

    @BeforeClass
    public static void setToyFactory() {
        toyFactory = new ToyFactory();
    }

    @DataProvider
    public static Object[][] validResults() {
        return new Object[][]{
                {JUMP_ROPE_TYPE, JumpRopeBuilder.class},
                {LEGO_TYPE, LegoBuilder.class},
                {GAME_CONSOLE_TYPE, GameConsoleBuilder.class},
                {RUBIK_CUBE_TYPE, RubikCubeBuilder.class},
                {RADIO_CAR_TYPE, RadioCarBuilder.class},
                {BASKET_BALL_SET_TYPE, BasketBallSetBuilder.class}
        };
    }

    @DataProvider
    public static Object[][] notValidResults() {
        return new Object[][]{
                {LEGO_TYPE, JumpRopeBuilder.class},
                {JUMP_ROPE_TYPE, LegoBuilder.class},
                {GAME_CONSOLE_TYPE, RubikCubeBuilder.class},
                {RUBIK_CUBE_TYPE, GameConsoleBuilder.class},
                {BASKET_BALL_SET_TYPE, RadioCarBuilder.class},
                {RADIO_CAR_TYPE, BasketBallSetBuilder.class}
        };
    }

    @Test
    @UseDataProvider("validResults")
    public void shouldBuildersGetBeSuccessful(String type, Class expectedClass) {
        ToyBuilder toyBuilder = toyFactory.getToyBuilder(type);
        Class actualClass = toyBuilder.getClass();

        Assert.assertEquals(expectedClass, actualClass);
    }

    @Test
    @UseDataProvider("notValidResults")
    public void shouldBuildersGetBeNotSuccessful(String type, Class expectedClass) {
        ToyBuilder toyBuilder = toyFactory.getToyBuilder(type);
        Class actualClass = toyBuilder.getClass();

        Assert.assertNotEquals(expectedClass, actualClass);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider(value = "incorrectInputParameter", location = DataProviderSource.class)
    public void shouldEmptyTypeCauseException(String type) {
        toyFactory.getToyBuilder(type);
    }

}
