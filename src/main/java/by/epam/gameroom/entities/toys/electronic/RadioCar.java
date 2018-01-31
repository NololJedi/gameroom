package by.epam.gameroom.entities.toys.electronic;

import static by.epam.gameroom.entities.toys.AgeLimit.PRE_TEEN;

public class RadioCar extends ElectronicToy {

    private static final int DC_VOLTAGE = 9;

    private final int countOfBatteries;
    private final int maxSpeed;

    public RadioCar(String name, double price, double size, int countOfBatteries, int maxSpeed) {
        super(name, price, PRE_TEEN, size, DC_VOLTAGE, false);
        this.countOfBatteries = countOfBatteries;
        this.maxSpeed = maxSpeed;
    }

    public int getCountOfBatteries() {
        return countOfBatteries;
    }

    public int getMaxSpeed() {
        return maxSpeed;
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

        RadioCar radioCar = (RadioCar) object;

        if (maxSpeed != radioCar.maxSpeed) {
            return false;
        }
        if (countOfBatteries != radioCar.countOfBatteries) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash += 31 * maxSpeed;
        hash += 31 * countOfBatteries;

        return hash;
    }

    @Override
    public String toString() {
        String result = String.format("%s Max car speed - %d. Needed count of batteries - %d.",
                super.toString(), maxSpeed, countOfBatteries);

        return result;
    }
}
