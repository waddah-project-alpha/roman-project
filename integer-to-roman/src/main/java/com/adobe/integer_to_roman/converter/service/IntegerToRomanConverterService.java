package com.adobe.integer_to_roman.converter.service;

import com.adobe.integer_to_roman.converter.repository.IntegerToRomanConverterRepository;
import com.adobe.integer_to_roman.converter.service.internal.LargeNumberConversionService;
import com.adobe.integer_to_roman.converter.service.internal.SmallNumberConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.TreeMap;

@Service
public class IntegerToRomanConverterService {

    @Autowired
    private SmallNumberConversionService smallNumberConversionService;

    @Autowired
    private LargeNumberConversionService largeNumberConversionService;

    @Autowired
    private IntegerToRomanConverterRepository repository;

    public String convert(BigInteger numberToConvert, boolean useShortNotation) {
        if ( smallNumberConversionService.canConvertNumber(numberToConvert) ) {
            return smallNumberConversionService.convert(numberToConvert, useShortNotation);
        }

        if ( largeNumberConversionService.canConvertNumber(numberToConvert) ) {
            return largeNumberConversionService.convert(numberToConvert, useShortNotation);
        }

        throw new IllegalArgumentException("Invalid number - cannot convert");
    }
}
