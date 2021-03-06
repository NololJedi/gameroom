package by.epam.gameroom.entities.toys.educational;

import by.epam.gameroom.run.GameRoomRunner;

import java.util.Objects;

public class RubikCube extends EducationalToy {

    private static final double RUBIK_CUBE_SIZE = 185.0;
    private static final String RUBIK_CUBE_NAME = "Rubik's cube";

    private final boolean isRubikCubeAssembled;

    public RubikCube(double price, boolean isRubikCubeAssembled) {
        super(RUBIK_CUBE_NAME, price, RUBIK_CUBE_SIZE, EducationalToyType.MATHEMATICAL);
        this.isRubikCubeAssembled = isRubikCubeAssembled;
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

        RubikCube rubikCube = (RubikCube) object;

        if (isRubikCubeAssembled != rubikCube.isRubikCubeAssembled) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash += Objects.hashCode(isRubikCubeAssembled);

        return hash;
    }

    @Override
    public String toString() {
        String cubeCondition = GameRoomRunner.booleanToString(isRubikCubeAssembled);
        String result = String.format("%s, is Rubik's cube assembled? - %s.", super.toString(), cubeCondition);

        return result;
    }
}
