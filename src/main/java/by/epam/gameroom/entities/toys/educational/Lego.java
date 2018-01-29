package by.epam.gameroom.entities.toys.educational;

public class Lego extends EducationalToy {

    private final int countOfPeaces;
    private final int countOfToyMen;

    public Lego(String nameOfSet, double price, double boxSize, int countOfToyMen, int countOfPeaces) {
        super(nameOfSet, price, boxSize, EducationalToyType.CONSTRUCTOR);
        this.countOfToyMen = countOfToyMen;
        this.countOfPeaces = countOfPeaces;
    }

    public int getCountOfPeaces() {
        return countOfPeaces;
    }

    public int getCountOfToyMen() {
        return countOfToyMen;
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

        Lego lego = (Lego) object;

        if (countOfPeaces != lego.countOfPeaces) {
            return false;
        }
        if (countOfToyMen != lego.countOfToyMen) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash += 31 * countOfPeaces;
        hash += 31 * countOfToyMen;

        return hash;
    }

    @Override
    public String toString() {
        String result = String.format("%s Peaces = %d, toy men count = %d", super.toString(), countOfPeaces, countOfToyMen);

        return result;
    }
}
