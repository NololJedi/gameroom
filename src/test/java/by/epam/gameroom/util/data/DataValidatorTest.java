package by.epam.gameroom.util.data;

import by.epam.gameroom.util.DataProviderSource;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(DataProviderRunner.class)
public class DataValidatorTest {

    private final static DataValidator dataValidator = new DataValidator();
    ;

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

    @Test
    @UseDataProvider("validValue")
    public void shouldResultOfValidationValueBeSuccessful(String value, boolean expectedResult) {
        boolean actualResult = dataValidator.checkParameter(value);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    @UseDataProvider("notValidValue")
    public void shouldResultOfValidationValueBeNotSuccessful(String value, boolean expectedResult) {
        boolean actualResult = dataValidator.checkParameter(value);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider(value = "incorrectInputParameter", location = DataProviderSource.class)
    public void shouldIncorrectValueCauseException(String value) {
        dataValidator.checkParameter(value);
    }

}
