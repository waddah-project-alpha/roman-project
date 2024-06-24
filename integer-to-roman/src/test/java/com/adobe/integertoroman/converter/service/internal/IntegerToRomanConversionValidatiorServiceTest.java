package com.adobe.integertoroman.converter.service.internal;

import com.adobe.integertoroman.converter.service.IntegerToRomanConversionValidatorService;
import com.adobe.integertoroman.converter.service.IntegerToRomanConverterService;
import com.adobe.integertoroman.exceptions.InvalidIntegerToRomanInputException;
import com.adobe.integertoroman.utils.PromotheusRegistryMockImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import(PromotheusRegistryMockImplementation.class)
public class IntegerToRomanConversionValidatiorServiceTest {

    @Autowired
    private IntegerToRomanConversionValidatorService service;

    @ParameterizedTest
    @MethodSource("com.adobe.integertoroman.utils.UnitTestValuesProvider#getInvalidIntValues")
    public void testValidatorShouldThrowExceptionForInvalidNumericValues(String value) {
        Assertions.assertThrows(InvalidIntegerToRomanInputException.class,
                () -> service.validateInput(value));
    }

    @NullAndEmptySource
    public void testValidatorShouldThrowExceptionForBlankAndNullValues(String value) {
        Assertions.assertThrows(InvalidIntegerToRomanInputException.class,
                () -> service.validateInput(value));
    }

    @ValueSource(strings = {"test", "12235s", "invalid_value", "S65594"})
    public void testValidatorShouldThrowExceptionForNonNumericValues(String value) {
        Assertions.assertThrows(InvalidIntegerToRomanInputException.class,
                () -> service.validateInput(value));
    }

    @ParameterizedTest
    @MethodSource("com.adobe.integertoroman.utils.UnitTestValuesProvider#getValidValuesMapShortFormat")
    public void validatesValidValues(String value) {
        Assertions.assertDoesNotThrow(() -> service.validateInput(value));
    }
}
