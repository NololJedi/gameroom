package by.epam.gameroom.util.data;

import by.epam.gameroom.util.DataProviderSource;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static by.epam.gameroom.entities.toys.educational.Lego.LEGO_VALID_VALUES_COUNT;
import static by.epam.gameroom.entities.toys.educational.RubikCube.RUBIKCUBE_VALID_VALUES_COUNT;
import static by.epam.gameroom.entities.toys.electronic.GameConsole.GAMECONSOLE_VALID_VALUES_COUNT;
import static by.epam.gameroom.entities.toys.electronic.RadioCar.RADIOCAR__VALID_VALUES_COUNT;
import static by.epam.gameroom.entities.toys.sport.BasketballSet.BASKETBALLSET__VALID_VALUES_COUNT;
import static by.epam.gameroom.entities.toys.sport.JumpRope.JUMPROPE_VALID_VALUES_COUNT;

@RunWith(DataProviderRunner.class)
public class DataValidatorTest {

    private static DataValidator dataValidator;

    @BeforeClass
    public static void setDataValidator() {
        dataValidator = new DataValidator();
    }

    @DataProvider
    public static Object[][] validValue() {
        String type = "type=JumpRope";
        String name = "name=Police_car";
        String price = "price=22.0";
        String size = "size=4.0";
        String isAssembled = "isAssembled=+";
        String numMaxSpeed = "numMaxSpeed=50";

        return new Object[][]{
                {name, true},
                {type, true},
                {price, true},
                {size, true},
                {numMaxSpeed, true},
                {isAssembled, true},
        };
    }

    @DataProvider
    public static Object[][] validCount() {
        String[] jumpRope = {"type=JumpRope", "price=22.0", "numLength=4"};
        String[] lego = {"type=Lego", "name=Pirates_set", "price=22.0", "size=4.5", "numCountOfToyMen=25", "numCountOfPeaces=200"};
        String[] rubikCube = {"type=RubikCube", "price=22.0", "isAssembled=+"};
        String[] gameConsole = {"type=GameConsole", "name=Xbox360", "price=200.0", "size=50", "isInternetAvailable=+"};
        String[] radioCar = {"type=RadioCar", "name=Police_car", "price=10.0", "size=4.5", "numCountOfBatteries=6", "numMaxSpeed=200"};
        String[] basketBallSet = {"type=BasketBallSet", "name=Ghetto_Wars", "price=45.0", "numBallDiameter=25", "numBasketHeight=200"};

        boolean expectedResult = true;

        return new Object[][]{
                {jumpRope, JUMPROPE_VALID_VALUES_COUNT, expectedResult},
                {lego, LEGO_VALID_VALUES_COUNT, expectedResult},
                {rubikCube, RUBIKCUBE_VALID_VALUES_COUNT, expectedResult},
                {gameConsole, GAMECONSOLE_VALID_VALUES_COUNT, expectedResult},
                {radioCar, RADIOCAR__VALID_VALUES_COUNT, expectedResult},
                {basketBallSet, BASKETBALLSET__VALID_VALUES_COUNT, expectedResult}
        };
    }

    @DataProvider
    public static Object[][] notValidValue() {
        String type = "typ=JumpRope";
        String name = "name=Police car";
        String price = "price=zz";
        String size = "size=";
        String isAssembled = "isAssembled=yes";
        String numMaxSpeed = "numMaxSpeed50";

        boolean expectedResult = false;

        return new Object[][]{
                {name, expectedResult},
                {type, expectedResult},
                {price, expectedResult},
                {size, expectedResult},
                {numMaxSpeed, expectedResult},
                {isAssembled, expectedResult},
        };
    }

    @DataProvider
    public static Object[][] notValidCount() {
        String[] jumpRope = {"type=JumpRope", "price=22.0"};
        String[] lego = {"type=Lego", "price=22.0", "size=4.5", "numCountOfToyMen=25", "numCountOfPeaces=200"};
        String[] rubikCube = {"price=22.0", "isAssembled=+"};
        String[] gameConsole = {"type=GameConsole", "name=Xbox360", "price=200.0", "size=50"};
        String[] radioCar = {"type=RadioCar", "price=10.0", "size=4.5", "numCountOfBatteries=6", "numMaxSpeed=200"};
        String[] basketBallSet = {"type=BasketBallSet", "price=45.0", "numBallDiameter=25", "numBasketHeight=200"};

        boolean expectedResult = false;

        return new Object[][]{
                {jumpRope, JUMPROPE_VALID_VALUES_COUNT, expectedResult},
                {lego, LEGO_VALID_VALUES_COUNT, expectedResult},
                {rubikCube, RUBIKCUBE_VALID_VALUES_COUNT, expectedResult},
                {gameConsole, GAMECONSOLE_VALID_VALUES_COUNT, expectedResult},
                {radioCar, RADIOCAR__VALID_VALUES_COUNT, expectedResult},
                {basketBallSet, BASKETBALLSET__VALID_VALUES_COUNT, expectedResult}
        };
    }

    @DataProvider
    public static Object[][] emptyValues() {
        String[] valid = {"type=JumpRope", "price=22.0", "numLength=4"};
        String[] valuesNull = null;
        String[] valuesEmpty = {};
        int incorrectCount = 0;

        return new Object[][]{
                {valuesEmpty, JUMPROPE_VALID_VALUES_COUNT},
                {valuesNull, JUMPROPE_VALID_VALUES_COUNT},
                {valid, incorrectCount}

        };
    }


    @Test
    @UseDataProvider("validValue")
    public void shouldResultOfValidationValueBeSuccessful(String value, boolean expectedResult) {
        boolean actualResult = dataValidator.checkValue(value);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    @UseDataProvider("notValidValue")
    public void shouldResultOfValidationValueBeNotSuccessful(String value, boolean expectedResult) {
        boolean actualResult = dataValidator.checkValue(value);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider(value = "incorrectInputParameter", location = DataProviderSource.class)
    public void shouldIncorrectValueCauseException(String value) {
        dataValidator.checkValue(value);
    }

    @Test
    @UseDataProvider("validCount")
    public void shouldResultOfCountValidationBeSuccessful(String[] parsedValues, int constantsCount, boolean expectedResult) {
        boolean actualResult = dataValidator.checkValuesCount(parsedValues, constantsCount);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    @UseDataProvider("notValidCount")
    public void shouldResultOfCountValidationBeNotSuccessful(String[] parsedValues, int constantsCount, boolean expectedResult) {
        boolean actualResult = dataValidator.checkValuesCount(parsedValues, constantsCount);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("emptyValues")
    public void shouldIncorrectCountCauseException(String[] parsedValues, int count) {
        dataValidator.checkValuesCount(parsedValues, count);
    }

}
