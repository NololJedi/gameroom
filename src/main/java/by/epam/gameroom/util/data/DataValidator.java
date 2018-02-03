package by.epam.gameroom.util.data;

import by.epam.gameroom.util.LineParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {

    private final static String NAME_VALUE_PATTERN_OF_VALIDATION = "\\w+_?\\w+";
    private final static String TYPE_VALUE_PATTERN_OF_VALIDATION = "JumpRope|Lego|GameConsole|BasketBallSet|RadioCar|RubikCube";
    private final static String DOUBLE_VALUE_PATTERN_OF_VALIDATION = "^\\d+\\.{1}\\d+$";
    private final static String INTEGER_VALUE_PATTERN_OF_VALIDATION = "^\\d+";
    private final static String BOOLEAN_VALUE_PATTERN_OF_VALIDATION = "[-]|[+]";

    private final static String NAME_VALUE_INDICATOR = "name";
    private final static String TYPE_VALUE_INDICATOR = "type";
    private final static String SIZE_VALUE_INDICATOR = "size";
    private final static String PRICE_VALUE_INDICATOR = "price";
    private final static String BOOLEAN_VALUE_INDICATOR = "is";
    private final static String INTEGER_VALUE_INDICATOR = "num";

    private final static int NAME_PARAMETER_INDEX = 0;
    private final static int VALUE_PARAMETER_INDEX = 1;

    public boolean checkValuesCount(String[] parsedValues, int typeValuesCount) {
        if (parsedValues == null || parsedValues.length == 0) {
            throw new IllegalArgumentException("Empty parsed values is empty.");
        }
        if (typeValuesCount == 0) {
            throw new IllegalArgumentException("Check input values count.");
        }

        int currentValueCount = parsedValues.length;

        return typeValuesCount == currentValueCount;
    }

    public boolean checkValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Input value is empty.");
        }

        String[] parsedValue = LineParser.parseLine(value, LineParser.VALUE_PARSER_INDICATOR);

        if (parsedValue.length == 1) {
            return false;
        }

        String valueName = parsedValue[NAME_PARAMETER_INDEX];
        String valueParameter = parsedValue[VALUE_PARAMETER_INDEX];
        Pattern pattern = getPatterOfValidationByValueName(valueName);

        if (pattern == null) {
            return false;
        }

        Matcher matcher = pattern.matcher(valueParameter);

        return matcher.matches();
    }

    public boolean checkValues(String[] parsedValues) {
        if (parsedValues == null || parsedValues.length == 0) {
            throw new IllegalArgumentException("Empty parsed values is empty.");
        }

        for (String parsedValue : parsedValues) {
            boolean isValuesValid = checkValue(parsedValue);

            if (!isValuesValid) {
                return false;
            }
        }

        return true;
    }

    private Pattern getPatterOfValidationByValueName(String valueName) {
        if (valueName == null || valueName.isEmpty()) {
            throw new IllegalArgumentException("Value name is empty.");
        }
        Pattern pattern = null;

        if (valueName.startsWith(NAME_VALUE_INDICATOR)) {
            pattern = Pattern.compile(NAME_VALUE_PATTERN_OF_VALIDATION);
        }
        if (valueName.startsWith(TYPE_VALUE_INDICATOR)) {
            pattern = Pattern.compile(TYPE_VALUE_PATTERN_OF_VALIDATION);
        }
        if (valueName.startsWith(BOOLEAN_VALUE_INDICATOR)) {
            pattern = Pattern.compile(BOOLEAN_VALUE_PATTERN_OF_VALIDATION);
        }
        if (valueName.startsWith(SIZE_VALUE_INDICATOR) || valueName.startsWith(PRICE_VALUE_INDICATOR)) {
            pattern = Pattern.compile(DOUBLE_VALUE_PATTERN_OF_VALIDATION);
        }
        if (valueName.startsWith(INTEGER_VALUE_INDICATOR)) {
            pattern = Pattern.compile(INTEGER_VALUE_PATTERN_OF_VALIDATION);
        }

        return pattern;
    }

}
