package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.entities.toys.electronic.RadioCar;
import by.epam.gameroom.util.LineParser;

import static by.epam.gameroom.entities.toys.electronic.RadioCar.RADIOCAR__VALID_VALUES_COUNT;

public class RadioCarBuilder implements ToyBuilder {

    private final static int nameValueIndex = 1;
    private final static int priceValueIndex = 2;
    private final static int sizeValueIndex = 3;
    private final static int countOfBatteriesValueIndex = 4;
    private final static int maxSpeedValueIndex = 5;
    private final static int parameterValueIndex = 1;

    public RadioCar createToy(String[] values) {
        if (values == null || values.length != RADIOCAR__VALID_VALUES_COUNT) {
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

        String countOfBatteriesValue = values[countOfBatteriesValueIndex];
        String[] parsedCountOfBatteries = LineParser.parseLine(countOfBatteriesValue, LineParser.VALUE_PARSER_INDICATOR);
        String countOfBatteriesParameter = parsedCountOfBatteries[parameterValueIndex];
        int countOfBatteries = Integer.parseInt(countOfBatteriesParameter);

        String maxSpeedValue = values[maxSpeedValueIndex];
        String[] parsedMaxSpeed = LineParser.parseLine(maxSpeedValue, LineParser.VALUE_PARSER_INDICATOR);
        String maxSpeedParameter = parsedMaxSpeed[parameterValueIndex];
        int maxSpeed = Integer.parseInt(maxSpeedParameter);

        return new RadioCar(name, price, size, countOfBatteries, maxSpeed);
    }

}
