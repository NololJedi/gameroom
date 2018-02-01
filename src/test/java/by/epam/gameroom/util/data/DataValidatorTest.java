package by.epam.gameroom.util.data;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static by.epam.gameroom.util.data.DataValidator.*;

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

        boolean expectedResult = true;

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
    public static Object[][] validValues() {
        String[] jumpRope = {"type=JumpRope", "price=22.0", "numLength=4"};
        String[] lego = {"type=Lego", "name=Pirates_set", "price=22.0", "size=4.5", "numCountOfToyMen=25", "numCountOfPeaces=200"};
        String[] rubikCube = {"type=RubikCube", "price=22.0", "isAssembled=+"};
        String[] gameConsole = {"type=GameConsole", "name=Xbox360", "price=200.0", "size=50", "isInternetAvailable=+"};
        String[] radioCar = {"type=RadioCar", "name=Police_car", "price=10.0", "size=4.5", "numCountOfBatteries=6", "numMaxSpeed=200"};
        String[] basketBallSet = {"type=BasketBallSet", "name=Ghetto_Wars", "price=45.0", "numBallDiameter=25", "numBasketHeight=200"};

        boolean expectedResult = true;

        return new Object[][]{
                {JUMPROPE_TYPE, jumpRope, expectedResult},
                {LEGO_TYPE, lego, expectedResult},
                {RUBIKCUBE_TYPE, rubikCube, expectedResult},
                {GAMECONSOLE_TYPE, gameConsole, expectedResult},
                {RADIOCAR_TYPE, radioCar, expectedResult},
                {BASKETBALLSET_TYPE, basketBallSet, expectedResult}
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
    public static Object[][] notValidValues() {
        String[] jumpRope = {"type=JumpRop", "price=22.0", "numLength=4"};
        String[] lego = {"type=Lego", "namePirates_set", "price=22.0", "size=4.5", "numCountOfToyMen=25", "numCountOfPeaces=200"};
        String[] rubikCube = {"type=RubikCube", "price=2z2.0", "isAssembled=+"};
        String[] gameConsole = {"type=GameConsole", "name=Xbox360", "price=200.0", "size=50", "InternetAvailable=+"};
        String[] radioCar = {"type=RadioCar", "name=Police_car", "price=10.0", "numCountOfBatteries=6", "numMaxSpeed=200"};
        String[] basketBallSet = {"type=BasketBallSet", "name=Ghett!o_Wars", "price=45.0", "numBallDiameter=25", "numBasketHeight=200"};

        boolean expectedResult = false;

        return new Object[][]{
                {JUMPROPE_TYPE, jumpRope, expectedResult},
                {LEGO_TYPE, lego, expectedResult},
                {RUBIKCUBE_TYPE, rubikCube, expectedResult},
                {GAMECONSOLE_TYPE, gameConsole, expectedResult},
                {RADIOCAR_TYPE, radioCar, expectedResult},
                {BASKETBALLSET_TYPE, basketBallSet, expectedResult}
        };
    }

    @DataProvider
    public static Object[][] emptyValue() {
        String type = null;
        String name = "";

        return new Object[][]{
                {name},
                {type},

        };
    }

    @DataProvider
    public static Object[][] emptyValues() {
        String type = "type";
        String typeNull = null;
        String typeEmpty = "";
        String[] valuesNull = null;
        String[] valuesEmpty = {};

        return new Object[][]{
                {typeNull, valuesEmpty},
                {typeEmpty, valuesEmpty},
                {type, valuesEmpty},
                {type, valuesNull}

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

    @Test
    @UseDataProvider("validValues")
    public void shouldResultOfValidationValuesBeSuccessful(String type, String[] values, boolean expectedResult) {
        boolean actualResult = dataValidator.checkValues(type, values);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    @UseDataProvider("notValidValues")
    public void shouldResultOfValidationValuesBeNotSuccessful(String type, String[] values, boolean expectedResult) {
        boolean actualResult = dataValidator.checkValues(type, values);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("emptyValue")
    public void shouldIncorrectValueCauseException(String value) {
        dataValidator.checkValue(value);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("emptyValues")
    public void shouldIncorrectValuesCauseException(String type, String[] values) {
        dataValidator.checkValues(type, values);
    }
}
