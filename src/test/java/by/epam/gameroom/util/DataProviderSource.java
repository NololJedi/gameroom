package by.epam.gameroom.util;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class DataProviderSource {

    @DataProvider
    public static Object[][] incorrectInputParameter() {
        String nullParameter = null;
        String emptyParameter = "";

        return new Object[][]{
                {emptyParameter},
                {nullParameter},

        };
    }

}
