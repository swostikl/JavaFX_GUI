package application;


import dao.TransactionDao;
import entity.Currency;
import entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class CurrencyConverterApp {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("currencyPU");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("=== Currency Converter with JPA ===");

            // Step 1: Seed initial currencies if table is empty
            em.getTransaction().begin();
            if (em.createQuery("SELECT COUNT(c) FROM Currency c", Long.class).getSingleResult() == 0) {
                em.persist(new Currency("USD", "United States Dollar", 1.0));
                em.persist(new Currency("NPR", "Nepali Rupee", 141.78));
                em.persist(new Currency("INR", "Indian Rupee", 88.64));
                em.persist(new Currency("JPY", "Japanese Yen", 149.51));
                em.persist(new Currency("CNY", "Chinese Yuan", 7.13));
                em.persist(new Currency("EUR", "Euro", 0.85));
                em.persist(new Currency("CAD", "Canadian Dollar", 1.39));
                em.persist(new Currency("GBP", "British Pound", 0.75));
                em.persist(new Currency("THB", "Thai Baht", 32.14));
            }
            em.getTransaction().commit();
            System.out.println("Initial currencies are ready in the database.");

            // Step 2: Read user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter source currency abbreviation (e.g., EUR): ");
            String sourceAbbr = scanner.nextLine().trim().toUpperCase();

            System.out.print("Enter target currency abbreviation (e.g., USD): ");
            String targetAbbr = scanner.nextLine().trim().toUpperCase();

            System.out.print("Enter amount to convert: ");
            double amount = Double.parseDouble(scanner.nextLine().trim());

            // Step 3: Find currencies safely
            Currency source = null;
            Currency target = null;
            try {
                source = em.createQuery(
                                "SELECT c FROM Currency c WHERE UPPER(c.abbreviation) = :abbr", Currency.class)
                        .setParameter("abbr", sourceAbbr)
                        .getSingleResult();

                target = em.createQuery(
                                "SELECT c FROM Currency c WHERE UPPER(c.abbreviation) = :abbr", Currency.class)
                        .setParameter("abbr", targetAbbr)
                        .getSingleResult();
            } catch (NoResultException e) {
                System.out.println("One or both currency abbreviations not found. Please check and try again.");
                return;
            }

            // Step 4: Perform conversion
            double rate = target.getRateTOUSD() / source.getRateTOUSD();
            double result = amount * rate;
            System.out.printf("%.2f %s = %.2f %s%n", amount, sourceAbbr, result, targetAbbr);

            // Step 5: Store transaction
            TransactionDao transactionDAO = new TransactionDao(em);
            Transaction transaction = new Transaction(source, target, amount, result);
            transactionDAO.saveTransaction(transaction);
            System.out.println("Transaction saved successfully to the database.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}

