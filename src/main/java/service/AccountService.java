package service;

import dao.JpaDAO;
import model.Account;

import javax.persistence.EntityManager;

public class AccountService extends JpaDAO<Account, String> {

    public AccountService(EntityManager em) {
        super(em, Account.class);
    }
}
