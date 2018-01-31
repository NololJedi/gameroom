package by.epam.gameroom.util.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {

    private final static int RUBIKCUBE_VALID_VALUES_COUNT = 3;
    private final static int JUMPROPE_VALID_VALUES_COUNT = 3;
    private final static int BASKETBALLSET__VALID_VALUES_COUNT = 5;
    private final static int GAMECONSOLE_VALID_VALUES_COUNT = 5;
    private final static int RADIOCAR__VALID_VALUES_COUNT = 6;
    private final static int LEGO_VALID_VALUES_COUNT = 6;

    private final static String JUMPROPE_TYPE = "JumpRope";
    private final static String LEGO_TYPE = "Lego";
    private final static String GAMECONSOLE_TYPE = "GameConsole";
    private final static String BASKETBALLSET_TYPE = "BasketBallSet";
    private final static String RADIOCAR_TYPE = "RadioCar";
    private final static String RUBIKCUBE_TYPE = "RubikCube";

    private final static String NAME_VALUE_PATTERN_OF_VALIDATION = "^name=\\w+_?\\w+";
    private final static String TYPE_VALUE_PATTERN_OF_VALIDATION = "^type=\\p{Alpha}+";
    private final static String DOUBLE_VALUE_PATTERN_OF_VALIDATION = "(^size=\\d+\\.?\\d+)|(price=\\d+\\.?\\d+)";
    private final static String INTEGER_VALUE_PATTERN_OF_VALIDATION = "^\\w+=\\d+";
    private final static String BOOLEAN_VALUE_PATTERN_OF_VALIDATION = "^\\w+=([-]|[+])";

    public boolean checkValuesCount(String[] parsedLine, int validValuesCount) {
        int actualValuesCount = parsedLine.length;

        return actualValuesCount == validValuesCount;
    }

    public boolean checkValues(String type, String line) {
        return true;
    }


}
