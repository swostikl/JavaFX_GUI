package dao;

import entity.Currency;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class CurrencyDao {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("CurrencyMariaDbUnit");

    // Get all currencies
    public List<Currency> getAllCurrencies() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Currency c", Currency.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Get rate for a specific currency by abbreviation
    public double getRate(String abbreviation) {
        EntityManager em = emf.createEntityManager();
        try {
            Currency currency = em.createQuery(
                            "SELECT c FROM Currency c WHERE c.abbreviation = :abbr", Currency.class)
                    .setParameter("abbr", abbreviation)
                    .getSingleResult();
            return currency.getRateTOUSD();
        } finally {
            em.close();
        }
    }

    // Add a new currency
    public void addCurrency(Currency currency) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(currency);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
