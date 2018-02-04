package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.electronic.GameConsole;
import by.epam.gameroom.exceptions.IncorrectValueException;

public class GameConsoleBuilder implements ToyBuilder {

    private final static int GAME_CONSOLE_VALID_PARAMETERS_COUNT = 5;
    private final static int nameValueIndex = 1;
    private final static int priceValueIndex = 2;
    private final static int sizeValueIndex = 3;
    private final static int isInternetAvailableValueIndex = 4;

    public GameConsole createToy(String[] parameters) throws IncorrectValueException {
        if (parameters == null) {
            throw new IllegalArgumentException("Incorrect input parameters.");
        }
        if (parameters.length != GAME_CONSOLE_VALID_PARAMETERS_COUNT) {
            throw new IncorrectValueException(String.format("Incorrect count of parameters. Need - %d, input - %d.",
                    GAME_CONSOLE_VALID_PARAMETERS_COUNT, parameters.length));
        }

        ToyParametersProvider toyParametersProvider = new ToyParametersProvider();
        String name = toyParametersProvider.getStringParameter(parameters, nameValueIndex);
        double price = toyParametersProvider.getDoubleParameter(parameters, priceValueIndex);
        double size = toyParametersProvider.getDoubleParameter(parameters, sizeValueIndex);
        boolean isInternetAvailable = toyParametersProvider.getBooleanParameter(parameters, isInternetAvailableValueIndex);

        return new GameConsole(name, price, size, isInternetAvailable);
    }

}
