package com.adobe.integertoroman.converter.service;

import com.adobe.integertoroman.converter.repository.IntegerToRomanConverterRepository;
import com.adobe.integertoroman.converter.service.internal.LongNotationConversionService;
import com.adobe.integertoroman.converter.service.internal.ShortNotationConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * This service handles converting a BigInteger value into its Roman counterpart, if possible. It supports both long
 * and short notation representations
 */
@Service
public class IntegerToRomanConverterService {

    @Autowired
    private ShortNotationConversionService shortNotationConversionService;

    @Autowired
    private LongNotationConversionService longNotationConversionService;

    @Autowired
    private IntegerToRomanConverterRepository repository;

    /**
     * Converts a BigInteger value passed as parameter to its Roman value counterpart. The returned value could be
     * in short or long notation, based on the parameter
     * @param numberToConvert
     * @param useShortNotation
     * @return Roman notation representation of the input value
     */
    public String convert(BigInteger numberToConvert, boolean useShortNotation) {
        if ( useShortNotation ) {
            return shortNotationConversionService.convert(numberToConvert);
        } else {
            return longNotationConversionService.convert(numberToConvert);
        }
    }
}
