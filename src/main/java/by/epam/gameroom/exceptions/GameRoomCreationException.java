package by.epam.gameroom.exceptions;

public class GameRoomCreationException extends Exception {

    public GameRoomCreationException() {
    }

    public GameRoomCreationException(String message) {
        super(message);
    }

    public GameRoomCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameRoomCreationException(Throwable cause) {
        super(cause);
    }
}
