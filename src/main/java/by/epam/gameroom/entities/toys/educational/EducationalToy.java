package by.epam.gameroom.entities.toys.educational;

import by.epam.gameroom.entities.toys.AgeLimit;
import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.entities.toys.ToyType;

public abstract class EducationalToy extends Toy {

    private final EducationalToyType educationalToyType;

    public EducationalToy(String name, double price, double toySize, EducationalToyType educationalToyType) {
        super(name, price, AgeLimit.CHILD, toySize, ToyType.EDUCATIONAL);
        this.educationalToyType = educationalToyType;
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

        EducationalToy that = (EducationalToy) object;
        return educationalToyType == that.educationalToyType;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash += educationalToyType.hashCode();

        return hash;
    }

    @Override
    public String toString() {
        String result = String.format("%s Educational type - %s.", super.toString(), educationalToyType.name());

        return result;
    }
}
