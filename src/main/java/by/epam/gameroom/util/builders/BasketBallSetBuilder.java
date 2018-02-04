package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.sport.BasketballSet;
import by.epam.gameroom.exceptions.IncorrectValueException;

public class BasketBallSetBuilder implements ToyBuilder {

    private final static int BASKET_BALL_SET__VALID_PARAMETERS_COUNT = 5;
    private final static int nameValueIndex = 1;
    private final static int priceValueIndex = 2;
    private final static int ballDiameterValueIndex = 3;
    private final static int basketHeightValueIndex = 4;

    public BasketballSet createToy(String[] parameters) throws IncorrectValueException {
        if (parameters == null) {
            throw new IllegalArgumentException("Incorrect input parameters.");
        }
        if (parameters.length != BASKET_BALL_SET__VALID_PARAMETERS_COUNT) {
            throw new IncorrectValueException(String.format("Incorrect count of parameters. Need - %d, input - %d.",
                    BASKET_BALL_SET__VALID_PARAMETERS_COUNT, parameters.length));
        }

        ToyParametersProvider toyParametersProvider = new ToyParametersProvider();
        String name = toyParametersProvider.getStringParameter(parameters, nameValueIndex);
        double price = toyParametersProvider.getDoubleParameter(parameters, priceValueIndex);
        int ballDiameter = toyParametersProvider.getIntegerParameter(parameters, ballDiameterValueIndex);
        int basketHeight = toyParametersProvider.getIntegerParameter(parameters, basketHeightValueIndex);

        return new BasketballSet(name, price, ballDiameter, basketHeight);
    }

}
