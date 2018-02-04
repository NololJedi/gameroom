package by.epam.gameroom.run;

import by.epam.gameroom.entities.GameRoom;
import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.exceptions.DataLoadException;
import by.epam.gameroom.exceptions.GameRoomCreationException;
import by.epam.gameroom.exceptions.IncorrectValueException;
import by.epam.gameroom.util.Calculator;
import by.epam.gameroom.util.LineParser;
import by.epam.gameroom.util.builders.ToyBuilder;
import by.epam.gameroom.util.builders.ToyFactory;
import by.epam.gameroom.util.builders.ToyParametersProvider;
import by.epam.gameroom.util.data.DataFileLoader;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GameRoomDirector {

    private final static Logger LOGGER = Logger.getLogger(GameRoomDirector.class);
    private final static String FILE_NAME = "./src/main/resources/data.txt";
    private final static int TYPE_PARAMETER_INDEX = 0;

    public GameRoom createGameRoom(String name, double area, double availableMoney) throws GameRoomCreationException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Check input game room name.");
        }
        if (area <= 0) {
            throw new IllegalArgumentException("Check input game room area.");
        }
        if (availableMoney <= 0) {
            throw new IllegalArgumentException("Check input game room available money.");
        }

        LOGGER.info(String.format("Try to create game room - %s.", name));

        List<Toy> toys = getToysFromFile(area, availableMoney);

        if (toys == null){
            throw new GameRoomCreationException("Game room wasn't created. Cause: toys weren't loaded.");
        }

        GameRoom gameRoom = new GameRoom(toys, area, name);

        LOGGER.info("Room was created successful.");

        return gameRoom;
    }

    private List<Toy> getToysFromFile(double area, double availableMoney) {
        List<Toy> toys = new ArrayList<>();
        DataFileLoader dataFileLoader = new DataFileLoader();
        List<String> data = null;
        LOGGER.info(String.format("Try to load data from file - %s.", FILE_NAME));

        try {
            data = dataFileLoader.loadDataFromFile(FILE_NAME);
        } catch (DataLoadException e) {
            LOGGER.warn(e);
            return null;
        }

        LOGGER.info("Data was loaded successful.");

        for (String parameters : data) {
            LOGGER.info(String.format("Try to create toy from parameters - %s.", parameters));

            ToyParametersProvider toyParametersProvider = new ToyParametersProvider();
            ToyFactory toyFactory = new ToyFactory();
            String[] parsedParameters = LineParser.parseLine(parameters, LineParser.DATA_PARSER_INDICATOR);

            try {
                String type = toyParametersProvider.getStringParameter(parsedParameters, TYPE_PARAMETER_INDEX);
                ToyBuilder toyBuilder = toyFactory.getToyBuilder(type);
                Toy toy = toyBuilder.createToy(parsedParameters);

                LOGGER.info(String.format("Toy was created successful - %s.", toy.toString()));

                double toyPrice = toy.getPrice();
                availableMoney = Calculator.calculate(availableMoney, toyPrice);

                double toySize = toy.getSize();
                area = Calculator.calculate(area, toySize);

                toys.add(toy);
            } catch (IncorrectValueException | IllegalArgumentException exception) {
                LOGGER.warn(exception);
            }
        }

        return toys;
    }

}
