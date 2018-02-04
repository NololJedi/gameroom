package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.educational.Lego;
import by.epam.gameroom.exceptions.IncorrectValueException;

public class LegoBuilder implements ToyBuilder {


    private final static int LEGO_VALID_VALUES_COUNT = 6;
    private final static int nameValueIndex = 1;
    private final static int priceValueIndex = 2;
    private final static int sizeValueIndex = 3;
    private final static int countOfToyMenValueIndex = 4;
    private final static int countOfPeacesValueIndex = 5;

    public Lego createToy(String[] parameters) throws IncorrectValueException {
        if (parameters == null) {
            throw new IllegalArgumentException("Incorrect input parameters.");
        }
        if (parameters.length != LEGO_VALID_VALUES_COUNT) {
            throw new IncorrectValueException(String.format("Incorrect count of parameters. Need - %d, input - %d.",
                    LEGO_VALID_VALUES_COUNT, parameters.length));
        }

        ToyParametersProvider toyParametersProvider = new ToyParametersProvider();
        String name = toyParametersProvider.getStringParameter(parameters, nameValueIndex);
        double price = toyParametersProvider.getDoubleParameter(parameters, priceValueIndex);
        double size = toyParametersProvider.getDoubleParameter(parameters, sizeValueIndex);
        int countOfToyMen = toyParametersProvider.getIntegerParameter(parameters, countOfToyMenValueIndex);
        int countOfPeaces = toyParametersProvider.getIntegerParameter(parameters, countOfPeacesValueIndex);

        return new Lego(name, price, size, countOfToyMen, countOfPeaces);
    }

}
