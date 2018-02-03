package by.epam.gameroom.run;

import by.epam.gameroom.entities.GameRoom;
import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.exceptions.DataLoadException;
import by.epam.gameroom.util.LineParser;
import by.epam.gameroom.util.builders.ToyBuilder;
import by.epam.gameroom.util.builders.ToyFactory;
import by.epam.gameroom.util.data.DataFileLoader;
import by.epam.gameroom.util.data.DataValidator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GameRoomDirector {

    private final static Logger LOGGER = Logger.getLogger(GameRoomDirector.class);
    private final static String FILE_NAME = "./src/main/resources/data.txt";
    private final static int TYPE_VALUE_INDEX = 0;
    private final static int TYPE_PARAMETER_INDEX = 1;

    private List<Toy> getToysFromFile(String fileName){
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("Check file name.");
        }

        LOGGER.info(String.format("Try to load data from file - %s.",fileName));

        DataFileLoader dataFileLoader = new DataFileLoader();
        List<Toy> toys = new ArrayList<>();
        List<String> data = null;
        try {
           data = dataFileLoader.loadDataFromFile(fileName);
        } catch (DataLoadException e) {
            LOGGER.warn(e);
        }

        if (data == null){
            LOGGER.info("Data was loaded incorrect.");
            return null;
        } else {
            LOGGER.info("Data was loaded successful");
        }

        for (String line : data) {
            LOGGER.info(String.format("Try to create toy from line - %s.",line));

            Toy toy = getToyFromLine(line);

            if (toy == null){
                LOGGER.info(String.format("Toy can't be created from line -%s.", line));
            } else {
                LOGGER.info(String.format("Toy was created successful - %s.", toy.toString()));

                toys.add(toy);
            }
        }

        return toys;
    }

    private Toy getToyFromLine(String line){
        DataValidator dataValidator = new DataValidator();

        String[] parsedValues = LineParser.parseLine(line,LineParser.DATA_PARSER_INDICATOR);

        boolean isValuesValid = dataValidator.checkValues(parsedValues);

        if (!isValuesValid){
            return null;
        }

        String typeValue = parsedValues[TYPE_VALUE_INDEX];
        String[] parsedTypeValue = LineParser.parseLine(typeValue,LineParser.VALUE_PARSER_INDICATOR);
        String type = parsedTypeValue[TYPE_PARAMETER_INDEX];
        ToyFactory toyFactory = new ToyFactory();
        ToyBuilder toyBuilder = toyFactory.getToyBuilder(type);

        Toy toy = toyBuilder.createToy(parsedValues);

        return toy;
    }

    public GameRoom buildGameRoom(){
        String roomName = "Laplandia";
        int area = 40000;
        List<Toy> toys = getToysFromFile(FILE_NAME);

        GameRoom gameRoom = new GameRoom(toys,area,roomName);

        return gameRoom;
    }

}
