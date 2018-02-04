package by.epam.gameroom.util.builders;

import by.epam.gameroom.exceptions.IncorrectValueException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class ToyParametersProviderTest {

    private final static ToyParametersProvider TOY_PARAMETERS_PROVIDER = new ToyParametersProvider();
    private final static int STRING_VALUE_INDEX = 0;
    private final static int DOUBLE_VALUE_INDEX = 1;
    private final static int INTEGER_VALUE_INDEX = 2;
    private final static int BOOLEAN_VALUE_INDEX = 3;

    private static String[] correctParameters;
    private static String[] inCorrectParameters;

    @BeforeClass
    public static void setIncorrectParameters() {
        String stringParameter = "name=Police car";
        String doubleParameter = "price=-22.0";
        String integerParameter = "numMaxSpeed=50z";
        String booleanParameter = "isAssembled=!";

        inCorrectParameters = new String[4];
        inCorrectParameters[0] = stringParameter;
        inCorrectParameters[1] = doubleParameter;
        inCorrectParameters[2] = integerParameter;
        inCorrectParameters[3] = booleanParameter;
    }

    @BeforeClass
    public static void setCorrectParameters() {
        String stringParameter = "name=Police_car";
        String doubleParameter = "price=22.0";
        String integerParameter = "numMaxSpeed=50";
        String booleanParameter = "isAssembled=+";

        correctParameters = new String[4];
        correctParameters[0] = stringParameter;
        correctParameters[1] = doubleParameter;
        correctParameters[2] = integerParameter;
        correctParameters[3] = booleanParameter;
    }

    @DataProvider
    public static Object[][] illegalArgumentParameter() {
        String[] nullArray = null;
        String[] emptyArray = {};
        int incorrectIndex = -1;

        return new Object[][]{
                {nullArray, STRING_VALUE_INDEX},
                {emptyArray, INTEGER_VALUE_INDEX},
                {correctParameters, incorrectIndex}
        };
    }

    @Test
    public void shouldStringParametersBeProvidedSuccessful() throws IncorrectValueException {
        String correctValue = "Police_car";
        String actualValue = TOY_PARAMETERS_PROVIDER.getStringParameter(correctParameters, STRING_VALUE_INDEX);

        Assert.assertEquals(correctValue, actualValue);
    }

    @Test(expected = IncorrectValueException.class)
    public void shouldStringParametersCauseException() throws IncorrectValueException {
        TOY_PARAMETERS_PROVIDER.getStringParameter(inCorrectParameters, STRING_VALUE_INDEX);
    }

    @Test
    public void shouldDoubleParametersBeProvidedSuccessful() throws IncorrectValueException {
        double correctValue = 22.0;
        double actualValue = TOY_PARAMETERS_PROVIDER.getDoubleParameter(correctParameters, DOUBLE_VALUE_INDEX);

        Assert.assertEquals(correctValue, actualValue, 0.01);
    }

    @Test(expected = IncorrectValueException.class)
    public void shouldDoubleParametersCauseException() throws IncorrectValueException {
        TOY_PARAMETERS_PROVIDER.getDoubleParameter(inCorrectParameters, DOUBLE_VALUE_INDEX);
    }

    @Test
    public void shouldIntegerParametersBeProvidedSuccessful() throws IncorrectValueException {
        int correctValue = 50;
        int actualValue = TOY_PARAMETERS_PROVIDER.getIntegerParameter(correctParameters, INTEGER_VALUE_INDEX);

        Assert.assertEquals(correctValue, actualValue);
    }

    @Test(expected = IncorrectValueException.class)
    public void shouldIntegerParametersCauseException() throws IncorrectValueException {
        TOY_PARAMETERS_PROVIDER.getIntegerParameter(inCorrectParameters, INTEGER_VALUE_INDEX);
    }

    @Test
    public void shouldBooleanParametersBeProvidedSuccessful() throws IncorrectValueException {
        boolean correctValue = true;
        boolean actualValue = TOY_PARAMETERS_PROVIDER.getBooleanParameter(correctParameters, BOOLEAN_VALUE_INDEX);

        Assert.assertEquals(correctValue, actualValue);
    }

    @Test(expected = IncorrectValueException.class)
    public void shouldBooleanParametersCauseException() throws IncorrectValueException {
        TOY_PARAMETERS_PROVIDER.getBooleanParameter(inCorrectParameters, BOOLEAN_VALUE_INDEX);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("illegalArgumentParameter")
    public void shouldIllegalParametersCauseException(String[] parameters, int index) throws IncorrectValueException {
        TOY_PARAMETERS_PROVIDER.getDoubleParameter(parameters, index);
    }

}
