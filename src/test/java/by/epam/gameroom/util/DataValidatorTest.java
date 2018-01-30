package by.epam.gameroom.util;

import by.epam.gameroom.exceptions.IncorrectValueException;
import by.epam.gameroom.util.data.DataValidator;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class DataValidatorTest {

    private static DataValidator dataValidator;
    private static String jumpRopeValidLine;
    private static String rubikCubeValidLine;
    private static String legoValidLine;
    private static String gameConsoleValidLine;
    private static String radioCarValidLine;
    private static String basketBallSetValidLine;

    @BeforeClass
    public static void setDataValidator() {
        dataValidator = new DataValidator();
    }

    @BeforeClass
    public static void setValidTypes() {
        jumpRopeValidLine = "type=JumpRope";
        rubikCubeValidLine = "type=RubikCube";
        legoValidLine = "type=Lego";
        gameConsoleValidLine = "type=GameConsole";
        radioCarValidLine = "type=RadioCar";
        basketBallSetValidLine = "type=BasketBallSet";
    }

    @DataProvider
    public static Object[][] validTypes() {
        boolean expectedValidationResult = true;

        return new Object[][]{
                {jumpRopeValidLine, expectedValidationResult},
                {radioCarValidLine, expectedValidationResult},
                {legoValidLine, expectedValidationResult},
                {gameConsoleValidLine, expectedValidationResult},
                {rubikCubeValidLine, expectedValidationResult},
                {basketBallSetValidLine, expectedValidationResult}
        };
    }

    @DataProvider
    public static Object[][] validToyValues() {
        String jumpRopeValues = "type=JumpRope price=22.0 length=5";
        String rubikCubeValues = "type=RubikCube price=3.3 isAssembled=+";
        String radioCarValues = "type=RadioCar name=Radio_car price=250.0 size=30.0 countOfBatteries=6 maxSpeed=50";
        String gameConsoleValues = "type=GameConsole name=XBOX_360 price=45.5 size=50.0 isInternetAvailable=-";
        String legoValues = "type=Lego name=Pirates price=5.0 size=4.0 countOfToyMen=5 countOfPeaces=500";
        String basketBallSetValues = "type=BasketBallSet name=GhettoPlaying price=50.0 ballDiameter=20 basketHeight=2";

        boolean expectedValidationResult = true;

        return new Object[][]{
                {jumpRopeValidLine, jumpRopeValues, expectedValidationResult},
                {rubikCubeValidLine, rubikCubeValues, expectedValidationResult},
                {radioCarValidLine, radioCarValues, expectedValidationResult},
                {gameConsoleValidLine, gameConsoleValues, expectedValidationResult},
                {legoValidLine, legoValues, expectedValidationResult},
                {basketBallSetValidLine, basketBallSetValues, expectedValidationResult}
        };
    }

    @DataProvider
    public static Object[][] notValidTypes() {
        String jumpRopeNotValidLine = "type=JumpRope=";
        String rubikCubeNotValidLine = "type=Rubik Cube";
        String legoNotValidLine = "typ=Lego";
        String gameConsoleNotValidLine = "typeGameConsole";
        String radioCarNotValidLine = "=RadioCar";
        String basketBallSetNotValidLine = "type=Bas22k3e3t3B3a3llSet";

        return new Object[][]{
                {jumpRopeNotValidLine},
                {rubikCubeNotValidLine},
                {legoNotValidLine},
                {gameConsoleNotValidLine},
                {radioCarNotValidLine},
                {basketBallSetNotValidLine},
        };
    }

    @DataProvider
    public static Object[][] notValidToyValues() {
        String jumpRopeNotValidValues = "type=JumpRope price=sad length=5";
        String rubikCubeNotValidValues = "type=RubikCube  isAssembled=+";
        String radioCarNotValidValues = "type=RadioCar nameRadio_car price=250.0 size=30.0 countOfBatteries=6 maxSpeed=50";
        String gameConsoleNotValidValues = "type=GameConsole name=XBOX_360 price=45.5 size=50.0 isInternetAvailable=sad";
        String legoNotValidValues = "type=Lego name=Pirates price=5.0 another=200 size=4.0 countOfToyMen=5 countOfPeaces=";
        String basketBallSetNotValidValues = "type=BasketBallSet name=GhettoPlaying!!!! price=50.0 ballDiameter=20 basketHeight=2";

        return new Object[][]{
                {jumpRopeValidLine, jumpRopeNotValidValues},
                {rubikCubeValidLine, rubikCubeNotValidValues},
                {radioCarValidLine, radioCarNotValidValues},
                {gameConsoleValidLine, gameConsoleNotValidValues},
                {legoValidLine, legoNotValidValues},
                {basketBallSetValidLine, basketBallSetNotValidValues}
        };
    }

    @Test
    @UseDataProvider("validTypes")
    public void shouldTypeValidationBeSuccessful(String toyType, boolean expectedResult) throws IncorrectValueException {
        boolean actualResult = dataValidator.checkToyType(toyType);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(expected = IncorrectValueException.class)
    @UseDataProvider("notValidTypes")
    public void shouldTypeValidationBeNotSuccessful(String toyType) throws IncorrectValueException {
        dataValidator.checkToyType(toyType);
    }

    @Test
    @UseDataProvider("validToyValues")
    public void shouldToyValuesValidationBeSuccessful(String toyType, String line, boolean expectedResult) throws IncorrectValueException {
        LineParser lineParser = new LineParser();
        String typeValueIndex = "type=";
        String clearedIndex = "";
        String type = toyType.replaceAll(typeValueIndex, clearedIndex);
        String[] parsedLine = lineParser.parseLine(line, LineParser.DATA_PARSER_INDICATOR);
        boolean actualResult = dataValidator.checkToyValues(type, parsedLine);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(expected = IncorrectValueException.class)
    @UseDataProvider("notValidToyValues")
    public void shouldToyValuesValidationBeNotSuccessful(String toyType, String line) throws IncorrectValueException {
        LineParser lineParser = new LineParser();
        String typeValueIndex = "type=";
        String clearedIndex = "";
        String type = toyType.replaceAll(typeValueIndex, clearedIndex);
        String[] parsedLine = lineParser.parseLine(line, LineParser.DATA_PARSER_INDICATOR);

        dataValidator.checkToyValues(type, parsedLine);
    }



}
