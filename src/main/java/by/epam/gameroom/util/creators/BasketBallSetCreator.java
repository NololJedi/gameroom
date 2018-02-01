package by.epam.gameroom.util.creators;

import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.entities.toys.sport.BasketballSet;
import by.epam.gameroom.util.LineParser;

public class BasketBallSetCreator implements ToyCreator {

    public Toy createToy(String[] parameters) {
        String[] parsedName = LineParser.parseLine(parameters[1], LineParser.VALUE_PARSER_INDICATOR);
        String name = parsedName[1];

        String[] parsedPrice = LineParser.parseLine(parameters[2], LineParser.VALUE_PARSER_INDICATOR);
        double price = Double.parseDouble(parsedPrice[1]);

        String[] parsedBallDiameter = LineParser.parseLine(parameters[3], LineParser.VALUE_PARSER_INDICATOR);
        int ballDiameter = Integer.parseInt(parsedBallDiameter[1]);

        String[] parsedBasketHeight = LineParser.parseLine(parameters[4], LineParser.VALUE_PARSER_INDICATOR);
        int basketHeight = Integer.parseInt(parsedBasketHeight[1]);

        return new BasketballSet(name, price, ballDiameter, basketHeight);
    }

}
