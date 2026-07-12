package com.stocktrading;

public class Stock {
    private final String symbol;
    private final String companyName;
    private double currentPrice;
    private double priceChangePercent;

    public Stock(String symbol, String companyName, double currentPrice, double priceChangePercent) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
        this.priceChangePercent = priceChangePercent;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getPriceChangePercent() {
        return priceChangePercent;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setPriceChangePercent(double priceChangePercent) {
        this.priceChangePercent = priceChangePercent;
    }
}
