package by.epam.gameroom.util.creators;

import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.entities.toys.educational.RubikCube;
import by.epam.gameroom.util.LineParser;

public class RubikCubeCreator implements ToyCreator {

    public Toy createToy(String[] parameters) {
        if (parameters == null || parameters.length == 0) {
            throw new IllegalArgumentException("Input parameters is empty.");
        }
        String[] parsedPrice = LineParser.parseLine(parameters[1], LineParser.VALUE_PARSER_INDICATOR);
        double price = Double.parseDouble(parsedPrice[1]);

        String isAssembledConditionTrue = "+";
        String isAssembledConditionFalse = "-";

        boolean isAssembled = false;
        String[] parsedIsAssembled = LineParser.parseLine(parameters[2], LineParser.VALUE_PARSER_INDICATOR);

        if (parsedIsAssembled[1].equals(isAssembledConditionFalse)) {
            isAssembled = false;
        } else if (parsedIsAssembled[1].equals(isAssembledConditionTrue)) {
            isAssembled = true;
        }

        return new RubikCube(price, isAssembled);
    }

}
