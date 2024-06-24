package com.adobe.integertoroman.converter.service.internal;

import com.adobe.integertoroman.converter.repository.IntegerToRomanConverterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.TreeMap;

/**
 * Converts a integer to its Roman notation counterpart using Short notation
 */
@Service
public class ShortNotationConversionService extends BaseConversionService {

    @Autowired
    private IntegerToRomanConverterRepository repository;

    @Override
    protected TreeMap<BigInteger, String> getMap() {
        return repository.getVinculumShortNotation();
    }
}
