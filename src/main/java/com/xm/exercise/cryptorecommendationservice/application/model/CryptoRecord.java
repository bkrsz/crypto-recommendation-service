package com.xm.exercise.cryptorecommendationservice.application.model;

import java.time.LocalDateTime;

public class CryptoRecord {

    private String symbol;
    private LocalDateTime dateTime;
    private Double price;

    public CryptoRecord() {
    }

    public CryptoRecord(String symbol, LocalDateTime dateTime, Double price) {
        this.symbol = symbol;
        this.dateTime = dateTime;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CryptoValue{" +
                "symbol='" + symbol + '\'' +
                ", dateTime=" + dateTime +
                ", price=" + price +
                '}';
    }
}
