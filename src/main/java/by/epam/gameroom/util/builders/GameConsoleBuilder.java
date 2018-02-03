package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.electronic.GameConsole;
import by.epam.gameroom.util.LineParser;

import static by.epam.gameroom.entities.toys.electronic.GameConsole.GAMECONSOLE_VALID_VALUES_COUNT;

public class GameConsoleBuilder implements ToyBuilder {

    private final static int nameValueIndex = 1;
    private final static int priceValueIndex = 2;
    private final static int sizeValueIndex = 3;
    private final static int isInternetAvailableValueIndex = 4;
    private final static int parameterValueIndex = 1;

    public GameConsole createToy(String[] values) {
        if (values == null || values.length != GAMECONSOLE_VALID_VALUES_COUNT) {
            throw new IllegalArgumentException("Incorrect input values.");
        }

        String nameValue = values[nameValueIndex];
        String[] parsedName = LineParser.parseLine(nameValue, LineParser.VALUE_PARSER_INDICATOR);
        String name = parsedName[parameterValueIndex];

        String priceValue = values[priceValueIndex];
        String[] parsedPrice = LineParser.parseLine(priceValue, LineParser.VALUE_PARSER_INDICATOR);
        String priceParameter = parsedPrice[parameterValueIndex];
        double price = Double.parseDouble(priceParameter);

        String sizeValue = values[sizeValueIndex];
        String[] parsedSize = LineParser.parseLine(sizeValue, LineParser.VALUE_PARSER_INDICATOR);
        String sizeParameter = parsedSize[parameterValueIndex];
        double size = Double.parseDouble(sizeParameter);

        String isInternetAvailableConditionTrue = "+";
        String isInternetAvailableConditionFalse = "-";

        boolean isInternetAvailable = false;
        String isInternetAvailableValue = values[isInternetAvailableValueIndex];
        String[] parsedIsInternetAvailable = LineParser.parseLine(isInternetAvailableValue, LineParser.VALUE_PARSER_INDICATOR);
        String isInternetAvailableParameter = parsedIsInternetAvailable[parameterValueIndex];

        if (isInternetAvailableParameter.equals(isInternetAvailableConditionFalse)) {
            isInternetAvailable = false;
        } else if (isInternetAvailableParameter.equals(isInternetAvailableConditionTrue)) {
            isInternetAvailable = true;
        }

        return new GameConsole(name, price, size, isInternetAvailable);
    }

}
