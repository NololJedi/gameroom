package by.epam.gameroom.run;

import by.epam.gameroom.entities.GameRoom;
import by.epam.gameroom.entities.toys.Toy;

import java.util.List;

public class GameRoomRunner {

    public static void main(String[] args) {

        GameRoomDirector gameRoomDirector = new GameRoomDirector();
        GameRoom gameRoom = gameRoomDirector.buildGameRoom();

        List<Toy> toys = gameRoom.getToys();

     }

}
