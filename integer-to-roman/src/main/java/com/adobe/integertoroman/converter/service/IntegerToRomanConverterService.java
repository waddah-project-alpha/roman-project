package com.adobe.integertoroman.converter.service;

import com.adobe.integertoroman.converter.repository.IntegerToRomanConverterRepository;
import com.adobe.integertoroman.converter.service.internal.LongNotationConversionService;
import com.adobe.integertoroman.converter.service.internal.ShortNotationConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class IntegerToRomanConverterService {

    @Autowired
    private ShortNotationConversionService shortNotationConversionService;

    @Autowired
    private LongNotationConversionService longNotationConversionService;

    @Autowired
    private IntegerToRomanConverterRepository repository;

    public String convert(BigInteger numberToConvert, boolean useShortNotation) {
        if (useShortNotation) {
            return shortNotationConversionService.convert(numberToConvert);
        }
        else
        {
            return longNotationConversionService.convert(numberToConvert);
        }
    }
}
