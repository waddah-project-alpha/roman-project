package com.adobe.integer_to_roman.converter.controller;

import com.adobe.integer_to_roman.converter.service.IntegerToRomanConverterService;
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

    @GetMapping
    public String convertIntegerToRoman(@RequestParam(name = "query") BigInteger numberToConvert,
                                        @RequestParam(required = false, defaultValue = "true") boolean useShortNotation)
    {
        return integerToRomanConverterService.convert(numberToConvert, useShortNotation);
    }
}
