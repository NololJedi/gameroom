package by.epam.gameroom.util.creators;

import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.entities.toys.educational.Lego;
import by.epam.gameroom.util.LineParser;

public class LegoCreator implements ToyCreator {

    public Toy createToy(String[] parameters) {
        String[] parsedName = LineParser.parseLine(parameters[1], LineParser.VALUE_PARSER_INDICATOR);
        String name = parsedName[1];

        String[] parsedPrice = LineParser.parseLine(parameters[2], LineParser.VALUE_PARSER_INDICATOR);
        double price = Double.parseDouble(parsedPrice[1]);

        String[] parsedSize = LineParser.parseLine(parameters[3], LineParser.VALUE_PARSER_INDICATOR);
        double size = Double.parseDouble(parsedSize[1]);

        String[] parsedCountOfToyMen = LineParser.parseLine(parameters[4], LineParser.VALUE_PARSER_INDICATOR);
        int countOfToyMen = Integer.parseInt(parsedCountOfToyMen[1]);

        String[] parsedCountOfPeaces = LineParser.parseLine(parameters[5], LineParser.VALUE_PARSER_INDICATOR);
        int countOfPeaces = Integer.parseInt(parsedCountOfPeaces[1]);

        return new Lego(name, price, size, countOfToyMen, countOfPeaces);
    }

}
