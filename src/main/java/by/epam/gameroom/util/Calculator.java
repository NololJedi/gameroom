package by.epam.gameroom.util;

public class Calculator {

    public static double calculateAvailableSum(double currentSum, double toyPrice) {
        if (currentSum == 0){
            throw new IllegalArgumentException("Not enough money.");
        }
        double result = currentSum - toyPrice;

        return result;
    }

}
