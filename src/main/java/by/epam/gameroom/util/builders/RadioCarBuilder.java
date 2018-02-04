package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.electronic.RadioCar;
import by.epam.gameroom.exceptions.IncorrectValueException;

public class RadioCarBuilder implements ToyBuilder {

    private final static int RADIO_CAR__VALID_VALUES_COUNT = 6;
    private final static int nameValueIndex = 1;
    private final static int priceValueIndex = 2;
    private final static int sizeValueIndex = 3;
    private final static int countOfBatteriesValueIndex = 4;
    private final static int maxSpeedValueIndex = 5;

    public RadioCar createToy(String[] parameters) throws IncorrectValueException {
        if (parameters == null) {
            throw new IllegalArgumentException("Incorrect input parameters.");
        }
        if (parameters.length != RADIO_CAR__VALID_VALUES_COUNT) {
            throw new IncorrectValueException(String.format("Incorrect count of parameters. Need - %d, input - %d.",
                    RADIO_CAR__VALID_VALUES_COUNT, parameters.length));
        }

        ToyParametersProvider toyParametersProvider = new ToyParametersProvider();
        String name = toyParametersProvider.getStringParameter(parameters, nameValueIndex);
        double price = toyParametersProvider.getDoubleParameter(parameters, priceValueIndex);
        double size = toyParametersProvider.getDoubleParameter(parameters, sizeValueIndex);
        int countOfBatteries = toyParametersProvider.getIntegerParameter(parameters, countOfBatteriesValueIndex);
        int maxSpeed = toyParametersProvider.getIntegerParameter(parameters, maxSpeedValueIndex);

        return new RadioCar(name, price, size, countOfBatteries, maxSpeed);
    }

}
