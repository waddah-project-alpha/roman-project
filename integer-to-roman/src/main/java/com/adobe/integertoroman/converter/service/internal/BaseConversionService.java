package com.adobe.integertoroman.converter.service.internal;

import com.adobe.integertoroman.converter.service.IntegerToRomanConversionValidatorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.TreeMap;

public abstract class BaseConversionService {

    @Autowired
    private IntegerToRomanConversionValidatorService integerToRomanConversionValidatorService;

    /**
     * Returns the map of integer to String roman representation which is used to compute the result. Note that this
     * allows us to compute both short and long notations, based on which values are present in the map
     * @return
     */
    protected abstract TreeMap<BigInteger, String> getMap();

    /**
     * Converts an integer passed in parameter to its Roman notation, based on the map which the descendant class returns
     * @param numberToConvert
     * @return
     */
    public String convert(BigInteger numberToConvert) {
        integerToRomanConversionValidatorService.validateInput(numberToConvert);
        return convertToRoman(numberToConvert, getMap());
    }

    /**
     * Handles converting an integer to its Roman notation. The logic to execute is simple: As long as the input is
     * not 0, find the smallest number in the integer->roman map which is smaller or equals to the input, append its
     * value to the result and substract it from the original input. The recursively call the method on the result.
     * Ex:
     * convertToRoman(3)
     * -> map.floor(3) = 1: "I" -> "I" + convertToRoman(2)
     * -> map.floor(2) = 1: "I" -> "I" + "I" + convertToRoman(1)
     * -> map.floor(1) = 1: "I" -> "I" + "I" + "I" + convertToRoman(0)
     * -> return "III"
     * @param numberToConvert
     * @param map
     * @return
     */
    private String convertToRoman(BigInteger numberToConvert, TreeMap<BigInteger, String> map) {
        if ( numberToConvert.compareTo(BigInteger.ZERO) == 0 ) {
            return "";
        }
        BigInteger floor = map.floorKey(numberToConvert);
        return map.get(floor) + convertToRoman(numberToConvert.subtract(floor), map);
    }
}
