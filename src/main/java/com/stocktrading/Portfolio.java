package com.stocktrading;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Portfolio {
    private final Map<String, Holding> holdings = new LinkedHashMap<>();
    private final List<Transaction> transactions = new ArrayList<>();

    public void buyStock(String symbol, int quantity, double pricePerShare) {
        Holding holding = holdings.computeIfAbsent(symbol, key -> new Holding(symbol, 0, 0));
        holding.addShares(quantity, pricePerShare);
        transactions.add(new Transaction("BUY", symbol, quantity, pricePerShare));
    }

    public void sellStock(String symbol, int quantity, double pricePerShare) {
        Holding holding = holdings.get(symbol);
        if (holding == null || holding.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough shares to sell");
        }
        holding.removeShares(quantity, pricePerShare);
        if (holding.getQuantity() == 0) {
            holdings.remove(symbol);
        }
        transactions.add(new Transaction("SELL", symbol, quantity, pricePerShare));
    }

    public List<Holding> getHoldings() {
        return new ArrayList<>(holdings.values());
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public double calculateTotalValue(Map<String, Stock> marketData) {
        double total = 0.0;
        for (Holding holding : holdings.values()) {
            Stock stock = marketData.get(holding.getSymbol());
            if (stock != null) {
                total += holding.getMarketValue(stock.getCurrentPrice());
            }
        }
        return total;
    }

    public double calculateCostBasis() {
        double total = 0.0;
        for (Holding holding : holdings.values()) {
            total += holding.getCostBasis();
        }
        return total;
    }
}
