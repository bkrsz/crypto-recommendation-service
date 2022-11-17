package com.xm.exercise.cryptorecommendationservice.unit.application.service;

import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoCalculatedValues;
import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoNormalizedRange;
import com.xm.exercise.cryptorecommendationservice.application.model.Crypto;
import com.xm.exercise.cryptorecommendationservice.application.service.CalculationService;
import com.xm.exercise.cryptorecommendationservice.testUtils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationServiceTest {

    private CalculationService calculationService;

    @BeforeEach
    public void setUp() {
        calculationService = new CalculationService();
    }

    @Test
    public void calculateNormalizedRanges() {
        List<CryptoNormalizedRange> cryptoNormalizedRanges = calculationService.calculateNormalizedRanges(TestData.TEST_CRYPTO_LIST);

        CryptoNormalizedRange first = cryptoNormalizedRanges.get(0);
        CryptoNormalizedRange second = cryptoNormalizedRanges.get(1);
        assertEquals("LTC", first.getSymbol());
        assertEquals(0.25, first.getNormalizedRange());
        assertEquals("BTC", second.getSymbol());
        assertEquals(0.15, second.getNormalizedRange());
    }

    @Test
    public void calculateValues() {
        Crypto crypto = new Crypto("BTC", TestData.TEST_CRYPTO_RECORD_LIST_1);

        CryptoCalculatedValues cryptoCalculatedValues = calculationService.calculateValues(crypto);

        assertEquals("BTC", cryptoCalculatedValues.getSymbol());

        assertEquals(20000.00, cryptoCalculatedValues.getOldest().getValue());
        assertEquals(23000.00, cryptoCalculatedValues.getNewest().getValue());
        assertEquals(20000.00, cryptoCalculatedValues.getMin().getValue());
        assertEquals(23000.00, cryptoCalculatedValues.getMax().getValue());

        assertEquals(LocalDateTime.of(2022, Month.JANUARY, 1, 0, 0), cryptoCalculatedValues.getOldest().getDateTime());
        assertEquals(LocalDateTime.of(2022, Month.JANUARY, 1, 3, 0), cryptoCalculatedValues.getNewest().getDateTime());
        assertEquals(LocalDateTime.of(2022, Month.JANUARY, 1, 0, 0), cryptoCalculatedValues.getMin().getDateTime());
        assertEquals(LocalDateTime.of(2022, Month.JANUARY, 1, 3, 0), cryptoCalculatedValues.getMax().getDateTime());
    }

    @Test
    public void calculateHighestNormalizedRange() {
        CryptoNormalizedRange cryptoNormalizedRange = calculationService
                .calculateHighestNormalizedRange(TestData.TEST_CRYPTO_LIST, LocalDate.of(2022, Month.JANUARY, 1));

        assertEquals("LTC", cryptoNormalizedRange.getSymbol());
        assertEquals(0.25, cryptoNormalizedRange.getNormalizedRange());
    }
}
