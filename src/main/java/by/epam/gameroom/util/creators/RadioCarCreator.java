package by.epam.gameroom.util.creators;

import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.entities.toys.electronic.RadioCar;
import by.epam.gameroom.util.LineParser;

public class RadioCarCreator implements ToyCreator {

    public Toy createToy(String[] parameters) {

        String[] parsedName = LineParser.parseLine(parameters[1], LineParser.VALUE_PARSER_INDICATOR);
        String name = parsedName[1];

        String[] parsedPrice = LineParser.parseLine(parameters[2], LineParser.VALUE_PARSER_INDICATOR);
        double price = Double.parseDouble(parsedPrice[1]);

        String[] parsedSize = LineParser.parseLine(parameters[3], LineParser.VALUE_PARSER_INDICATOR);
        double size = Double.parseDouble(parsedSize[1]);

        String[] parsedCountOfBatteries = LineParser.parseLine(parameters[4], LineParser.VALUE_PARSER_INDICATOR);
        int countOfBatteries = Integer.parseInt(parsedCountOfBatteries[1]);

        String[] parsedMaxSpeed = LineParser.parseLine(parameters[5], LineParser.VALUE_PARSER_INDICATOR);
        int maxSpeed = Integer.parseInt(parsedMaxSpeed[1]);

        return new RadioCar(name, price, size, countOfBatteries, maxSpeed);
    }

}
