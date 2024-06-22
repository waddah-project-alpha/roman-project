package com.adobe.integer_to_roman.converter.service.internal;

import com.adobe.integer_to_roman.converter.repository.IntegerToRomanConverterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.TreeMap;

@Service
public class LongNotationConversionService extends BaseConversionService{

    @Autowired
    private IntegerToRomanConverterRepository repository;

    @Override
    protected TreeMap<BigInteger, String> getMap() {
        return repository.getVinculumLongNotation();
    }
}
