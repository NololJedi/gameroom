package by.epam.gameroom.entities;

import by.epam.gameroom.entities.toys.Toy;

import java.util.List;

public class GameRoom {

    private final List<Toy> toys;
    private final double area;
    private final String name;

    public GameRoom(List<Toy> toys, double area, String name) {
        this.toys = toys;
        this.area = area;
        this.name = name;
    }

    public List<Toy> getToys() {
        return toys;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        GameRoom gameRoom = (GameRoom) object;
        List<Toy> objectToys = gameRoom.toys;

        if (area != gameRoom.area) {
            return false;
        }
        if (!name.equals(gameRoom.name)) {
            return false;
        }
        if (toys.size() != objectToys.size()) {
            return false;
        }

        for (int listIndex = 0; listIndex < toys.size(); listIndex++) {
            Toy currentObjectToy = toys.get(listIndex);
            Toy testingObjectToy = objectToys.get(listIndex);

            if (!currentObjectToy.equals(testingObjectToy)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = (int) (31 * area);
        hash += name.hashCode();
        for (Toy toy : toys) {
            hash += toy.hashCode();
        }

        return hash;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Game room : %s. ", name));
        stringBuilder.append(String.format("Area = %f. ", area));
        stringBuilder.append("Toys :");
        for (Toy toy : toys) {
            stringBuilder.append(" " + toy.toString());
        }
        stringBuilder.append(".");
        String result = stringBuilder.toString();

        return result;
    }
}
