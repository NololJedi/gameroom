package by.epam.gameroom.util;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class LineParserTest {

    private static LineParser lineParser;

    @BeforeClass
    public static void setLineParser(){
        lineParser = new LineParser();
    }

    @DataProvider
    public static Object[][] validResults() {
        return new Object[][]{};
    }



}
