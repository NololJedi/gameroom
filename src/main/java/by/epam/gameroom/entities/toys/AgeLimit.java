package by.epam.gameroom.entities.toys;

public enum AgeLimit {

    CHILD(6), PRE_TEEN(10), TEENAGER(16);

    int limit;

    AgeLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }
}
