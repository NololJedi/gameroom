package by.epam.gameroom.entities.toys.sport;

public class JumpRope extends SportToy {

    private static final String JUMP_ROPE_NAME = "Jumping rope";
    private static final int JUMP_ROPE_SIZE_INDEX = 2;
    private static final int JUMP_ROPE_SPACE_INDEX = 3;

    private final int length;

    public JumpRope(double price, int length) {
        super(JUMP_ROPE_NAME, price, length * JUMP_ROPE_SIZE_INDEX, length * JUMP_ROPE_SPACE_INDEX, false);
        this.length = length;
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

        JumpRope jumpRope = (JumpRope) object;

        if (length != jumpRope.length) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash += 31 * length;

        return hash;
    }

    @Override
    public String toString() {
        String result = String.format("%s Jumping rope length = %d.", super.toString(), length);

        return result;
    }
}
