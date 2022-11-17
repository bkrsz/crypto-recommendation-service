package com.xm.exercise.cryptorecommendationservice.application.model;

import java.util.List;

public class Crypto {

    private String symbol;
    private List<CryptoRecord> cryptoValues;

    public Crypto() {
    }

    public Crypto(String symbol, List<CryptoRecord> cryptoValues) {
        this.symbol = symbol;
        this.cryptoValues = cryptoValues;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<CryptoRecord> getCryptoValues() {
        return cryptoValues;
    }

    public void setCryptoValues(List<CryptoRecord> cryptoValues) {
        this.cryptoValues = cryptoValues;
    }
}
