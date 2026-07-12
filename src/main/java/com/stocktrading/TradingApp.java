package com.stocktrading;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class TradingApp {
    private final Map<String, Stock> marketData = new LinkedHashMap<>();
    private final Portfolio portfolio = new Portfolio();
    private final Scanner scanner = new Scanner(System.in);

    public TradingApp() {
        marketData.put("AAPL", new Stock("AAPL", "Apple Inc.", 190.25, 1.40));
        marketData.put("MSFT", new Stock("MSFT", "Microsoft Corp.", 415.80, 0.92));
        marketData.put("GOOGL", new Stock("GOOGL", "Alphabet Inc.", 175.60, -0.34));
        marketData.put("TSLA", new Stock("TSLA", "Tesla Inc.", 248.10, 2.10));
    }

    public void start() {
        while (true) {
            System.out.println("\n===== Stock Trading Console =====");
            System.out.println("1. View market data");
            System.out.println("2. Buy stock");
            System.out.println("3. Sell stock");
            System.out.println("4. View portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1 -> displayMarketData();
                case 2 -> buyStock();
                case 3 -> sellStock();
                case 4 -> displayPortfolio();
                case 5 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void displayMarketData() {
        System.out.println("\nMarket Data:");
        for (Stock stock : marketData.values()) {
            System.out.printf("%s - %s | Price: $%.2f | Change: %.2f%%%n",
                    stock.getSymbol(), stock.getCompanyName(), stock.getCurrentPrice(), stock.getPriceChangePercent());
        }
    }

    private void buyStock() {
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.nextLine().toUpperCase();
        Stock stock = marketData.get(symbol);
        if (stock == null) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        portfolio.buyStock(symbol, quantity, stock.getCurrentPrice());
        System.out.println("Purchase completed.");
    }

    private void sellStock() {
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.nextLine().toUpperCase();
        Stock stock = marketData.get(symbol);
        if (stock == null) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        try {
            portfolio.sellStock(symbol, quantity, stock.getCurrentPrice());
            System.out.println("Sale completed.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayPortfolio() {
        System.out.println("\nPortfolio:");
        if (portfolio.getHoldings().isEmpty()) {
            System.out.println("No holdings yet.");
            return;
        }

        double totalValue = portfolio.calculateTotalValue(marketData);
        double costBasis = portfolio.calculateCostBasis();

        for (Holding holding : portfolio.getHoldings()) {
            Stock stock = marketData.get(holding.getSymbol());
            double currentPrice = stock != null ? stock.getCurrentPrice() : 0;
            double marketValue = holding.getMarketValue(currentPrice);
            double gainLoss = marketValue - holding.getCostBasis();
            System.out.printf("%s | Qty: %d | Avg Cost: $%.2f | Market Value: $%.2f | P/L: $%.2f%n",
                    holding.getSymbol(), holding.getQuantity(), holding.getAverageCostPerShare(), marketValue, gainLoss);
        }

        System.out.printf("Total portfolio value: $%.2f%n", totalValue);
        System.out.printf("Total cost basis: $%.2f%n", costBasis);
        System.out.printf("Overall gain/loss: $%.2f%n", totalValue - costBasis);
    }

    public static void main(String[] args) {
        new TradingApp().start();
    }
}
