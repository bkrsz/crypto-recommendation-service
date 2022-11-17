package com.xm.exercise.cryptorecommendationservice.unit.application.service;

import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoCalculatedValues;
import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoNormalizedRange;
import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoValue;
import com.xm.exercise.cryptorecommendationservice.application.model.Crypto;
import com.xm.exercise.cryptorecommendationservice.application.service.CalculationService;
import com.xm.exercise.cryptorecommendationservice.application.service.FileReaderService;
import com.xm.exercise.cryptorecommendationservice.application.service.RecommendationService;
import com.xm.exercise.cryptorecommendationservice.testUtils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecommendationServiceTest {

    @Mock
    FileReaderService fileReaderService;

    @Mock
    CalculationService calculationService;

    private RecommendationService recommendationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        recommendationService = new RecommendationService(fileReaderService, calculationService);
    }

    @Test
    public void calculateNormalizedRanges() {
        Mockito.when(fileReaderService.readFiles()).thenReturn(TestData.TEST_CRYPTO_LIST);

        List<CryptoNormalizedRange> testCryptoNormalizedRanges = List.of(TestData.TEST_CRYPTO_NORMALIZED_RANGE);
        Mockito.when(calculationService.calculateNormalizedRanges(Mockito.anyList())).thenReturn(testCryptoNormalizedRanges);

        List<CryptoNormalizedRange> cryptoNormalizedRanges = recommendationService.calculateNormalizedRanges();

        assertEquals(1, cryptoNormalizedRanges.size());

        CryptoNormalizedRange range = cryptoNormalizedRanges.get(0);
        assertEquals(TestData.TEST_CRYPTO_NORMALIZED_RANGE.getSymbol(), range.getSymbol());
        assertEquals(TestData.TEST_CRYPTO_NORMALIZED_RANGE.getNormalizedRange(), range.getNormalizedRange());
    }

    @Test
    public void calculateValues() {
        Mockito.when(fileReaderService.readFiles()).thenReturn(TestData.TEST_CRYPTO_LIST);

        CryptoCalculatedValues testValues = new CryptoCalculatedValues("BTC",
                new CryptoValue(LocalDateTime.of(2022, Month.JANUARY, 1, 0, 0), 1000.00),
                new CryptoValue(LocalDateTime.of(2022, Month.JANUARY, 20, 0, 0), 1100.00),
                new CryptoValue(LocalDateTime.of(2022, Month.JANUARY, 10, 0, 0), 900.00),
                new CryptoValue(LocalDateTime.of(2022, Month.JANUARY, 20, 0, 0), 1200.00)
        );
        Mockito.when(calculationService.calculateValues(Mockito.any(Crypto.class))).thenReturn(testValues);

        CryptoCalculatedValues calculatedValues = recommendationService.calculateValues("BTC");

        assertEquals("BTC", calculatedValues.getSymbol());

        CryptoValue oldest = calculatedValues.getOldest();
        CryptoValue newest = calculatedValues.getNewest();
        CryptoValue min = calculatedValues.getMin();
        CryptoValue max = calculatedValues.getMax();

        assertEquals(LocalDateTime.of(2022, Month.JANUARY, 1, 0, 0), oldest.getDateTime());
        assertEquals(LocalDateTime.of(2022, Month.JANUARY, 20, 0, 0), newest.getDateTime());
        assertEquals(LocalDateTime.of(2022, Month.JANUARY, 10, 0, 0), min.getDateTime());
        assertEquals(LocalDateTime.of(2022, Month.JANUARY, 20, 0, 0), max.getDateTime());

        assertEquals(1000.00, oldest.getValue());
        assertEquals(1100.00, newest.getValue());
        assertEquals(900.00, min.getValue());
        assertEquals(1200.00, max.getValue());
    }

    @Test
    public void calculateHighestNormalizedRange() {
        Mockito.when(fileReaderService.readFiles()).thenReturn(TestData.TEST_CRYPTO_LIST);

        Mockito.when(calculationService.calculateHighestNormalizedRange(Mockito.any(), Mockito.any())).thenReturn(TestData.TEST_CRYPTO_NORMALIZED_RANGE);

        CryptoNormalizedRange range = recommendationService.calculateHighestNormalizedRange("2022-01-10");

        assertEquals(TestData.TEST_CRYPTO_NORMALIZED_RANGE.getSymbol(), range.getSymbol());
        assertEquals(TestData.TEST_CRYPTO_NORMALIZED_RANGE.getNormalizedRange(), range.getNormalizedRange());
    }
}
