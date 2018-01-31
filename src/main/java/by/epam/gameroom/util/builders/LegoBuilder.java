package by.epam.gameroom.util.builders;

import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.util.LineParser;

import java.lang.reflect.Method;

public class LegoBuilder extends ToyBuilder {

    private String name;
    private double price;
    private double size;
    private int countOfToyMen;
    private int countOfPeaces;

    public Toy getToy() {
        return null;
    }

    public void buildPart(String value) {

    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    private void setSize(double size) {
        this.size = size;
    }

    private void setCountOfToyMen(int countOfToyMen) {
        this.countOfToyMen = countOfToyMen;
    }

    private void setCountOfPeaces(int countOfPeaces) {
        this.countOfPeaces = countOfPeaces;
    }
}
