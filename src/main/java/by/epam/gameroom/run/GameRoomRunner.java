package by.epam.gameroom.run;

import by.epam.gameroom.comparator.ComparatorParameter;
import by.epam.gameroom.comparator.ToyComparator;
import by.epam.gameroom.entities.GameRoom;
import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.entities.toys.electronic.GameConsole;
import by.epam.gameroom.exceptions.GameRoomCreationException;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class GameRoomRunner {

    private final static Logger LOGGER = Logger.getLogger(GameRoomRunner.class);
    private final static String GAME_ROOM_NAME = "Oz country.";
    private final static double GAME_ROOM_AREA = 4000.0;
    private final static double GAME_ROOM_AVAILABLE_MONEY = 2000.0;
    private final static GameConsole GAME_CONSOLE;

    static {
        String name = "Xbox360";
        double price = 45.0;
        double size = 25.0;
        boolean isInternetAvailable = true;

        GAME_CONSOLE = new GameConsole(name, price, size, isInternetAvailable);
    }

    public static void main(String[] args) {
        LOGGER.info("Start program.");

        GameRoomDirector gameRoomDirector = new GameRoomDirector();
        GameRoom gameRoom = null;

        try {
            gameRoom = gameRoomDirector.createGameRoom(GAME_ROOM_NAME, GAME_ROOM_AREA, GAME_ROOM_AVAILABLE_MONEY);
        } catch (GameRoomCreationException e) {
            LOGGER.error(e);
            System.exit(1);
        }

        LOGGER.info("Toys in room : ");

        List<Toy> toys = gameRoom.getToys();
        logList(toys);

        LOGGER.info(String.format("Is that toy - %s in the room - %s", GAME_CONSOLE.toString(), gameRoom.getName()));

        boolean result = toys.contains(GAME_CONSOLE);
        String answer = booleanToString(result);

        LOGGER.info(answer);
        LOGGER.info("Sort by price.");

        ToyComparator toyComparator = new ToyComparator(ComparatorParameter.PRICE);
        Collections.sort(toys, toyComparator);
        logList(toys);
    }

    private static void logList(List<Toy> toys) {
        for (Toy toy : toys) {
            LOGGER.info(toy.toString());
        }
    }

    public static String booleanToString(boolean condition) {
        String answer;
        if (condition) {
            answer = "Yes";
        } else {
            answer = "No";
        }

        return answer;
    }

}
