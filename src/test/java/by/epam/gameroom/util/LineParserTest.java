package by.epam.gameroom.util;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class LineParserTest {

    @Test
    public void shouldDataParsingBeSuccessful() {
        String line = "type=JumpRope price=2.0 length=4";
        String[] expectedParsedData = {"type=JumpRope", "price=2.0", "length=4"};
        String[] actualParsedData = LineParser.parseLine(line, LineParser.DATA_PARSER_INDICATOR);

        Assert.assertArrayEquals(expectedParsedData, actualParsedData);
    }

    @Test
    public void shouldDataParsingBeNotSuccessful() {
        String line = "type=JumpRope price=2.0 length=4";
        String[] expectedParsedData = {"type=JumpRop price=2.0", "length=4"};
        String[] actualParsedData = LineParser.parseLine(line, LineParser.DATA_PARSER_INDICATOR);

        Assert.assertNotEquals(expectedParsedData, actualParsedData);
    }

    @Test
    public void shouldValueParsingBeSuccessful() {
        String value = "type=JumpRope";
        String[] expectedParsedValue = {"type", "JumpRope"};
        String[] actualParsedValue = LineParser.parseLine(value, LineParser.PARAMETER_PARSER_INDICATOR);

        Assert.assertArrayEquals(expectedParsedValue, actualParsedValue);
    }

    @Test
    public void shouldValueParsingBeNotSuccessful() {
        String value = "type=JumpRope";
        String[] expectedParsedValue = {"type=", "JumpRope"};
        String[] actualParsedValue = LineParser.parseLine(value, LineParser.PARAMETER_PARSER_INDICATOR);

        Assert.assertNotEquals(expectedParsedValue, actualParsedValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNullLineCauseException() {
        String nullLine = null;

        LineParser.parseLine(nullLine, LineParser.DATA_PARSER_INDICATOR);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider(value = "incorrectInputParameter", location = DataProviderSource.class)
    public void shouldIncorrectLineCauseException(String line) {
        LineParser.parseLine(line, LineParser.DATA_PARSER_INDICATOR);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider(value = "incorrectInputParameter", location = DataProviderSource.class)
    public void shouldIncorrectParserIndicatorCauseException(String parserIndicator) {
        String line = "type=JumpRope price=2.0";

        LineParser.parseLine(line, parserIndicator);
    }

}
