package by.epam.gameroom.util.data;

import by.epam.gameroom.util.LineParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {

    private final static String NAME_PARAMETER_PATTERN_OF_VALIDATION = "\\w+_?\\w+";
    private final static String TYPE_PARAMETER_PATTERN_OF_VALIDATION = "JumpRope|Lego|GameConsole|BasketBallSet|RadioCar|RubikCube";
    private final static String DOUBLE_PARAMETER_PATTERN_OF_VALIDATION = "^\\d+\\.{1}\\d+$";
    private final static String INTEGER_PARAMETER_PATTERN_OF_VALIDATION = "^\\d+";
    private final static String BOOLEAN_PARAMETER_PATTERN_OF_VALIDATION = "[-]|[+]";

    private final static String NAME_PARAMETER_INDICATOR = "name";
    private final static String TYPE_PARAMETER_INDICATOR = "type";
    private final static String SIZE_PARAMETER_INDICATOR = "size";
    private final static String PRICE_PARAMETER_INDICATOR = "price";
    private final static String BOOLEAN_PARAMETER_INDICATOR = "is";
    private final static String INTEGER_PARAMETER_INDICATOR = "num";

    private final static int NAME_PARAMETER_INDEX = 0;
    private final static int VALUE_PARAMETER_INDEX = 1;

    public boolean checkParameter(String parameter) {
        if (parameter == null || parameter.isEmpty()) {
            throw new IllegalArgumentException("Input parameter is empty.");
        }

        String[] parsedParameter = LineParser.parseLine(parameter, LineParser.PARAMETER_PARSER_INDICATOR);

        if (parsedParameter.length == 1) {
            return false;
        }

        String parameterName = parsedParameter[NAME_PARAMETER_INDEX];
        String parameterValue = parsedParameter[VALUE_PARAMETER_INDEX];
        Pattern pattern = getPatterOfValidationByParameterName(parameterName);

        if (pattern == null) {
            return false;
        }

        Matcher matcher = pattern.matcher(parameterValue);

        return matcher.matches();
    }

    private Pattern getPatterOfValidationByParameterName(String parameterName) {
        if (parameterName == null || parameterName.isEmpty()) {
            throw new IllegalArgumentException("Value name is empty.");
        }
        Pattern pattern = null;

        if (parameterName.startsWith(NAME_PARAMETER_INDICATOR)) {
            pattern = Pattern.compile(NAME_PARAMETER_PATTERN_OF_VALIDATION);
        }
        if (parameterName.startsWith(TYPE_PARAMETER_INDICATOR)) {
            pattern = Pattern.compile(TYPE_PARAMETER_PATTERN_OF_VALIDATION);
        }
        if (parameterName.startsWith(BOOLEAN_PARAMETER_INDICATOR)) {
            pattern = Pattern.compile(BOOLEAN_PARAMETER_PATTERN_OF_VALIDATION);
        }
        if (parameterName.startsWith(SIZE_PARAMETER_INDICATOR) || parameterName.startsWith(PRICE_PARAMETER_INDICATOR)) {
            pattern = Pattern.compile(DOUBLE_PARAMETER_PATTERN_OF_VALIDATION);
        }
        if (parameterName.startsWith(INTEGER_PARAMETER_INDICATOR)) {
            pattern = Pattern.compile(INTEGER_PARAMETER_PATTERN_OF_VALIDATION);
        }

        return pattern;
    }

}
