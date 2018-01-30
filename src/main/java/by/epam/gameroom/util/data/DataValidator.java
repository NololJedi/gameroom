package by.epam.gameroom.util.data;

import by.epam.gameroom.exceptions.IncorrectValueException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {

    private final static int RUBIKCUBE_VALID_VALUES_COUNT = 3;
    private final static int JUMPROPE_VALID_VALUES_COUNT = 3;
    private final static int BASKETBALLSET__VALID_VALUES_COUNT = 5;
    private final static int GAMECONSOLE_VALID_VALUES_COUNT = 5;
    private final static int RADIOCAR__VALID_VALUES_COUNT = 6;
    private final static int LEGO_VALID_VALUES_COUNT = 6;

    private final static String NAME_VALUE_PATTERN_OF_VALIDATION = "^name=\\w+_?\\w+";
    private final static String TYPE_VALUE_PATTERN_OF_VALIDATION = "^type=\\p{Alpha}+_?\\p{Alpha}+";
    private final static String DOUBLE_VALUE_PATTERN_OF_VALIDATION = "(^size=\\d+\\.?\\d+)|(price=\\d+\\.?\\d+)";
    private final static String INTEGER_VALUE_PATTERN_OF_VALIDATION = "^\\w+=\\d+";
    private final static String BOOLEAN_VALUE_PATTERN_OF_VALIDATION = "^\\w+=([-]|[+])";

    private void checkValuesCount(String[] parsedLine, int validValuesCount) throws IncorrectValueException {
        int actualValuesCount = parsedLine.length;
        boolean validationResult = validValuesCount == actualValuesCount;

        if (!validationResult) {
            throw new IncorrectValueException(String.format("Incorrect values count - %d, needed -%d.", actualValuesCount, validValuesCount));
        }
    }

    private boolean checkValue(String value, String patternOfValidation) throws IncorrectValueException {
        Pattern pattern = Pattern.compile(patternOfValidation);
        Matcher matcher = pattern.matcher(value);

        boolean resultOfValidation = matcher.matches();

        if (resultOfValidation) {
            return true;
        } else {
            throw new IncorrectValueException(String.format("Incorrect value was detected - %s.", value));
        }
    }

    public boolean checkToyType(String toyType) throws IncorrectValueException {
        if (toyType == null || toyType.isEmpty()) {
            throw new IllegalArgumentException("Empty toy type detected.");
        }

        return checkValue(toyType, TYPE_VALUE_PATTERN_OF_VALIDATION);
    }

    public boolean checkToyValues(String toyType, String[] parsedLine) throws IncorrectValueException {
        if (toyType == null || toyType.isEmpty()) {
            throw new IllegalArgumentException("Empty toy type detected.");
        }
        if (parsedLine == null || parsedLine.length == 0) {
            throw new IllegalArgumentException("Values is empty.");
        }

        switch (toyType) {
            case "JumpRope": {
                return jumpRopeValuesValidation(parsedLine);
            }
            case "RubikCube": {
                return rubikCubeValuesValidation(parsedLine);
            }
            case "GameConsole": {
                return gameConsoleValuesValidation(parsedLine);
            }
            case "BasketBallSet": {
                return basketBallSetValuesValidation(parsedLine);
            }
            case "Lego": {
                return legoValuesValidation(parsedLine);
            }
            case "RadioCar": {
                return radioCarValuesValidation(parsedLine);
            }
            default: {
                throw new IncorrectValueException(String.format("Incorrect type of class was detected - %s.", toyType));
            }

        }
    }

    private boolean jumpRopeValuesValidation(String[] parsedLine) throws IncorrectValueException {
        checkValuesCount(parsedLine, JUMPROPE_VALID_VALUES_COUNT);

        String priceValue = parsedLine[1];
        String lengthValue = parsedLine[2];

        boolean priceValueValidation = checkValue(priceValue, DOUBLE_VALUE_PATTERN_OF_VALIDATION);
        boolean lengthValueValidation = checkValue(lengthValue, INTEGER_VALUE_PATTERN_OF_VALIDATION);

        return priceValueValidation && lengthValueValidation;
    }

    private boolean rubikCubeValuesValidation(String[] parsedLine) throws IncorrectValueException {
        checkValuesCount(parsedLine, RUBIKCUBE_VALID_VALUES_COUNT);

        String priceValue = parsedLine[1];
        String isAssembledValue = parsedLine[2];

        boolean priceValueValidation = checkValue(priceValue, DOUBLE_VALUE_PATTERN_OF_VALIDATION);
        boolean isAssembledValueValidation = checkValue(isAssembledValue, BOOLEAN_VALUE_PATTERN_OF_VALIDATION);

        return priceValueValidation && isAssembledValueValidation;
    }

    private boolean gameConsoleValuesValidation(String[] parsedLine) throws IncorrectValueException {
        checkValuesCount(parsedLine, GAMECONSOLE_VALID_VALUES_COUNT);

        String nameValue = parsedLine[1];
        String priceValue = parsedLine[2];
        String sizeValue = parsedLine[3];
        String isInternetAvailableValue = parsedLine[4];

        boolean nameValueValidation = checkValue(nameValue, NAME_VALUE_PATTERN_OF_VALIDATION);
        boolean priceValueValidation = checkValue(priceValue, DOUBLE_VALUE_PATTERN_OF_VALIDATION);
        boolean sizeValueValidation = checkValue(sizeValue, DOUBLE_VALUE_PATTERN_OF_VALIDATION);
        boolean isInternetAvailableValueValidation = checkValue(isInternetAvailableValue, BOOLEAN_VALUE_PATTERN_OF_VALIDATION);

        return nameValueValidation && priceValueValidation && sizeValueValidation && isInternetAvailableValueValidation;
    }

    private boolean basketBallSetValuesValidation(String[] parsedLine) throws IncorrectValueException {
        checkValuesCount(parsedLine, BASKETBALLSET__VALID_VALUES_COUNT);

        String nameValue = parsedLine[1];
        String priceValue = parsedLine[2];
        String ballDiameterValue = parsedLine[3];
        String basketHeightValue = parsedLine[4];

        boolean nameValueValidation = checkValue(nameValue, NAME_VALUE_PATTERN_OF_VALIDATION);
        boolean priceValueValidation = checkValue(priceValue, DOUBLE_VALUE_PATTERN_OF_VALIDATION);
        boolean ballDiameterValueValidation = checkValue(ballDiameterValue, INTEGER_VALUE_PATTERN_OF_VALIDATION);
        boolean basketHeightValueValidation = checkValue(basketHeightValue, INTEGER_VALUE_PATTERN_OF_VALIDATION);

        return nameValueValidation && priceValueValidation && ballDiameterValueValidation && basketHeightValueValidation;
    }

    private boolean legoValuesValidation(String[] parsedLine) throws IncorrectValueException {
        checkValuesCount(parsedLine, LEGO_VALID_VALUES_COUNT);

        String nameValue = parsedLine[1];
        String priceValue = parsedLine[2];
        String sizeValue = parsedLine[3];
        String countOfToyMenValue = parsedLine[4];
        String countOfPeacesValue = parsedLine[5];

        boolean nameValueValidation = checkValue(nameValue, NAME_VALUE_PATTERN_OF_VALIDATION);
        boolean priceValueValidation = checkValue(priceValue, DOUBLE_VALUE_PATTERN_OF_VALIDATION);
        boolean sizeValueValidation = checkValue(sizeValue, DOUBLE_VALUE_PATTERN_OF_VALIDATION);
        boolean countOfToyMenValueValidation = checkValue(countOfToyMenValue, INTEGER_VALUE_PATTERN_OF_VALIDATION);
        boolean countOfPeacesValueValidation = checkValue(countOfPeacesValue, INTEGER_VALUE_PATTERN_OF_VALIDATION);

        return nameValueValidation && priceValueValidation && sizeValueValidation
                && countOfToyMenValueValidation && countOfPeacesValueValidation;
    }

    private boolean radioCarValuesValidation(String[] parsedLine) throws IncorrectValueException {
        checkValuesCount(parsedLine, RADIOCAR__VALID_VALUES_COUNT);

        String nameValue = parsedLine[1];
        String priceValue = parsedLine[2];
        String sizeValue = parsedLine[3];
        String countOfBatteries = parsedLine[4];
        String maxSpeed = parsedLine[5];

        boolean nameValueValidation = checkValue(nameValue, NAME_VALUE_PATTERN_OF_VALIDATION);
        boolean priceValueValidation = checkValue(priceValue, DOUBLE_VALUE_PATTERN_OF_VALIDATION);
        boolean sizeValueValidation = checkValue(sizeValue, DOUBLE_VALUE_PATTERN_OF_VALIDATION);
        boolean countOfMenValueValidation = checkValue(countOfBatteries, INTEGER_VALUE_PATTERN_OF_VALIDATION);
        boolean countOfPeacesValueValidation = checkValue(maxSpeed, INTEGER_VALUE_PATTERN_OF_VALIDATION);

        return nameValueValidation && priceValueValidation && sizeValueValidation
                && countOfMenValueValidation && countOfPeacesValueValidation;
    }

}
