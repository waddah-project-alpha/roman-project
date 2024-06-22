package com.adobe.integer_to_roman.converter.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.TreeMap;

@Repository
public class IntegerToRomanConverterRepository {

    private final TreeMap<BigInteger, String> originalMap = new TreeMap<>();
    private final TreeMap<BigInteger, String> shortNotationMap = new TreeMap<>();
    private final TreeMap<BigInteger, String> vinculumMap = new TreeMap<>();
    private final TreeMap<BigInteger, String> vinculumShortNotationMap = new TreeMap<>();
    private final TreeMap<BigInteger, String> originalWithShortNotationMap = new TreeMap<>();
    private final TreeMap<BigInteger, String> vinculumWithShortNotationMap = new TreeMap<>();

    @PostConstruct
    private void initialize()
    {
        initializeDefaultMap();
        initializeExtendedMap();
        initializeVinculumMap();
        initializeVinculumExtendedMap();

        originalWithShortNotationMap.putAll(originalMap);
        originalWithShortNotationMap.putAll(shortNotationMap);
        vinculumWithShortNotationMap.putAll(vinculumMap);
        vinculumWithShortNotationMap.putAll(vinculumShortNotationMap);
    }

    private void initializeDefaultMap()
    {
        originalMap.put(new BigInteger("1"), "I");
        originalMap.put(new BigInteger("5"), "V");
        originalMap.put(new BigInteger("10"), "X");
        originalMap.put(new BigInteger("50"), "L");
        originalMap.put(new BigInteger("100"), "C");
        originalMap.put(new BigInteger("500"), "D");
        originalMap.put(new BigInteger("1000"), "M");
    }

    private void initializeExtendedMap()
    {
        shortNotationMap.put(new BigInteger("4"), "IV");
        shortNotationMap.put(new BigInteger("9"), "IX");
        shortNotationMap.put(new BigInteger("40"), "XL");
        shortNotationMap.put(new BigInteger("90"), "XC");
        shortNotationMap.put(new BigInteger("400"), "CD");
        shortNotationMap.put(new BigInteger("900"), "CM");
    }

    private void initializeVinculumMap()
    {
        vinculumMap.putAll(originalMap);
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

    private void initializeVinculumExtendedMap()
    {
        vinculumShortNotationMap.putAll(shortNotationMap);
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

    public TreeMap<BigInteger, String> getMapWithShortNotation()
    {
        return originalWithShortNotationMap;
    }

    public TreeMap<BigInteger, String> getMapWithoutShortNotation()
    {
        return originalMap;
    }

    public TreeMap<BigInteger, String> getVinculumMapWithShortNotation()
    {
        return vinculumWithShortNotationMap;
    }

    public TreeMap<BigInteger, String> getVinculumMapWithoutShortNotation()
    {
        return vinculumMap;
    }
}
