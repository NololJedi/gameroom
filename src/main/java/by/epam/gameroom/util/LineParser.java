package by.epam.gameroom.util;

public class LineParser {

    public static final String DATA_PARSER_INDICATOR = " ";
    public static final String VALUE_PARSER_INDICATOR = "=";

    public String[] parseLine(String line, String parserIndicator) {
        if (line == null || line.isEmpty()){
            throw new IllegalArgumentException("Empty line to parse.");
        }
        if (parserIndicator == null || parserIndicator.isEmpty()){
            throw new IllegalArgumentException("Empty parser indicator.");
        }

        String[] parsedLine = line.split(parserIndicator);

        return parsedLine;
    }

}