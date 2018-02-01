package by.epam.gameroom.util.creators;

import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.entities.toys.electronic.GameConsole;
import by.epam.gameroom.util.LineParser;

public class GameConsoleCreator implements ToyCreator {

    public Toy createToy(String[] parameters) {

        String[] parsedName = LineParser.parseLine(parameters[1], LineParser.VALUE_PARSER_INDICATOR);
        String name = parsedName[1];

        String[] parsedPrice = LineParser.parseLine(parameters[2], LineParser.VALUE_PARSER_INDICATOR);
        double price = Double.parseDouble(parsedPrice[1]);

        String[] parsedSize = LineParser.parseLine(parameters[3], LineParser.VALUE_PARSER_INDICATOR);
        double size = Double.parseDouble(parsedSize[1]);

        String isInternetAvailableConditionTrue = "+";
        String isInternetAvailableConditionFalse = "-";

        boolean isInternetAvailable = false;
        String[] parsedIsInternetAvailable = LineParser.parseLine(parameters[4], LineParser.VALUE_PARSER_INDICATOR);

        if (parsedIsInternetAvailable[1].equals(isInternetAvailableConditionFalse)) {
            isInternetAvailable = false;
        } else if (parsedIsInternetAvailable[1].equals(isInternetAvailableConditionTrue)) {
            isInternetAvailable = true;
        }

        return new GameConsole(name, price, size, isInternetAvailable);
    }

}
