package controller;

import dao.TransactionDao;
import entity.Currency;
import entity.Transaction;
import jakarta.persistence.EntityManager;

public class CurrencyController {

    private EntityManager em;
    private TransactionDao transactionDAO;

    public CurrencyController(EntityManager em) {
        this.em = em;
        this.transactionDAO = new TransactionDao(em);
    }

    public double convert(Currency from, Currency to, double amount) {
        double rate = to.getRateTOUSD() / from.getRateTOUSD();

        double result = amount * rate;

        // Save transaction
        Transaction tx = new Transaction(from, to, amount, result);
        transactionDAO.saveTransaction(tx);

        return result;
    }
}

