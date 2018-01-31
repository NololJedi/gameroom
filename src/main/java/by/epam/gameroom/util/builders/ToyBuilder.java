package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.Toy;

public abstract class ToyBuilder {

    public abstract Toy getToy();

    public abstract void buildPart(String value);

}
