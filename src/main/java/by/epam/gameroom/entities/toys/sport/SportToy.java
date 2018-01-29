package by.epam.gameroom.entities.toys.sport;

import by.epam.gameroom.entities.toys.AgeLimit;
import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.entities.toys.ToyType;

import java.util.Objects;

public class SportToy extends Toy {

    private final int requiredSpaceToPlay;
    private final boolean isMultiPlaying;

    public SportToy(String name, double price, double size, int requiredSpaceToPlay, boolean isMultiPlaying) {
        super(name, price, AgeLimit.CHILD, size, ToyType.SPORT);
        this.requiredSpaceToPlay = requiredSpaceToPlay;
        this.isMultiPlaying = isMultiPlaying;
    }

    public int getRequiredSpaceToPlay() {
        return requiredSpaceToPlay;
    }

    public boolean isMultiPlaying() {
        return isMultiPlaying;
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
        SportToy sportToy = (SportToy) object;

        if (requiredSpaceToPlay != sportToy.requiredSpaceToPlay) {
            return false;
        }
        if (isMultiPlaying != sportToy.isMultiPlaying) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash += 31 * requiredSpaceToPlay;
        hash += Objects.hashCode(isMultiPlaying);

        return hash;
    }

    @Override
    public String toString() {
        String multiPlaying;
        if (isMultiPlaying) {
            multiPlaying = "Yes";
        } else {
            multiPlaying = "No";
        }

        String result = String.format("%s Required space to play - %d. Is multi playing - %s.",
                super.toString(), requiredSpaceToPlay, multiPlaying);

        return result;
    }
}
