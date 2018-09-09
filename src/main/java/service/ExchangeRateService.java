package service;

import dao.ExchangeRateDAO;
import dao.JpaDAO;
import model.Currency;
import model.ExchangeRate;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ExchangeRateService extends JpaDAO<ExchangeRate, Integer> implements ExchangeRateDAO {

    private ExchangeRateService(EntityManager em) {
        super(em, ExchangeRate.class);
    }

    @Override
    public boolean check(ExchangeRate exchangeRate) {
        Query query = getEm()
                .createQuery("SELECT count (*) FROM "+getPersistentClass().getSimpleName()+
                        " e WHERE e.from = :from AND e.to = :to")
                .setParameter("from", exchangeRate.getFrom())
                .setParameter("to", exchangeRate.getTo());
        Long count = (Long) query.getSingleResult();
        return count==1;
    }

    @Override
    public ExchangeRate getByCurrency(Currency from, Currency to) {
        Query query = getEm()
                .createQuery("SELECT e FROM "+getPersistentClass().getSimpleName()+
                        " e WHERE e.from = :from AND e.to = :to")
                .setParameter("from", from)
                .setParameter("to", to);
        return (ExchangeRate) query.getSingleResult();
    }
}
