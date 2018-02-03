package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.sport.JumpRope;
import by.epam.gameroom.util.LineParser;

import static by.epam.gameroom.entities.toys.sport.JumpRope.JUMPROPE_VALID_VALUES_COUNT;

public class JumpRopeBuilder implements ToyBuilder {

    private final static int priceValueIndex = 1;
    private final static int lengthValueIndex = 2;
    private final static int parameterValueIndex = 1;

    public JumpRope createToy(String[] values) {
        if (values == null || values.length != JUMPROPE_VALID_VALUES_COUNT) {
            throw new IllegalArgumentException("Incorrect input values.");
        }

        String priceValue = values[priceValueIndex];
        String[] parsedPrice = LineParser.parseLine(priceValue, LineParser.VALUE_PARSER_INDICATOR);
        String priceParameter = parsedPrice[parameterValueIndex];
        double price = Double.parseDouble(priceParameter);

        String lengthValue = values[lengthValueIndex];
        String[] parsedLength = LineParser.parseLine(lengthValue, LineParser.VALUE_PARSER_INDICATOR);
        String lengthParameter = parsedLength[parameterValueIndex];
        int length = Integer.parseInt(lengthParameter);

        return new JumpRope(price, length);
    }

}
