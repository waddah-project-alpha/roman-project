package com.adobe.integertoroman.converter.service.internal;

import com.adobe.integertoroman.converter.service.IntegerToRomanConversionValidatorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.TreeMap;

public abstract class BaseConversionService {

    @Autowired
    private IntegerToRomanConversionValidatorService integerToRomanConversionValidatorService;

    protected abstract TreeMap<BigInteger, String> getMap();

    public String convert(BigInteger numberToConvert) {
        integerToRomanConversionValidatorService.validateInput(numberToConvert);
        return convertToRoman(numberToConvert, getMap());
    }

    private String convertToRoman(BigInteger numberToConvert, TreeMap<BigInteger, String> map) {
        if ( numberToConvert.compareTo(BigInteger.ZERO) == 0 ) {
            return "";
        }
        BigInteger floor = map.floorKey(numberToConvert);
        return map.get(floor) + convertToRoman(numberToConvert.subtract(floor), map);
    }
}
