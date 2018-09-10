package service;

import dao.JpaDAO;
import model.Transaction;

import javax.persistence.EntityManager;

public class TransactionService extends JpaDAO<Transaction, Integer> {

    public TransactionService(EntityManager em) {
        super(em, Transaction.class);
    }

}
