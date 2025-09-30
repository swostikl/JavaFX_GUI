package controller;

import dao.CurrencyDao;

public class CurrencyController {
    private final CurrencyDao currencyDao;

    public CurrencyController() {
        this.currencyDao = new CurrencyDao();
    }

    public double convert(String fromAbbr, String toAbbr, double amount) {
        try {
            double fromRate = currencyDao.getRate(fromAbbr);
            double toRate = currencyDao.getRate(toAbbr);
            return amount * (toRate / fromRate);
        } catch (RuntimeException e) {
            throw new CurrencyConversionException("Error: " + e.getMessage());
        }
    }
}
