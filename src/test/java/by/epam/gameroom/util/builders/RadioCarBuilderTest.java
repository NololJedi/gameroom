package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.electronic.RadioCar;
import by.epam.gameroom.exceptions.IncorrectValueException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class RadioCarBuilderTest {

    private final static RadioCarBuilder RADIO_CAR_BUILDER = new RadioCarBuilder();

    @DataProvider
    public static Object[][] validResults() {
        String[] validValuesFirst = {"type=RadioCar", "name=Police_Car", "price=45.0", "size=25.0",
                "numCountOfBatteries=2", "numMaxSpeed=50"};
        String[] validValuesSecond = {"type=RadioCar", "name=Robocop_Car", "price=60.0", "size=10.5",
                "numCountOfBatteries=6", "numMaxSpeed=60"};
        String[] validValuesThird = {"type=RadioCar", "name=BMW", "price=45.0", "size=15.0",
                "numCountOfBatteries=10", "numMaxSpeed=100"};

        RadioCar first = new RadioCar("Police_Car", 45.0, 25.0, 2, 50);
        RadioCar second = new RadioCar("Robocop_Car", 60.0, 10.5, 6, 60);
        RadioCar third = new RadioCar("BMW", 45.0, 15.0, 10, 100);

        return new Object[][]{
                {validValuesFirst, first},
                {validValuesSecond, second},
                {validValuesThird, third}
        };
    }

    @DataProvider
    public static Object[][] notValidResults() {
        String[] notValidValuesFirst = {"type=RadioCar", "name=Police_Car", "price=45.0", "size=25.0",
                "numCountOfBatteries=2", "numMaxSpeed=50"};
        String[] notValidValuesSecond = {"type=RadioCar", "name=Robocop_Car", "price=60.0", "size=10.5",
                "numCountOfBatteries=6", "numMaxSpeed=60"};
        String[] notValidValuesThird = {"type=RadioCar", "name=BMW", "price=45.0", "size=15.0",
                "numCountOfBatteries=10", "numMaxSpeed=100"};

        RadioCar first = new RadioCar("Volvo", 45.0, 25.0, 2, 50);
        RadioCar second = new RadioCar("Robocop_Car", 60.0, 10.5, 4, 35);
        RadioCar third = new RadioCar("BMW_X5", 200.0, 15.0, 10, 100);

        return new Object[][]{
                {notValidValuesFirst, first},
                {notValidValuesSecond, second},
                {notValidValuesThird, third}
        };
    }

    @DataProvider
    public static Object[][] exceptionResults() {
        String[] notValidParametersFirst = {"type=RadioCar", "name=Police Car", "price=45.0", "size=25.0",
                "numCountOfBatteries=2", "numMaxSpeed=50"};
        String[] notValidParametersSecond = {"type=RadioCar", "name=Police_Car", "price=-45.0", "size=25.0",
                "numCountOfBatteries=2", "numMaxSpeed=50"};
        String[] notValidParametersThird = {"type=RadioCar", "name=Police_Car", "price=45.0", "size=-25.0",
                "numCountOfBatteries=2", "numMaxSpeed=50"};
        String[] notValidParametersFourth = {"type=RadioCar", "name=Police_Car", "price=45.0", "size=25.0",
                "numCountOfBatteries=2!", "numMaxSpeed=50"};
        String[] notValidParameterFive = {"type=RadioCar", "name=Police_Car", "price=45.0", "size=25.0",
                "numCountOfBatteries=2", "numMaxSpeed=50,00"};
        return new Object[][]{
                {notValidParametersFirst},
                {notValidParametersSecond},
                {notValidParametersThird},
                {notValidParametersFourth},
                {notValidParameterFive}
        };
    }

    @Test
    @UseDataProvider("validResults")
    public void shouldBuiltObjectBeCorrect(String[] values, RadioCar expectedToy) throws IncorrectValueException {
        RadioCar actualToy = RADIO_CAR_BUILDER.createToy(values);

        Assert.assertEquals(expectedToy, actualToy);
    }

    @Test
    @UseDataProvider("notValidResults")
    public void shouldBuiltObjectBeNotSimilar(String[] values, RadioCar expectedToy) throws IncorrectValueException {
        RadioCar actualToy = RADIO_CAR_BUILDER.createToy(values);

        Assert.assertNotEquals(expectedToy, actualToy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldIncorrectValueCauseIllegalArgumentException() throws IncorrectValueException {
        String[] nullArray = null;

        RADIO_CAR_BUILDER.createToy(nullArray);
    }

    @Test(expected = IncorrectValueException.class)
    @UseDataProvider("exceptionResults")
    public void shouldIncorrectParametersCauseIncorrectArgumentException(String[] parameters) throws IncorrectValueException {
        RADIO_CAR_BUILDER.createToy(parameters);
    }

}
