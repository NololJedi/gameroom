package by.epam.gameroom.entities.toys.electronic;

import by.epam.gameroom.entities.toys.AgeLimit;

import java.util.Objects;

public class GameConsole extends ElectronicToy {

    private static final int AC_VOLTAGE = 220;

    private final boolean isInternetConnectionAvailable;

    public GameConsole(String modelName, double price, double size, boolean isInternetConnectionAvailable) {
        super(modelName, price, AgeLimit.TEENAGER, size, AC_VOLTAGE, true);
        this.isInternetConnectionAvailable = isInternetConnectionAvailable;
    }

    public boolean isInternetConnectionAvailable() {
        return isInternetConnectionAvailable;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }

        GameConsole testingConsole = (GameConsole) object;

        if (isInternetConnectionAvailable != testingConsole.isInternetConnectionAvailable) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash += Objects.hashCode(isInternetConnectionAvailable);

        return hash;
    }

    @Override
    public String toString() {
        String internetConnection;

        if (isInternetConnectionAvailable) {
            internetConnection = "Yes";
        } else {
            internetConnection = "No";
        }

        String result = String.format("%s Can connect to Internet - %s.", internetConnection);

        return result;
    }
}
