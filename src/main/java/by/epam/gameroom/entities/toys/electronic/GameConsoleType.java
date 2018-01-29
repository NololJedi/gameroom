package by.epam.gameroom.entities.toys.electronic;

public enum GameConsoleType {

    XBOX("Xbox 360"), DENDI("Dendi"), PLAYSTATION("Playstation 4");

    private String name;

    GameConsoleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
