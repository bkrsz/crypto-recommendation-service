package com.xm.exercise.cryptorecommendationservice.api.model.response;

import com.xm.exercise.cryptorecommendationservice.application.model.CryptoRecord;

import java.time.LocalDateTime;

public class CryptoValue {

    private LocalDateTime dateTime;
    private Double value;

    public CryptoValue() {
    }

    public CryptoValue(LocalDateTime dateTime, Double value) {
        this.dateTime = dateTime;
        this.value = value;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public static CryptoValue fromCryptoRecord(CryptoRecord cryptoRecord) {
        return new CryptoValue(cryptoRecord.getDateTime(), cryptoRecord.getPrice());
    }
}
