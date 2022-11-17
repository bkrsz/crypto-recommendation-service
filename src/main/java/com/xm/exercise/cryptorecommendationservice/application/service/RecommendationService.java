package com.xm.exercise.cryptorecommendationservice.application.service;

import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoCalculatedValues;
import com.xm.exercise.cryptorecommendationservice.api.model.response.CryptoNormalizedRange;
import com.xm.exercise.cryptorecommendationservice.application.exception.NotFoundException;
import com.xm.exercise.cryptorecommendationservice.application.model.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RecommendationService {

    private final FileReaderService fileReaderService;
    private final CalculationService calculationService;

    @Autowired
    public RecommendationService(FileReaderService fileReaderService, CalculationService calculationService) {
        this.fileReaderService = fileReaderService;
        this.calculationService = calculationService;
    }

    /**
     * Reads the data from the file and calculates the normalized range for each crypto
     *
     * @return List of normalized ranges
     */
    public List<CryptoNormalizedRange> calculateNormalizedRanges() {
        List<Crypto> cryptoList = fileReaderService.readFiles();

        return calculationService.calculateNormalizedRanges(cryptoList);
    }

    /**
     * Reads the data from the files and calculates different values for the selected crypto
     *
     * @param symbol The selected crypto
     * @return Calculated values
     */
    public CryptoCalculatedValues calculateValues(String symbol) {
        List<Crypto> cryptoList = fileReaderService.readFiles();
        Crypto cryptoBySymbol = cryptoList.stream()
                .filter(crypto -> crypto.getSymbol().equals(symbol))
                .findFirst()
                .orElseThrow(NotFoundException::new);

        if (cryptoBySymbol.getCryptoValues().isEmpty()) {
            throw new NotFoundException();
        }

        return calculationService.calculateValues(cryptoBySymbol);
    }

    /**
     * Reads the files then the normalized range for each crypto for a specific day
     * and returns the crypto with the highest value
     *
     * @param dateTime The date
     * @return The highest normalized range and the symbol of the crypto
     */
    public CryptoNormalizedRange calculateHighestNormalizedRange(String dateTime) {
        LocalDate localDate = LocalDate.parse(dateTime, DateTimeFormatter.ISO_DATE);
        List<Crypto> cryptoList = fileReaderService.readFiles();

        return calculationService.calculateHighestNormalizedRange(cryptoList, localDate);
    }
}
