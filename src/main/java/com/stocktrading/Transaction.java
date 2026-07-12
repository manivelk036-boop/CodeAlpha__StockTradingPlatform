package com.stocktrading;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final String type;
    private final String symbol;
    private final int quantity;
    private final double pricePerShare;
    private final String timestamp;

    public Transaction(String type, String symbol, int quantity, double pricePerShare) {
        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getType() {
        return type;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
