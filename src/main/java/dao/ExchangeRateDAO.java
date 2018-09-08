package dao;

import model.ExchangeRate;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ExchangeRateDAO extends AbstractDAO<ExchangeRate, Integer>{
    public ExchangeRateDAO(EntityManager em) {
        super(em);
    }

    @Override
    ExchangeRate get(Integer id) {
        return em.find(ExchangeRate.class, id.intValue());
    }

    @Override
    List<ExchangeRate> getAll() {
        TypedQuery<ExchangeRate> query = em.createQuery("SELECT e FROM ExchangeRate e", ExchangeRate.class);
        return query.getResultList();
    }
}
