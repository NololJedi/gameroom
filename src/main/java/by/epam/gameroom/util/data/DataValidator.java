package by.epam.gameroom.util.data;

import by.epam.gameroom.util.LineParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {

    public final static String JUMPROPE_TYPE = "JumpRope";
    public final static String LEGO_TYPE = "Lego";
    public final static String GAMECONSOLE_TYPE = "GameConsole";
    public final static String BASKETBALLSET_TYPE = "BasketBallSet";
    public final static String RADIOCAR_TYPE = "RadioCar";
    public final static String RUBIKCUBE_TYPE = "RubikCube";

    private final static int RUBIKCUBE_VALID_VALUES_COUNT = 3;
    private final static int JUMPROPE_VALID_VALUES_COUNT = 3;
    private final static int BASKETBALLSET__VALID_VALUES_COUNT = 5;
    private final static int GAMECONSOLE_VALID_VALUES_COUNT = 5;
    private final static int RADIOCAR__VALID_VALUES_COUNT = 6;
    private final static int LEGO_VALID_VALUES_COUNT = 6;

    private final static String NAME_VALUE_PATTERN_OF_VALIDATION = "\\w+_?\\w+";
    private final static String TYPE_VALUE_PATTERN_OF_VALIDATION = "JumpRope|Lego|GameConsole|BasketBallSet|RadioCar|RubikCube";
    private final static String DOUBLE_VALUE_PATTERN_OF_VALIDATION = "\\d+\\.?\\d+";
    private final static String INTEGER_VALUE_PATTERN_OF_VALIDATION = "^\\d+";
    private final static String BOOLEAN_VALUE_PATTERN_OF_VALIDATION = "[-]|[+]";

    private final static String NAME_VALUE_INDICATOR = "name";
    private final static String TYPE_VALUE_INDICATOR = "type";
    private final static String SIZE_VALUE_INDICATOR = "size";
    private final static String PRICE_VALUE_INDICATOR = "price";
    private final static String BOOLEAN_VALUE_INDICATOR = "is";
    private final static String INTEGER_VALUE_INDICATOR = "num";

    public boolean checkValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Input value is empty.");
        }

        String[] parsedValue = LineParser.parseLine(value, LineParser.VALUE_PARSER_INDICATOR);

        if (parsedValue.length == 1) {
            return false;
        }

        String valueName = parsedValue[0];
        String valueParameter = parsedValue[1];
        Pattern pattern = getPatterOfValidationByValueName(valueName);

        if (pattern == null) {
            return false;
        }

        Matcher matcher = pattern.matcher(valueParameter);

        return matcher.matches();
    }

    public boolean checkValues(String type, String[] values) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Input type is empty");
        }
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Input values is empty");
        }

        int expectedCountOfValues = getCountOfValuesByType(type);
        int actualCountOfValues = values.length;

        if (actualCountOfValues != expectedCountOfValues) {
            return false;
        }

        for (int arrayIndex = 0; arrayIndex < actualCountOfValues; arrayIndex++) {
            boolean resultOfValidation = checkValue(values[arrayIndex]);
            if (!resultOfValidation) {
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

    private int getCountOfValuesByType(String type) {

        switch (type) {
            case JUMPROPE_TYPE: {
                return JUMPROPE_VALID_VALUES_COUNT;
            }
            case LEGO_TYPE: {
                return LEGO_VALID_VALUES_COUNT;
            }
            case GAMECONSOLE_TYPE: {
                return GAMECONSOLE_VALID_VALUES_COUNT;
            }
            case BASKETBALLSET_TYPE: {
                return BASKETBALLSET__VALID_VALUES_COUNT;
            }
            case RADIOCAR_TYPE: {
                return RADIOCAR__VALID_VALUES_COUNT;
            }
            case RUBIKCUBE_TYPE: {
                return RUBIKCUBE_VALID_VALUES_COUNT;
            }
            default: {
                throw new IllegalArgumentException(String.format("No such type(%s) was found. Check the input parameter.", type));
            }
        }
    }

}
