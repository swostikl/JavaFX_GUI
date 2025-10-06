package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import entity.Transaction;

public class TransactionDao {

    private EntityManager em;

    public TransactionDao(EntityManager em) {
        this.em = em;
    }

    public void saveTransaction(Transaction transaction) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(transaction);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }
}

