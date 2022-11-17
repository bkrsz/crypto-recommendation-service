package com.xm.exercise.cryptorecommendationservice.api.model.response;

public class CryptoNormalizedRange {

    private String symbol;
    private Double normalizedRange;

    public CryptoNormalizedRange() {
    }

    public CryptoNormalizedRange(String symbol, Double normalizedRange) {
        this.symbol = symbol;
        this.normalizedRange = normalizedRange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getNormalizedRange() {
        return normalizedRange;
    }

    public void setNormalizedRange(Double normalizedRange) {
        this.normalizedRange = normalizedRange;
    }
}
