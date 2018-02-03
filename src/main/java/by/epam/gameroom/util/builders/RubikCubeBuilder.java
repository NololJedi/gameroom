package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.educational.RubikCube;
import by.epam.gameroom.util.LineParser;

import static by.epam.gameroom.entities.toys.educational.RubikCube.RUBIKCUBE_VALID_VALUES_COUNT;

public class RubikCubeBuilder implements ToyBuilder {

    private final static int priceValueIndex = 1;
    private final static int isAssembledValueIndex = 2;
    private final static int parameterValueIndex = 1;

    public RubikCube createToy(String[] values) {
        if (values == null || values.length != RUBIKCUBE_VALID_VALUES_COUNT) {
            throw new IllegalArgumentException("Incorrect input values.");
        }

        String priceValue = values[priceValueIndex];
        String[] parsedPrice = LineParser.parseLine(priceValue, LineParser.VALUE_PARSER_INDICATOR);
        String priceParameter = parsedPrice[parameterValueIndex];
        double price = Double.parseDouble(priceParameter);

        String isAssembledConditionTrue = "+";
        String isAssembledConditionFalse = "-";

        boolean isAssembled = false;
        String isAssembledValue = values[isAssembledValueIndex];
        String[] parsedIsAssembled = LineParser.parseLine(isAssembledValue, LineParser.VALUE_PARSER_INDICATOR);
        String isAssembledParameter = parsedIsAssembled[parameterValueIndex];

        if (isAssembledParameter.equals(isAssembledConditionFalse)) {
            isAssembled = false;
        } else if (isAssembledParameter.equals(isAssembledConditionTrue)) {
            isAssembled = true;
        }

        return new RubikCube(price, isAssembled);
    }

}
