package controller;

import dao.CurrencyDao;
import entity.Currency;

public class CurrencyController {

    private final CurrencyDao currencyDao;

    public CurrencyController() {
        this.currencyDao = new CurrencyDao();
    }

    /**
     * Convert an amount from one currency to another.
     *
     * @param fromAbbr Source currency abbreviation (e.g., "USD")
     * @param toAbbr   Target currency abbreviation (e.g., "EUR")
     * @param amount   Amount to convert
     * @return Converted amount
     * @throws CurrencyConversionException if any currency is not found or rate error occurs
     */
    public double convert(String fromAbbr, String toAbbr, double amount) {
        try {
            // Get rates from DAO
            double fromRate = currencyDao.getRate(fromAbbr);
            double toRate = currencyDao.getRate(toAbbr);

            // Conversion formula: amount * (toRate / fromRate)
            return amount * (toRate / fromRate);

        } catch (Exception e) {
            // Wrap all exceptions into a CurrencyConversionException
            throw new CurrencyConversionException("Conversion failed: " + e.getMessage());
        }
    }

    /**
     * Add a new currency to the database.
     *
     * @param abbreviation Currency abbreviation (e.g., "GBP")
     * @param name         Currency full name (e.g., "British Pound")
     * @param rateToUSD    Rate compared to USD
     */
    public void addCurrency(String abbreviation, String name, double rateToUSD) {
        Currency currency = new Currency(abbreviation, name, rateToUSD);
        try {
            currencyDao.addCurrency(currency);
        } catch (Exception e) {
            throw new CurrencyConversionException("Failed to add currency: " + e.getMessage());
        }
    }
}
