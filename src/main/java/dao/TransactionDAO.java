package dao;

import model.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TransactionDAO extends AbstractDAO <Transaction, Integer> {

    public TransactionDAO(EntityManager em) {
        super(em);
    }

    @Override
    Transaction get(Integer id) {
        return em.find(Transaction.class, id.intValue());
    }

    @Override
    List<Transaction> getAll() {
        TypedQuery<Transaction> query = em.createQuery("SELECT t FROM Transaction t", Transaction.class);
       return query.getResultList();
    }
}
