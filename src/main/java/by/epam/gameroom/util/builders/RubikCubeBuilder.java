package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.educational.RubikCube;
import by.epam.gameroom.exceptions.IncorrectValueException;

public class RubikCubeBuilder implements ToyBuilder {

    private final static int RUBIK_CUBE_VALID_VALUES_COUNT = 3;
    private final static int priceValueIndex = 1;
    private final static int isAssembledValueIndex = 2;

    public RubikCube createToy(String[] parameters) throws IncorrectValueException {
        if (parameters == null) {
            throw new IllegalArgumentException("Incorrect input parameters.");
        }
        if (parameters.length != RUBIK_CUBE_VALID_VALUES_COUNT) {
            throw new IncorrectValueException(String.format("Incorrect count of parameters. Need - %d, input - %d.",
                    RUBIK_CUBE_VALID_VALUES_COUNT, parameters.length));
        }

        ToyParametersProvider toyParametersProvider = new ToyParametersProvider();
        double price = toyParametersProvider.getDoubleParameter(parameters, priceValueIndex);
        boolean isAssembled = toyParametersProvider.getBooleanParameter(parameters, isAssembledValueIndex);

        return new RubikCube(price, isAssembled);
    }

}
