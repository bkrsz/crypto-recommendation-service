package com.xm.exercise.cryptorecommendationservice.unit.api.controller;

import com.xm.exercise.cryptorecommendationservice.api.controller.RecommendationController;
import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoCalculatedValues;
import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoNormalizedRange;
import com.xm.exercise.cryptorecommendationservice.application.service.RecommendationService;
import com.xm.exercise.cryptorecommendationservice.testUtils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecommendationControllerTest {

    @Mock
    private RecommendationService recommendationService;

    private RecommendationController recommendationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        recommendationController = new RecommendationController(recommendationService);
    }

    @Test
    public void getAllCryptosNormalized() {
        List<CryptoNormalizedRange> testCryptoNormalizedRanges = List.of(TestData.TEST_CRYPTO_NORMALIZED_RANGE);
        Mockito.when(recommendationService.calculateNormalizedRanges()).thenReturn(testCryptoNormalizedRanges);

        List<CryptoNormalizedRange> cryptoNormalizedRanges = recommendationController.getAllCryptosNormalized();

        assertEquals(1, cryptoNormalizedRanges.size());

        CryptoNormalizedRange range = cryptoNormalizedRanges.get(0);
        assertEquals(TestData.TEST_CRYPTO_NORMALIZED_RANGE.getSymbol(), range.getSymbol());
        assertEquals(TestData.TEST_CRYPTO_NORMALIZED_RANGE.getNormalizedRange(), range.getNormalizedRange());
    }

    @Test
    public void getCryptoValues() {
        Mockito.when(recommendationService.calculateValues(Mockito.anyString())).thenReturn(TestData.TEST_CRYPTO_CALCULATED_VALUES);

        CryptoCalculatedValues calculatedValues = recommendationController.getCryptoValues("LTC");
    }

    @Test
    public void getHighestNormalizedRange() {
        Mockito.when(recommendationService.calculateHighestNormalizedRange(Mockito.anyString())).thenReturn(TestData.TEST_CRYPTO_NORMALIZED_RANGE);
        CryptoNormalizedRange highestNormalizedRange = recommendationController.getHighestNormalizedRange("2022-01-10");

        assertEquals(TestData.TEST_CRYPTO_NORMALIZED_RANGE.getSymbol(), highestNormalizedRange.getSymbol());
        assertEquals(TestData.TEST_CRYPTO_NORMALIZED_RANGE.getNormalizedRange(), highestNormalizedRange.getNormalizedRange());
    }
}
