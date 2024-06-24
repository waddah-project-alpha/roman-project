package com.adobe.integertoroman.converter.controller;

import com.adobe.integertoroman.exceptions.InvalidIntegerToRomanInputException;
import com.adobe.integertoroman.utils.PromotheusRegistryMockImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import(PromotheusRegistryMockImplementation.class)
public class IntegerToRomanConverterControllerTest {

    @Autowired
    private IntegerToRomanConverterController controller;

    @ParameterizedTest
    @MethodSource("com.adobe.integertoroman.utils.UnitTestValuesProvider#getInvalidIntValues")
    public void testShortNotationShouldThrowExceptionForInvalidNumbers(String value) {
        Assertions.assertThrows(InvalidIntegerToRomanInputException.class,
                () -> controller.convertIntegerToRoman(value, "true"));
    }

    @ParameterizedTest
    @MethodSource("com.adobe.integertoroman.utils.UnitTestValuesProvider#getInvalidIntValues")
    public void testLongNotationShouldThrowExceptionForInvalidNumbers(String value) {
        Assertions.assertThrows(InvalidIntegerToRomanInputException.class,
                () -> controller.convertIntegerToRoman(value, "false"));
    }

    @ValueSource(strings = {"", "test", "12235s", "invalid_value", "S65594"})
    public void testShortNotationShouldThrowExceptionForInvalidStrings(String value) {
        Assertions.assertThrows(InvalidIntegerToRomanInputException.class,
                () -> controller.convertIntegerToRoman(value, "true"));
    }

    @ValueSource(strings = {"", "test", "12235s", "invalid_value", "S65594"})
    public void testLongNotationShouldThrowExceptionForInvalidStrings(String value) {
        Assertions.assertThrows(InvalidIntegerToRomanInputException.class,
                () -> controller.convertIntegerToRoman(value,"false"));
    }

    @ParameterizedTest
    @MethodSource("com.adobe.integertoroman.utils.UnitTestValuesProvider#getValidValuesMapShortFormat")
    public void testShortNotationConvertsValidValues(String integer, String expectedRomanValue) {
        Assertions.assertEquals(expectedRomanValue,
                                controller.convertIntegerToRoman(integer,"true"));
    }

    @ParameterizedTest
    @MethodSource("com.adobe.integertoroman.utils.UnitTestValuesProvider#getValidValuesMapLongFormat")
    public void testLongNotationConvertsValidValues(String integer, String expectedRomanValue) {
        Assertions.assertEquals(expectedRomanValue,
                controller.convertIntegerToRoman(integer,"false"));
    }
}
