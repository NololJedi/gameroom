package by.epam.gameroom.util.builders;

public class ToyFactory {

    private final static String LEGO_TYPE = "Lego";
    private final static String RUBIK_CUBE_TYPE = "RubikCube";
    private final static String GAME_CONSOLE_TYPE = "GameConsole";
    private final static String RADIO_CAR_TYPE = "RadioCar";
    private final static String BASKET_BALL_SET_TYPE = "BasketBallSet";
    private final static String JUMP_ROPE_TYPE = "JumpRope";

    public ToyBuilder getToyBuilder(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Input type is empty");
        }

        switch (type) {
            case JUMP_ROPE_TYPE: {
                return new JumpRopeBuilder();
            }
            case LEGO_TYPE: {
                return new LegoBuilder();
            }
            case GAME_CONSOLE_TYPE: {
                return new GameConsoleBuilder();
            }
            case BASKET_BALL_SET_TYPE: {
                return new BasketBallSetBuilder();
            }
            case RADIO_CAR_TYPE: {
                return new RadioCarBuilder();
            }
            case RUBIK_CUBE_TYPE: {
                return new RubikCubeBuilder();
            }
            default: {
                throw new IllegalArgumentException(String.format("No such type(%s) was found. Check the input parameter.", type));
            }
        }

    }

}
