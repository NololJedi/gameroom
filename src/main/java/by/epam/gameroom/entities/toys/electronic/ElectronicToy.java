package by.epam.gameroom.entities.toys.electronic;

import by.epam.gameroom.entities.toys.AgeLimit;
import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.entities.toys.ToyType;

import java.util.Objects;

public class ElectronicToy extends Toy {

    private final int operatingVoltage;
    private final boolean isPowerSocketNeeded;

    public ElectronicToy(String name, double price, AgeLimit ageLimit, double toySize, int operatingVoltage, boolean isPowerSocketNeeded) {
        super(name, price, ageLimit, toySize, ToyType.ELECTRONIC);
        this.operatingVoltage = operatingVoltage;
        this.isPowerSocketNeeded = isPowerSocketNeeded;
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
        ElectronicToy that = (ElectronicToy) object;

        if (operatingVoltage != that.operatingVoltage) {
            return false;
        }
        if (isPowerSocketNeeded != that.isPowerSocketNeeded) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash += 31 * operatingVoltage;
        hash += Objects.hashCode(isPowerSocketNeeded);

        return hash;
    }

    @Override
    public String toString() {
        String powerNeed;
        if (isPowerSocketNeeded) {
            powerNeed = "Yes";
        } else {
            powerNeed = "No";
        }

        String result = String.format("%s Operation voltage - %d. Power socket needed - %s",
                super.toString(), operatingVoltage, powerNeed);

        return result;
    }
}
