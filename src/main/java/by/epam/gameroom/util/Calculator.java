package by.epam.gameroom.util;

public class Calculator {

    public static double calculate(double first, double second) {
        double result = first - second;

        if (first <= 0){
            throw new IllegalArgumentException("Not enough resources for creating room.");
        }

        return result;
    }

}
