package by.epam.gameroom.entities.toys.sport;

public class BasketballSet extends SportToy {

    private static final int BASKETBALL_REQUIRED_SPACE_TO_PLAY = 2500;

    private final int basketHeight;
    private final int ballDiameter;

    public BasketballSet(String basketballSetName, double price, int ballDiameter, int basketHeight) {
        super(basketballSetName, price, ballDiameter * basketHeight, BASKETBALL_REQUIRED_SPACE_TO_PLAY, true);
        this.ballDiameter = ballDiameter;
        this.basketHeight = basketHeight;
    }

    public int getBasketHeight() {
        return basketHeight;
    }

    public int getBallDiameter() {
        return ballDiameter;
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

        BasketballSet testingSet = (BasketballSet) object;

        if (ballDiameter != testingSet.ballDiameter) {
            return false;
        }
        if (basketHeight != testingSet.basketHeight) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash += 31 * basketHeight;
        hash += 31 * ballDiameter;

        return hash;
    }

    @Override
    public String toString() {
        String result = String.format("%s Ball diameter = %d. Basket height = %d.", super.toString(), ballDiameter, basketHeight);

        return result;
    }
}
