package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.entities.toys.educational.Lego;
import by.epam.gameroom.util.LineParser;

import static by.epam.gameroom.entities.toys.educational.Lego.LEGO_VALID_VALUES_COUNT;

public class LegoBuilder implements ToyBuilder {

    private final static int nameValueIndex = 1;
    private final static int priceValueIndex = 2;
    private final static int sizeValueIndex = 3;
    private final static int countOfToyMenValueIndex = 4;
    private final static int countOfPeacesValueIndex = 5;
    private final static int parameterValueIndex = 1;

    public Lego createToy(String[] values) {
        if (values == null || values.length != LEGO_VALID_VALUES_COUNT) {
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

        String countOfToyMenValue = values[countOfToyMenValueIndex];
        String[] parsedCountOfToyMen = LineParser.parseLine(countOfToyMenValue, LineParser.VALUE_PARSER_INDICATOR);
        String countOfToyMenParameter = parsedCountOfToyMen[parameterValueIndex];
        int countOfToyMen = Integer.parseInt(countOfToyMenParameter);

        String countOfPeacesValue = values[countOfPeacesValueIndex];
        String[] parsedCountOfPeaces = LineParser.parseLine(countOfPeacesValue, LineParser.VALUE_PARSER_INDICATOR);
        String countOfPeacesParameter = parsedCountOfPeaces[parameterValueIndex];
        int countOfPeaces = Integer.parseInt(countOfPeacesParameter);

        return new Lego(name, price, size, countOfToyMen, countOfPeaces);
    }

}
