package by.epam.gameroom.util.creators;

import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.entities.toys.sport.JumpRope;
import by.epam.gameroom.util.LineParser;

public class JumpRopeCreator implements ToyCreator {

    public Toy createToy(String[] parameters) {
        if (parameters == null || parameters.length == 0) {
            throw new IllegalArgumentException("Input parameters is empty.");
        }

        String[] parsedPrice = LineParser.parseLine(parameters[1], LineParser.VALUE_PARSER_INDICATOR);
        double price = Double.parseDouble(parsedPrice[1]);

        String[] parsedLength = LineParser.parseLine(parameters[2], LineParser.VALUE_PARSER_INDICATOR);
        int length = Integer.parseInt(parsedLength[1]);

        return new JumpRope(price, length);
    }

}
