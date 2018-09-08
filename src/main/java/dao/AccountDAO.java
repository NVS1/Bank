package dao;

import model.Account;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountDAO extends AbstractDAO<Account, String> {

    public AccountDAO(EntityManager em) {
        super(em);
    }

    @Override
    Account get(String number) {
        return em.find(Account.class, number);
    }

    @Override
    List<Account> getAll() {
        TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a", Account.class);
        return query.getResultList();
    }
}
