package com.adobe.integertoroman.converter.controller;

import com.adobe.integertoroman.converter.service.IntegerToRomanConversionValidatorService;
import com.adobe.integertoroman.converter.service.IntegerToRomanConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

/**
 * This is the main controller for our app. This handles converting a supposed integer value to its roman representation
 */
@RestController
@RequestMapping("/romannumeral")
public class IntegerToRomanConverterController {

    @Autowired
    private IntegerToRomanConverterService integerToRomanConverterService;

    @Autowired
    private IntegerToRomanConversionValidatorService integerToRomanConversionValidatorService;

    /**
     * Converts a string value provided by the user to its roman representation. It supports both long and short
     * notations (for exp. IV and IIII), but defaults to the short notation, which is more widely used.
     * @param numberToConvert
     * @param useShortNotation
     * @return the roman value representation
     */
    @GetMapping
    public String convertIntegerToRoman(@RequestParam(name = "query") String numberToConvert,
                                        @RequestParam(required = false, defaultValue = "true") boolean useShortNotation) {
        integerToRomanConversionValidatorService.validateInput(numberToConvert);
        return integerToRomanConverterService.convert(new BigInteger(numberToConvert), useShortNotation);
    }
}
