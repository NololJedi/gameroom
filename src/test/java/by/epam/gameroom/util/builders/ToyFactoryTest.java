package by.epam.gameroom.util.builders;

import by.epam.gameroom.util.DataProviderSource;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static by.epam.gameroom.entities.toys.educational.Lego.LEGO_TYPE;
import static by.epam.gameroom.entities.toys.educational.RubikCube.RUBIKCUBE_TYPE;
import static by.epam.gameroom.entities.toys.electronic.GameConsole.GAMECONSOLE_TYPE;
import static by.epam.gameroom.entities.toys.electronic.RadioCar.RADIOCAR_TYPE;
import static by.epam.gameroom.entities.toys.sport.BasketballSet.BASKETBALLSET_TYPE;
import static by.epam.gameroom.entities.toys.sport.JumpRope.JUMPROPE_TYPE;

@RunWith(DataProviderRunner.class)
public class ToyFactoryTest {

    private static ToyFactory toyFactory;

    @BeforeClass
    public static void setToyFactory() {
        toyFactory = new ToyFactory();
    }

    @DataProvider
    public static Object[][] validResults() {
        return new Object[][]{
                {JUMPROPE_TYPE, JumpRopeBuilder.class},
                {LEGO_TYPE, LegoBuilder.class},
                {GAMECONSOLE_TYPE, GameConsoleBuilder.class},
                {RUBIKCUBE_TYPE, RubikCubeBuilder.class},
                {RADIOCAR_TYPE, RadioCarBuilder.class},
                {BASKETBALLSET_TYPE, BasketBallSetBuilder.class}
        };
    }

    @DataProvider
    public static Object[][] notValidResults() {
        return new Object[][]{
                {LEGO_TYPE, JumpRopeBuilder.class},
                {JUMPROPE_TYPE, LegoBuilder.class},
                {GAMECONSOLE_TYPE, RubikCubeBuilder.class},
                {RUBIKCUBE_TYPE, GameConsoleBuilder.class},
                {BASKETBALLSET_TYPE, RadioCarBuilder.class},
                {RADIOCAR_TYPE, BasketBallSetBuilder.class}
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
