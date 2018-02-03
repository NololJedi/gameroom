package by.epam.gameroom.util.builders;

import static by.epam.gameroom.entities.toys.educational.Lego.LEGO_TYPE;
import static by.epam.gameroom.entities.toys.educational.RubikCube.RUBIKCUBE_TYPE;
import static by.epam.gameroom.entities.toys.electronic.GameConsole.GAMECONSOLE_TYPE;
import static by.epam.gameroom.entities.toys.electronic.RadioCar.RADIOCAR_TYPE;
import static by.epam.gameroom.entities.toys.sport.BasketballSet.BASKETBALLSET_TYPE;
import static by.epam.gameroom.entities.toys.sport.JumpRope.JUMPROPE_TYPE;

public class ToyFactory {

    public ToyBuilder getToyBuilder(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Input type is empty");
        }

        switch (type) {
            case JUMPROPE_TYPE: {
                return new JumpRopeBuilder();
            }
            case LEGO_TYPE: {
                return new LegoBuilder();
            }
            case GAMECONSOLE_TYPE: {
                return new GameConsoleBuilder();
            }
            case BASKETBALLSET_TYPE: {
                return new BasketBallSetBuilder();
            }
            case RADIOCAR_TYPE: {
                return new RadioCarBuilder();
            }
            case RUBIKCUBE_TYPE: {
                return new RubikCubeBuilder();
            }
            default: {
                throw new IllegalArgumentException(String.format("No such type(%s) was found. Check the input parameter.", type));
            }
        }

    }

}
