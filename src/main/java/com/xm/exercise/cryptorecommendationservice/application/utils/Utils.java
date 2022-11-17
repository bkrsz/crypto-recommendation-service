package com.xm.exercise.cryptorecommendationservice.application.utils;

import com.xm.exercise.cryptorecommendationservice.application.model.CryptoRecord;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final String FILENAME_POSTFIX = "_values";
    private static final String FILENAME_EXT = "csv";
    private static final String PATTERN = "([a-zA-Z0-9]++)" + FILENAME_POSTFIX + "\\." + FILENAME_EXT;
    private static final String CSV_SEPARATOR = ",";

    /**
     * Converts a CSV line to a model
     *
     * @param line A line form a csv file
     * @return Model
     */
    public static CryptoRecord convertLine(String line) {
        String[] data = line.split(CSV_SEPARATOR);

        String timestamp = data[0];
        long timestampLong = Long.parseLong(timestamp);
        Instant instant = Instant.ofEpochMilli(timestampLong);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        String symbol = data[1];

        String value = data[2];
        Double valueDouble = Double.valueOf(value);

        CryptoRecord cryptoRecord = new CryptoRecord(symbol, dateTime, valueDouble);
        return cryptoRecord;
    }

    /**
     * Validates the resource by the filename and returns the symbol of the crypto, if the resource is valid,
     * otherwise returns null
     *
     * @param fileName The file name of the resource
     * @return Symbol of the crypto, or null if the resource is invalid
     */
    public static String validateResource(String fileName) {
        final Pattern pattern = Pattern.compile(PATTERN);
        final Matcher matcher = pattern.matcher(fileName);
        if (matcher.matches()) {
            return matcher.group(1);
        }

        return null;
    }
}
