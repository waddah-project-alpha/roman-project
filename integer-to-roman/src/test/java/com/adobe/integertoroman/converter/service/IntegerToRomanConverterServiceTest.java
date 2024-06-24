package com.adobe.integertoroman.converter.service;

import com.adobe.integertoroman.utils.PromotheusRegistryMockImplementation;
import com.adobe.integertoroman.converter.service.IntegerToRomanConverterService;
import com.adobe.integertoroman.exceptions.InvalidIntegerToRomanInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import(PromotheusRegistryMockImplementation.class)
public class IntegerToRomanConverterServiceTest {

    @Autowired
    private IntegerToRomanConverterService service;

    @ParameterizedTest
    @MethodSource("com.adobe.integertoroman.utils.UnitTestValuesProvider#getInvalidIntValues")
    public void testShortNotationShouldThrowExceptionForInvalidValues(String value) {
        Assertions.assertThrows(InvalidIntegerToRomanInputException.class,
                () -> service.convert(new BigInteger(value), true));
    }

    @ParameterizedTest
    @MethodSource("com.adobe.integertoroman.utils.UnitTestValuesProvider#getInvalidIntValues")
    public void testLongNotationShouldThrowExceptionForInvalidValues(String value) {
        Assertions.assertThrows(InvalidIntegerToRomanInputException.class,
                () -> service.convert(new BigInteger(value), false));
    }

    @ParameterizedTest
    @MethodSource("com.adobe.integertoroman.utils.UnitTestValuesProvider#getValidValuesMapShortFormat")
    public void testShortNotationConvertsValidValues(String integer, String expectedRomanValue) {
        Assertions.assertEquals(expectedRomanValue,
                                service.convert(new BigInteger(integer),true));
    }

    @ParameterizedTest
    @MethodSource("com.adobe.integertoroman.utils.UnitTestValuesProvider#getValidValuesMapLongFormat")
    public void testLongNotationConvertsValidValues(String integer, String expectedRomanValue) {
        Assertions.assertEquals(expectedRomanValue,
                service.convert(new BigInteger(integer),false));
    }
}
