package by.epam.gameroom.util.builders;

import by.epam.gameroom.exceptions.IncorrectValueException;
import by.epam.gameroom.util.LineParser;
import by.epam.gameroom.util.data.DataValidator;

public class ToyParametersProvider {

    private final static int VALUE_PARAMETER_INDEX = 1;

    public String getStringParameter(String[] parameters, int parameterIndex) throws IncorrectValueException {
        inputParametersCheck(parameters, parameterIndex);

        String stringParameter = parameters[parameterIndex];
        String[] parsedParameter = LineParser.parseLine(stringParameter, LineParser.PARAMETER_PARSER_INDICATOR);
        String value = parsedParameter[VALUE_PARAMETER_INDEX];

        return value;
    }

    public double getDoubleParameter(String[] parameters, int parameterIndex) throws IncorrectValueException {
        inputParametersCheck(parameters, parameterIndex);

        String doubleParameter = parameters[parameterIndex];
        String[] parsedParameter = LineParser.parseLine(doubleParameter, LineParser.PARAMETER_PARSER_INDICATOR);
        String parameterValue = parsedParameter[VALUE_PARAMETER_INDEX];
        double value = Double.parseDouble(parameterValue);

        return value;
    }

    public int getIntegerParameter(String[] parameters, int parameterIndex) throws IncorrectValueException {
        inputParametersCheck(parameters, parameterIndex);

        String integerParameter = parameters[parameterIndex];
        String[] parsedParameter = LineParser.parseLine(integerParameter, LineParser.PARAMETER_PARSER_INDICATOR);
        String parameterValue = parsedParameter[VALUE_PARAMETER_INDEX];
        int value = Integer.parseInt(parameterValue);

        return value;
    }

    public boolean getBooleanParameter(String[] parameters, int parameterIndex) throws IncorrectValueException {
        inputParametersCheck(parameters, parameterIndex);

        String isAssembledConditionTrue = "+";
        String isAssembledConditionFalse = "-";

        boolean value = false;
        String booleanParameter = parameters[parameterIndex];
        String[] parsedParameter = LineParser.parseLine(booleanParameter, LineParser.PARAMETER_PARSER_INDICATOR);
        String parameterValue = parsedParameter[VALUE_PARAMETER_INDEX];

        if (parameterValue.equals(isAssembledConditionFalse)) {
            value = false;
        } else if (parameterValue.equals(isAssembledConditionTrue)) {
            value = true;
        }

        return value;
    }

    private void inputParametersCheck(String[] parameters, int parameterIndex) throws IncorrectValueException {
        if (parameters == null || parameters.length == 0) {
            throw new IllegalArgumentException("Check input parameters.");
        }
        if (parameterIndex < 0) {
            throw new IllegalArgumentException("Check input index.");
        }

        DataValidator dataValidator = new DataValidator();

        for (String parameter : parameters) {
            boolean isParameterCorrect = dataValidator.checkParameter(parameter);

            if (!isParameterCorrect) {
                throw new IncorrectValueException(String.format("Incorrect parameter was detected - %s.", parameter));
            }
        }
    }

}
