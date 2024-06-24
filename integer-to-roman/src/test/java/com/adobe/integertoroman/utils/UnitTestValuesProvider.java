package com.adobe.integertoroman.utils;

import org.junit.jupiter.params.provider.Arguments;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public final class UnitTestValuesProvider {

    private final static class LongAndShortFormat {
        String shortFormat;
        String longFormat;

        public LongAndShortFormat(String shortFormat, String longFormat) {
            this.shortFormat = shortFormat;
            this.longFormat = longFormat;
        }
    }

    private final static Map<String, LongAndShortFormat> validValuesMap = new HashMap<>();
    static {
        validValuesMap.put("1", new LongAndShortFormat("I", "I"));
        validValuesMap.put("2", new LongAndShortFormat("II", "II"));
        validValuesMap.put("4", new LongAndShortFormat("IV", "IIII"));
        validValuesMap.put("9", new LongAndShortFormat("IX", "VIIII"));
        validValuesMap.put("164", new LongAndShortFormat("CLXIV", "CLXIIII"));
        validValuesMap.put("999", new LongAndShortFormat("CMXCIX", "DCCCCLXXXXVIIII"));
        validValuesMap.put("1200", new LongAndShortFormat("MCC", "MCC"));
        validValuesMap.put("10454", new LongAndShortFormat("X̅CDLIV", "X̅CCCCLIIII"));
        validValuesMap.put("90216", new LongAndShortFormat("X̅C̅CCXVI", "L̅X̅X̅X̅X̅CCXVI"));
        validValuesMap.put("300689", new LongAndShortFormat("C̅C̅C̅DCLXXXIX", "C̅C̅C̅DCLXXXVIIII"));
        validValuesMap.put("66000006", new LongAndShortFormat("L̿X̿V̿M̅VI", "L̿X̿V̿M̅VI"));
        validValuesMap.put("2200000000", new LongAndShortFormat("M̿M̿C̿C̿", "M̿M̿C̿C̿"));
    }

    public static String[] getInvalidIntValues() {
        return new String[] {"0", "-1", "2200000009", "3200000000"};
    }

    public static Stream<Arguments> getValidValuesMapShortFormat() {
        return validValuesMap.entrySet().stream().map(entry -> Arguments.of(entry.getKey(), entry.getValue().shortFormat));
    }

    public static Stream<Arguments> getValidValuesMapLongFormat() {
        return validValuesMap.entrySet().stream().map(entry -> Arguments.of(entry.getKey(), entry.getValue().longFormat));
    }
}
