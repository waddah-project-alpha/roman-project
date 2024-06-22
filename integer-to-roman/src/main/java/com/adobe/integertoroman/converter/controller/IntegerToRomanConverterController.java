package com.adobe.integertoroman.converter.controller;

import com.adobe.integertoroman.converter.service.IntegerToRomanConversionValidatorService;
import com.adobe.integertoroman.converter.service.IntegerToRomanConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/romannumeral")
public class IntegerToRomanConverterController {

    @Autowired
    private IntegerToRomanConverterService integerToRomanConverterService;

    @Autowired
    private IntegerToRomanConversionValidatorService integerToRomanConversionValidatorService;

    @GetMapping
    public String convertIntegerToRoman(@RequestParam(name = "query") String numberToConvert,
                                        @RequestParam(required = false, defaultValue = "true") boolean useShortNotation)
    {
        integerToRomanConversionValidatorService.validateInput(numberToConvert);
        return integerToRomanConverterService.convert(new BigInteger(numberToConvert), useShortNotation);
    }
}
