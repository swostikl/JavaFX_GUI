package controller;

import model.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyConverterController {
    private List<Currency> currencies;

    public CurrencyConverterController() {
        currencies = new ArrayList<>();

        // Hardcoded currencies for now (rates relative to 1 USD)
        currencies.add(new Currency("USD", "United States Dollar", 1.0));
        currencies.add(new Currency("EUR", "Euro", 0.85));
        currencies.add(new Currency("GBP", "British Pound", 0.74));
        currencies.add(new Currency("NPR","Nepalin Rupia", 141.14));
        currencies.add(new Currency("INR", "Indian Rupee", 88.30));
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public double convert(double amount, Currency from, Currency to) {
        // Convert amount -> USD -> target
        double amountInUSD = amount / from.getRateToUSD();
        return amountInUSD * to.getRateToUSD();
    }
}
