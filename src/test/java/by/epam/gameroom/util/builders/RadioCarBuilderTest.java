package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.electronic.RadioCar;
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
    public static Object[][] incorrectValuesIllegalArgument() {
        String[] notValidValuesFirst = {"type=RadioCar", "name=Police_Car", "price==45.0", "size=25.0",
                "numCountOfBatteries=2", "numMaxSpeed=50"};
        String[] notValidValuesSecond = {"type=RadioCar", "size=10.5", "numCountOfBatteries=6", "numMaxSpeed=60"};
        String[] notValidValuesThird = {"type=RadioCar", "name=BMW", "price=45.0", "size=15.0",
                "numCountOfBatteries=10", "numMaxSpeed=100", "numCountOfPassengers=5"};
        String[] nullArray = null;
        String[] emptyArray = {};

        return new Object[][]{
                {notValidValuesFirst},
                {notValidValuesSecond},
                {notValidValuesThird},
                {nullArray},
                {emptyArray}
        };
    }

    @DataProvider
    public static Object[][] incorrectValuesArrayIndexOutOfBound() {
        String[] notValidValuesFirst = {"type=RadioCar", "nameBMW", "price=45.0", "size=25.0",
                "numCountOfBatteries=2", "numMaxSpeed=50"};
        String[] notValidValuesSecond = {"type=RadioCar", "Robocop_Car", "price=60.0", "size=10.5",
                "numCountOfBatteries=6", "numMaxSpeed=60"};
        String[] notValidValuesThird = {"type=RadioCar", "name=BMW", "price=45.0", "size=15.0",
                "numCountOfBatteries=10", "numMaxSpeed="};

        return new Object[][]{
                {notValidValuesFirst},
                {notValidValuesSecond},
                {notValidValuesThird}
        };
    }

    @DataProvider
    public static Object[][] incorrectValuesNumberFormat() {
        String[] notValidValuesFirst = {"type=RadioCar", "name=Police_Car", "price=45,0", "size=25.0",
                "numCountOfBatteries=2", "numMaxSpeed=50"};
        String[] notValidValuesSecond = {"type=RadioCar", "name=Robocop_Car", "price=60.0Z", "size=10.5",
                "numCountOfBatteries=6", "numMaxSpeed=60"};
        String[] notValidValuesThird = {"type=RadioCar", "name=BMW", "price=45.0", "size=15..0",
                "numCountOfBatteries=10s", "numMaxSpeed=10_0"};


        return new Object[][]{
                {notValidValuesFirst},
                {notValidValuesSecond},
                {notValidValuesThird}
        };
    }

    @Test
    @UseDataProvider("validResults")
    public void shouldBuiltObjectBeCorrect(String[] values, RadioCar expectedToy) {
        RadioCar actualToy = RADIO_CAR_BUILDER.createToy(values);

        Assert.assertEquals(expectedToy, actualToy);
    }

    @Test
    @UseDataProvider("notValidResults")
    public void shouldBuiltObjectBeNotSimilar(String[] values, RadioCar expectedToy) {
        RadioCar actualToy = RADIO_CAR_BUILDER.createToy(values);

        Assert.assertNotEquals(expectedToy, actualToy);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("incorrectValuesIllegalArgument")
    public void shouldIncorrectValuesCauseIllegalArgumentException(String[] values) {
        RADIO_CAR_BUILDER.createToy(values);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    @UseDataProvider("incorrectValuesArrayIndexOutOfBound")
    public void shouldIncorrectValuesCauseArrayIndexOutOfBoundsException(String[] values) {
        RADIO_CAR_BUILDER.createToy(values);
    }

    @Test(expected = NumberFormatException.class)
    @UseDataProvider("incorrectValuesNumberFormat")
    public void shouldIncorrectValuesCauseIncorrectValuesNumberFormatException(String[] values) {
        RADIO_CAR_BUILDER.createToy(values);
    }

}
