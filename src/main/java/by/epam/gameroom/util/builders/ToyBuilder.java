package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.exceptions.IncorrectValueException;

public interface ToyBuilder {

    Toy createToy(String[] parameters) throws IncorrectValueException;

}
