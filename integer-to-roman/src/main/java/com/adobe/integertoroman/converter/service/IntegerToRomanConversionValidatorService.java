package com.adobe.integertoroman.converter.service;

import com.adobe.integertoroman.exceptions.InvalidIntegerToRomanInputException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class IntegerToRomanConversionValidatorService implements InitializingBean {

    @Value("${integer.to.roman.min.value:1}")
    private String MIN_VALUE_STRING;

    @Value("${integer.to.roman.max.value:2200000000}")
    private String MAX_VALUE_STRING;

    private BigInteger MIN_VALUE;
    private BigInteger MAX_VALUE;

    public void validateInput(String numberToValidate)
    {
        if (numberToValidate == null || numberToValidate.isEmpty()) {
            throw new InvalidIntegerToRomanInputException("The number to convert cannot be null or empty");
        }

        BigInteger convertedNumber = null;
        try {
            convertedNumber = new BigInteger(numberToValidate);
        }
        catch (NumberFormatException _ex) {
            throw new InvalidIntegerToRomanInputException(String.format("The value %s is not a valid number", numberToValidate));
        }

        validateInput(convertedNumber);
    }

    public void validateInput(BigInteger numberToValidate) {
        if (numberToValidate == null) {
            throw new InvalidIntegerToRomanInputException("The number to convert cannot be null");
        }

        if (numberToValidate.compareTo(MIN_VALUE) < 0)
        {
            throw new InvalidIntegerToRomanInputException("The number to validate cannot be smaller than " + MIN_VALUE);
        }

        if (numberToValidate.compareTo(MAX_VALUE) > 0)
        {
            throw new InvalidIntegerToRomanInputException("The number to validate cannot be larger than " + MAX_VALUE);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        MIN_VALUE = new BigInteger(MIN_VALUE_STRING);
        MAX_VALUE = new BigInteger(MAX_VALUE_STRING);
    }
}
