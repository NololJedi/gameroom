package by.epam.gameroom.entities.toys;

public abstract class Toy {

    private final String name;
    private final double price;
    private final double size;
    private final AgeLimit ageLimit;
    private final ToyType toyType;

    public Toy(String name, double price, AgeLimit ageLimit, double size, ToyType toyType) {
        this.name = name;
        this.price = price;
        this.ageLimit = ageLimit;
        this.size = size;
        this.toyType = toyType;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public AgeLimit getAgeLimit() {
        return ageLimit;
    }

    public double getSize() {
        return size;
    }

    public ToyType getToyType() {
        return toyType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Toy toy = (Toy) object;

        if (!name.equals(toy.name)) {
            return false;
        }
        if (price != toy.price) {
            return false;
        }
        if (ageLimit != toy.ageLimit) {
            return false;
        }
        if (size != toy.size) {
            return false;
        }
        if (toyType != toy.toyType) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = (int) (31 * price);
        hash += name.hashCode();
        hash += ageLimit.hashCode();
        hash += (int) (31 * size);
        hash += toyType.hashCode();

        return hash;
    }

    @Override
    public String toString() {
        String result = String.format("Toy name - %s, price - %.2f, age limit - %s, size - %.1f, type - %s.",
                name, price, ageLimit.name(), size, toyType.name());

        return result;
    }
}
