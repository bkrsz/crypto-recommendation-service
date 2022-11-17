package com.xm.exercise.cryptorecommendationservice.testUtils;

import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoCalculatedValues;
import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoNormalizedRange;
import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoValue;
import com.xm.exercise.cryptorecommendationservice.application.model.Crypto;
import com.xm.exercise.cryptorecommendationservice.application.model.CryptoRecord;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class TestData {

    public static List<CryptoRecord> TEST_CRYPTO_RECORD_LIST_1 = List.of(
            new CryptoRecord("BTC", LocalDateTime.of(2022, Month.JANUARY, 1, 0, 0), 20000.00),
            new CryptoRecord("BTC", LocalDateTime.of(2022, Month.JANUARY, 1, 1, 0), 21000.00),
            new CryptoRecord("BTC", LocalDateTime.of(2022, Month.JANUARY, 1, 2, 0), 22000.00),
            new CryptoRecord("BTC", LocalDateTime.of(2022, Month.JANUARY, 1, 3, 0), 23000.00)
    );

    public static List<CryptoRecord> TEST_CRYPTO_RECORD_LIST_2 = List.of(
            new CryptoRecord("LTC", LocalDateTime.of(2022, Month.JANUARY, 1, 0, 0), 5000.00),
            new CryptoRecord("LTC", LocalDateTime.of(2022, Month.JANUARY, 1, 1, 0), 4200.00),
            new CryptoRecord("LTC", LocalDateTime.of(2022, Month.JANUARY, 1, 2, 0), 4300.00),
            new CryptoRecord("LTC", LocalDateTime.of(2022, Month.JANUARY, 1, 3, 0), 4000.00)
    );

    public static List<Crypto> TEST_CRYPTO_LIST = List.of(
            new Crypto("BTC", TEST_CRYPTO_RECORD_LIST_1),
            new Crypto("LTC", TEST_CRYPTO_RECORD_LIST_2)
    );

    public static CryptoNormalizedRange TEST_CRYPTO_NORMALIZED_RANGE = new CryptoNormalizedRange("BTC", 0.33);

    public static CryptoCalculatedValues TEST_CRYPTO_CALCULATED_VALUES = new CryptoCalculatedValues("BTC",
            new CryptoValue(LocalDateTime.of(2022, Month.JANUARY, 1, 0, 0), 1000.00),
            new CryptoValue(LocalDateTime.of(2022, Month.JANUARY, 20, 0, 0), 1100.00),
            new CryptoValue(LocalDateTime.of(2022, Month.JANUARY, 10, 0, 0), 900.00),
            new CryptoValue(LocalDateTime.of(2022, Month.JANUARY, 20, 0, 0), 1200.00)
    );
}
