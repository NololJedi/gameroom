package by.epam.gameroom.comparator;

import by.epam.gameroom.entities.toys.AgeLimit;
import by.epam.gameroom.entities.toys.Toy;
import by.epam.gameroom.entities.toys.ToyType;

import java.util.Comparator;

public class ToyComparator implements Comparator<Toy> {
    private ComparatorParameter comparatorParameter;

    public ToyComparator(ComparatorParameter comparatorParameter) {
        setComparatorParameter(comparatorParameter);
    }

    private void setComparatorParameter(ComparatorParameter comparatorParameter) {
        if (comparatorParameter == null) {
            throw new IllegalArgumentException("Empty parameter.");
        }

        this.comparatorParameter = comparatorParameter;
    }

    public int compare(Toy first, Toy second) {
        switch (comparatorParameter) {
            case NAME: {
                String firstToyName = first.getName();
                String secondToyName = second.getName();
                int sortingResult = firstToyName.compareTo(secondToyName);

                return sortingResult;
            }
            case SIZE: {
                double firstToySize = first.getToySize();
                double secondToySize = second.getToySize();
                int sortingResult = (int) (firstToySize - secondToySize);

                return sortingResult;
            }
            case PRICE: {
                double firstToyPrice = first.getPrice();
                double secondToyPrice = second.getPrice();
                int sortingResult = (int) (firstToyPrice - secondToyPrice);

                return sortingResult;
            }
            case AGE_LIMIT: {
                AgeLimit firstToyAgeLimit = first.getAgeLimit();
                AgeLimit secondToyAgelimit = second.getAgeLimit();

                int firstLimit = firstToyAgeLimit.getLimit();
                int secondLimit = secondToyAgelimit.getLimit();
                int sortingResult = firstLimit - secondLimit;

                return sortingResult;
            }
            case TYPE: {
                ToyType firstToyType = first.getToyType();
                ToyType secondToyType = second.getToyType();

                int sortingResult = firstToyType.compareTo(secondToyType);

                return sortingResult;
            }
            default: {
                throw new EnumConstantNotPresentException(ComparatorParameter.class, comparatorParameter.name());
            }
        }

    }
}
