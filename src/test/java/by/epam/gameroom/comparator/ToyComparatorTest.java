package by.epam.gameroom.comparator;

import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.entities.toys.educational.RubikCube;
import by.epam.gameroom.entities.toys.electronic.GameConsole;
import by.epam.gameroom.entities.toys.sport.JumpRope;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class ToyComparatorTest {

    private static ToyComparator nameComparator;
    private static ToyComparator priceComparator;
    private static ToyComparator sizeComparator;
    private static ToyComparator ageLimitComparator;
    private static ToyComparator toyTypeComparator;

    @BeforeClass
    public static void setToyComparator() {
        nameComparator = new ToyComparator(ComparatorParameter.NAME);
        priceComparator = new ToyComparator(ComparatorParameter.PRICE);
        sizeComparator = new ToyComparator(ComparatorParameter.SIZE);
        ageLimitComparator = new ToyComparator(ComparatorParameter.AGE_LIMIT);
        toyTypeComparator = new ToyComparator(ComparatorParameter.TYPE);
    }

    @DataProvider
    public static Object[][] validResults() {
        RubikCube firstToy = new RubikCube(15.0, false);
        RubikCube secondToy = new RubikCube(20.0, true);
        RubikCube thirdToy = new RubikCube(20.0, false);
        int sortingResult = 0;

        return new Object[][]{
                {nameComparator, firstToy, secondToy, sortingResult},
                {priceComparator, secondToy, thirdToy, sortingResult},
                {sizeComparator, secondToy, firstToy, sortingResult},
                {ageLimitComparator, firstToy, secondToy, sortingResult},
                {toyTypeComparator, firstToy, thirdToy, sortingResult}

        };
    }

    @DataProvider
    public static Object[][] notValidResults() {
        RubikCube firstRubikCube = new RubikCube(15.0, false);
        RubikCube secondRubikCube = new RubikCube(20.0, true);
        JumpRope jumpRope = new JumpRope(2.0, 10);
        GameConsole gameConsole = new GameConsole("XBOX360", 20.0, 4.0, true);
        int validResult = 0;
        int nameComparingResult = nameComparator.compare(jumpRope, firstRubikCube);
        int priceComparingResult = priceComparator.compare(secondRubikCube, jumpRope);
        int sizeComparingResult = sizeComparator.compare(secondRubikCube, jumpRope);
        int ageComparingResult = ageLimitComparator.compare(gameConsole, secondRubikCube);
        int typeComparingResult = toyTypeComparator.compare(firstRubikCube, jumpRope);

        return new Object[][]{
                {nameComparingResult, validResult},
                {priceComparingResult, validResult},
                {sizeComparingResult, validResult},
                {ageComparingResult, validResult},
                {typeComparingResult, validResult}

        };
    }

    @Test
    @UseDataProvider("validResults")
    public void shouldComparableBeSuccessful(ToyComparator toyComparator, Toy first, Toy second, int sortingResult) {
        int currentResult = toyComparator.compare(first, second);

        Assert.assertEquals(sortingResult, currentResult);
    }

    @Test
    @UseDataProvider("notValidResults")
    public void shouldComparableBeNotSuccessful(int sortingResult, int validResult) {
        Assert.assertNotEquals(validResult, sortingResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldExceptionBeCaused() {
        ToyComparator toyComparator = new ToyComparator(null);
    }

}
