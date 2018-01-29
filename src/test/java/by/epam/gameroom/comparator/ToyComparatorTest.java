package by.epam.gameroom.comparator;

import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.entities.toys.educational.RubikCube;
import by.epam.gameroom.entities.toys.electronic.GameConsole;
import by.epam.gameroom.entities.toys.electronic.GameConsoleType;
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

    private static ToyComparator toyComparatorNAME;
    private static ToyComparator toyComparatorPRICE;
    private static ToyComparator toyComparatorSIZE;
    private static ToyComparator toyComparatorAGE;
    private static ToyComparator toyComparatorTYPE;

    @BeforeClass
    public static void setToyComparator() {
        toyComparatorNAME = new ToyComparator(ComparatorParameter.NAME);
        toyComparatorPRICE = new ToyComparator(ComparatorParameter.PRICE);
        toyComparatorSIZE = new ToyComparator(ComparatorParameter.SIZE);
        toyComparatorAGE = new ToyComparator(ComparatorParameter.AGE_LIMIT);
        toyComparatorTYPE = new ToyComparator(ComparatorParameter.TYPE);
    }

    @DataProvider
    public static Object[][] validResults() {
        RubikCube firstToy = new RubikCube(15.0, false);
        RubikCube secondToy = new RubikCube(20.0, true);
        RubikCube thirdToy = new RubikCube(20.0, false);
        int sortingResult = 0;

        return new Object[][]{
                {toyComparatorNAME, firstToy, secondToy, sortingResult},
                {toyComparatorPRICE, secondToy, thirdToy, sortingResult},
                {toyComparatorSIZE, secondToy, firstToy, sortingResult},
                {toyComparatorAGE, firstToy, secondToy, sortingResult},
                {toyComparatorTYPE, firstToy, thirdToy, sortingResult}

        };
    }

    @DataProvider
    public static Object[][] notValidResults() {
        RubikCube firstRubikCube = new RubikCube(15.0, false);
        RubikCube secondRubikCube = new RubikCube(20.0, true);
        JumpRope jumpRope = new JumpRope(2.0, 10);
        GameConsole gameConsole = new GameConsole("XBOX360", 20.0, 4.0, true);
        int validResult = 0;
        int nameComparingResult = toyComparatorNAME.compare(jumpRope, firstRubikCube);
        int priceComparingResult = toyComparatorPRICE.compare(secondRubikCube, jumpRope);
        int sizeComparingResult = toyComparatorSIZE.compare(secondRubikCube, jumpRope);
        int ageComparingResult = toyComparatorAGE.compare(gameConsole, secondRubikCube);
        int typeComparingResult = toyComparatorTYPE.compare(firstRubikCube, jumpRope);

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
