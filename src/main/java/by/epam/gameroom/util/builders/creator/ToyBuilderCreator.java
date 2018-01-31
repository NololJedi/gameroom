package by.epam.gameroom.util.builders.creator;

import by.epam.gameroom.util.builders.ToyBuilder;

public interface ToyBuilderCreator {

    ToyBuilder getBuilder(String classType);

}
