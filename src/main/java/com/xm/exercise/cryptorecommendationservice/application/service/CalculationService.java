package com.xm.exercise.cryptorecommendationservice.application.service;

import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoCalculatedValues;
import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoNormalizedRange;
import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoValue;
import com.xm.exercise.cryptorecommendationservice.application.exception.NotFoundException;
import com.xm.exercise.cryptorecommendationservice.application.model.Crypto;
import com.xm.exercise.cryptorecommendationservice.application.model.CryptoRecord;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class CalculationService {

    /**
     * Calculates the normalized range for each crypto
     * Returns a descending sorted list of all the cryptos, comparing the normalized range (i.e. (max-min)/min)
     *
     * @param cryptoList List of cryptos
     * @return List of normalized ranges for each crypto
     */
    public List<CryptoNormalizedRange> calculateNormalizedRanges(List<Crypto> cryptoList) {
        return cryptoList.stream()
                .map(this::calculateNormalizedRange)
                .sorted(Comparator.comparing(CryptoNormalizedRange::getNormalizedRange).reversed())
                .toList();
    }

    /**
     * Calculate different values for a crypto and returns a dataset containing:
     *  - Oldest record by date
     *  - Newest record by date
     *  - Minimum by price
     *  - Maximum by price
     *
     * @param cryptoBySymbol A crypto which on the calculation is requested
     * @return The calculated values
     */
    public CryptoCalculatedValues calculateValues(Crypto cryptoBySymbol) {
        CryptoCalculatedValues cryptoCalculatedValues = new CryptoCalculatedValues();
        cryptoCalculatedValues.setSymbol(cryptoBySymbol.getSymbol());

        CryptoValue oldest = cryptoBySymbol.getCryptoValues().stream()
                .min(Comparator.comparing(CryptoRecord::getDateTime))
                .map(CryptoValue::fromCryptoRecord)
                .orElse(null);
        cryptoCalculatedValues.setOldest(oldest);

        CryptoValue newest = cryptoBySymbol.getCryptoValues().stream()
                .max(Comparator.comparing(CryptoRecord::getDateTime))
                .map(CryptoValue::fromCryptoRecord)
                .orElse(null);
        cryptoCalculatedValues.setNewest(newest);

        CryptoValue min = cryptoBySymbol.getCryptoValues().stream()
                .min(Comparator.comparing(CryptoRecord::getPrice))
                .map(CryptoValue::fromCryptoRecord)
                .orElse(null);
        cryptoCalculatedValues.setMin(min);

        CryptoValue max = cryptoBySymbol.getCryptoValues().stream()
                .max(Comparator.comparing(CryptoRecord::getPrice))
                .map(CryptoValue::fromCryptoRecord)
                .orElse(null);
        cryptoCalculatedValues.setMax(max);

        return cryptoCalculatedValues;
    }

    /**
     * Calculates the normalized range for each crypto for a specific day
     * and returns the crypto with the highest value
     *
     * @param cryptoList List of cryptos
     * @param dateTime The date
     * @return The highest normalized range and the symbol of the crypto
     */
    public CryptoNormalizedRange calculateHighestNormalizedRange(List<Crypto> cryptoList, LocalDate dateTime) {
        return cryptoList.stream()
                .map(crypto -> {
                    List<CryptoRecord> cryptoRecordList = crypto.getCryptoValues().stream()
                            .filter(cryptoRecord -> cryptoRecord.getDateTime().toLocalDate().equals(dateTime)).toList();
                    return new Crypto(crypto.getSymbol(), cryptoRecordList);
                })
                .filter(crypto -> !crypto.getCryptoValues().isEmpty())
                .map(this::calculateNormalizedRange)
                .max(Comparator.comparing(CryptoNormalizedRange::getNormalizedRange))
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Calculates the normalized range for a crypto
     *
     * @param crypto The crypto
     * @return Calculated normalized range
     */
    private CryptoNormalizedRange calculateNormalizedRange(Crypto crypto) {
        CryptoNormalizedRange range = new CryptoNormalizedRange();
        range.setSymbol(crypto.getSymbol());

        Double normalizedValue = calculateNormalizedRangeForList(crypto.getCryptoValues());
        range.setNormalizedRange(normalizedValue);
        return range;
    }

    /**
     * Calculates the normalized value ((max - min) / min) for a list of crypto records
     *
     * @param cryptoRecords List of crypto record models
     * @return Normalized value
     */
    private Double calculateNormalizedRangeForList(List<CryptoRecord> cryptoRecords) {
        CryptoRecord min = cryptoRecords.stream()
                .min(Comparator.comparing(CryptoRecord::getPrice))
                .orElseThrow(NotFoundException::new);

        CryptoRecord max = cryptoRecords.stream()
                .max(Comparator.comparing(CryptoRecord::getPrice))
                .orElseThrow(NotFoundException::new);

        Double minValue = min.getPrice();
        Double maxValue = max.getPrice();
        return (maxValue - minValue) / minValue;
    }
}
