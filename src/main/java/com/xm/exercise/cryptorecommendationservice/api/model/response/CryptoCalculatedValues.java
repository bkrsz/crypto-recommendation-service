package com.xm.exercise.cryptorecommendationservice.api.model.response;

public class CryptoCalculatedValues {

    private String symbol;
    private CryptoValue oldest;
    private CryptoValue newest;
    private CryptoValue min;
    private CryptoValue max;

    public CryptoCalculatedValues() {
    }

    public CryptoCalculatedValues(String symbol, CryptoValue oldest, CryptoValue newest, CryptoValue min, CryptoValue max) {
        this.symbol = symbol;
        this.oldest = oldest;
        this.newest = newest;
        this.min = min;
        this.max = max;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public CryptoValue getOldest() {
        return oldest;
    }

    public void setOldest(CryptoValue oldest) {
        this.oldest = oldest;
    }

    public CryptoValue getNewest() {
        return newest;
    }

    public void setNewest(CryptoValue newest) {
        this.newest = newest;
    }

    public CryptoValue getMin() {
        return min;
    }

    public void setMin(CryptoValue min) {
        this.min = min;
    }

    public CryptoValue getMax() {
        return max;
    }

    public void setMax(CryptoValue max) {
        this.max = max;
    }
}
