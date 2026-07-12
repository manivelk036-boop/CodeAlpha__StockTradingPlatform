package com.stocktrading;

public class Holding {
    private final String symbol;
    private int quantity;
    private double averageCostPerShare;

    public Holding(String symbol, int quantity, double averageCostPerShare) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.averageCostPerShare = averageCostPerShare;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAverageCostPerShare() {
        return averageCostPerShare;
    }

    public void addShares(int quantity, double pricePerShare) {
        double totalCost = this.quantity * this.averageCostPerShare + quantity * pricePerShare;
        this.quantity += quantity;
        this.averageCostPerShare = totalCost / this.quantity;
    }

    public void removeShares(int quantity, double pricePerShare) {
        if (this.quantity < quantity) {
            throw new IllegalArgumentException("Not enough shares to sell");
        }
        this.quantity -= quantity;
    }

    public double getMarketValue(double currentPrice) {
        return quantity * currentPrice;
    }

    public double getCostBasis() {
        return quantity * averageCostPerShare;
    }
}
