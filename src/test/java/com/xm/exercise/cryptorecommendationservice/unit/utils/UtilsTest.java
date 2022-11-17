package com.xm.exercise.cryptorecommendationservice.unit.utils;

import com.xm.exercise.cryptorecommendationservice.application.model.CryptoRecord;
import com.xm.exercise.cryptorecommendationservice.application.utils.Utils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UtilsTest {

    @Test
    public void testConvertLine() {
        String line = "1641553200000,BTC,42172.84";

        CryptoRecord result = Utils.convertLine(line);

        assertEquals(LocalDateTime.of(2022, Month.JANUARY, 7, 12, 0, 0), result.getDateTime());
        assertEquals("BTC", result.getSymbol());
        assertEquals(42172.84, result.getPrice());
    }

    @Test
    public void testValidateResourceSuccessfully() {
        String fileName = "LTC_values.csv";

        String result = Utils.validateResource(fileName);

        assertEquals("LTC", result);
    }

    @Test
    public void testValidateResourceWithWrongFileName() {
        String fileName = "LTC_val.csv";

        String result = Utils.validateResource(fileName);

        assertNull(result);
    }

    @Test
    public void testValidateResourceWithWrongExtension() {
        String fileName = "LTC_values.txt";

        String result = Utils.validateResource(fileName);

        assertNull(result);
    }
}
