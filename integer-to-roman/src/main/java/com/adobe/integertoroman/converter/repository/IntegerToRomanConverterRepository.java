package com.adobe.integertoroman.converter.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.TreeMap;

@Repository
public class IntegerToRomanConverterRepository {

    private final TreeMap<BigInteger, String> vinculumMap = new TreeMap<>();
    private final TreeMap<BigInteger, String> vinculumShortNotationMap = new TreeMap<>();

    @PostConstruct
    private void initialize()
    {
        initializeVinculumMap();
        initializeVinculumShortNotationMap();
    }

    private void initializeVinculumMap()
    {
        vinculumMap.put(new BigInteger("1"), "I");
        vinculumMap.put(new BigInteger("5"), "V");
        vinculumMap.put(new BigInteger("10"), "X");
        vinculumMap.put(new BigInteger("50"), "L");
        vinculumMap.put(new BigInteger("100"), "C");
        vinculumMap.put(new BigInteger("500"), "D");
        vinculumMap.put(new BigInteger("1000"), "M");
        vinculumMap.put(new BigInteger("5000"), "V̅");
        vinculumMap.put(new BigInteger("10000"), "X̅");
        vinculumMap.put(new BigInteger("50000"), "L̅");
        vinculumMap.put(new BigInteger("100000"), "C̅");
        vinculumMap.put(new BigInteger("500000"), "D̅");
        vinculumMap.put(new BigInteger("1000000"), "M̅");
        vinculumMap.put(new BigInteger("5000000"), "V̿");
        vinculumMap.put(new BigInteger("10000000"), "X̿");
        vinculumMap.put(new BigInteger("50000000"), "L̿");
        vinculumMap.put(new BigInteger("100000000"), "C̿");
        vinculumMap.put(new BigInteger("500000000"), "D̿");
        vinculumMap.put(new BigInteger("1000000000"), "M̿");
    }

    private void initializeVinculumShortNotationMap()
    {
        vinculumShortNotationMap.putAll(vinculumMap);
        vinculumShortNotationMap.put(new BigInteger("4"), "IV");
        vinculumShortNotationMap.put(new BigInteger("9"), "IX");
        vinculumShortNotationMap.put(new BigInteger("40"), "XL");
        vinculumShortNotationMap.put(new BigInteger("90"), "XC");
        vinculumShortNotationMap.put(new BigInteger("400"), "CD");
        vinculumShortNotationMap.put(new BigInteger("900"), "CM");
        vinculumShortNotationMap.put(new BigInteger("4000"), "I̅V̅");
        vinculumShortNotationMap.put(new BigInteger("9000"), "I̅X̅");
        vinculumShortNotationMap.put(new BigInteger("40000"), "X̅L̅");
        vinculumShortNotationMap.put(new BigInteger("90000"), "X̅C̅");
        vinculumShortNotationMap.put(new BigInteger("400000"), "C̅D̅");
        vinculumShortNotationMap.put(new BigInteger("900000"), "C̅M̅");
        vinculumShortNotationMap.put(new BigInteger("4000000"), "I̿V̿");
        vinculumShortNotationMap.put(new BigInteger("9000000"), "I̿X̿");
        vinculumShortNotationMap.put(new BigInteger("40000000"), "X̿L̿");
        vinculumShortNotationMap.put(new BigInteger("90000000"), "X̿C̿");
        vinculumShortNotationMap.put(new BigInteger("400000000"), "C̿D̿");
        vinculumShortNotationMap.put(new BigInteger("900000000"), "C̿M̿");
    }

    public TreeMap<BigInteger, String> getVinculumShortNotation()
    {
        return vinculumShortNotationMap;
    }

    public TreeMap<BigInteger, String> getVinculumLongNotation()
    {
        return vinculumMap;
    }
}
