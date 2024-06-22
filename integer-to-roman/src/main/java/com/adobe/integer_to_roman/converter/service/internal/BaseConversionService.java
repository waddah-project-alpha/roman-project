package com.adobe.integer_to_roman.converter.service.internal;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.TreeMap;

@Service
public abstract class BaseConversionService {

    protected abstract TreeMap<BigInteger, String> getMap(boolean useShortNotation);
    protected abstract BigInteger getThreshold();

    public boolean canConvertNumber(BigInteger numberToConvert)
    {
        return numberToConvert.compareTo(getThreshold()) < 0;
    }

    public String convert(BigInteger numberToConvert, boolean useShortNotation)
    {
        return convertToRoman(numberToConvert, getMap(useShortNotation));
    }

    private String convertToRoman(BigInteger numberToConvert, TreeMap<BigInteger, String> map) {
        if (numberToConvert.compareTo(BigInteger.ZERO) == 0) {
            return "";
        }
        BigInteger floor = map.floorKey(numberToConvert);
        return map.get(floor) + convertToRoman(numberToConvert.subtract(floor), map);
    }
}
