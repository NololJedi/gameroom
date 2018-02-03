package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.entities.toys.sport.BasketballSet;
import by.epam.gameroom.util.LineParser;

import static by.epam.gameroom.entities.toys.sport.BasketballSet.BASKETBALLSET__VALID_VALUES_COUNT;

public class BasketBallSetBuilder implements ToyBuilder {

    private final static int nameValueIndex = 1;
    private final static int priceValueIndex = 2;
    private final static int ballDiameterValueIndex = 3;
    private final static int basketHeightValueIndex = 4;
    private final static int parameterValueIndex = 1;

    public BasketballSet createToy(String[] values) {
        if (values == null || values.length != BASKETBALLSET__VALID_VALUES_COUNT){
            throw new IllegalArgumentException("Incorrect input values.");
        }

        String nameValue = values[nameValueIndex];
        String[] parsedName = LineParser.parseLine(nameValue, LineParser.VALUE_PARSER_INDICATOR);
        String name = parsedName[parameterValueIndex];

        String priceValue = values[priceValueIndex];
        String[] parsedPrice = LineParser.parseLine(priceValue, LineParser.VALUE_PARSER_INDICATOR);
        String priceParameter = parsedPrice[parameterValueIndex];
        double price = Double.parseDouble(priceParameter);

        String ballDiameterValue = values[ballDiameterValueIndex];
        String[] parsedBallDiameter = LineParser.parseLine(ballDiameterValue, LineParser.VALUE_PARSER_INDICATOR);
        String ballDiameterParameter = parsedBallDiameter[parameterValueIndex];
        int ballDiameter = Integer.parseInt(ballDiameterParameter);

        String basketHeightValue = values[basketHeightValueIndex];
        String[] parsedBasketHeight = LineParser.parseLine(basketHeightValue, LineParser.VALUE_PARSER_INDICATOR);
        String basketHeightParameter = parsedBasketHeight[parameterValueIndex];
        int basketHeight = Integer.parseInt(basketHeightParameter);

        return new BasketballSet(name, price, ballDiameter, basketHeight);
    }

}
