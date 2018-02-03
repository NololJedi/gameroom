package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.Toy;

public interface ToyBuilder {

    Toy createToy(String[] parameters);

}
