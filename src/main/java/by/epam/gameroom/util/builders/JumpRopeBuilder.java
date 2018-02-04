package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.sport.JumpRope;
import by.epam.gameroom.exceptions.IncorrectValueException;

public class JumpRopeBuilder implements ToyBuilder {

    private final static int JUMP_ROPE_VALID_VALUES_COUNT = 3;
    private final static int priceValueIndex = 1;
    private final static int lengthValueIndex = 2;

    public JumpRope createToy(String[] parameters) throws IncorrectValueException {
        if (parameters == null) {
            throw new IllegalArgumentException("Incorrect input parameters.");
        }
        if (parameters.length != JUMP_ROPE_VALID_VALUES_COUNT) {
            throw new IncorrectValueException(String.format("Incorrect count of parameters. Need - %d, input - %d.",
                    JUMP_ROPE_VALID_VALUES_COUNT, parameters.length));
        }

        ToyParametersProvider toyParametersProvider = new ToyParametersProvider();
        double price = toyParametersProvider.getDoubleParameter(parameters, priceValueIndex);
        int length = toyParametersProvider.getIntegerParameter(parameters, lengthValueIndex);

        return new JumpRope(price, length);
    }

}
