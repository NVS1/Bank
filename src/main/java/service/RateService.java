package service;

import dao.JpaDAO;
import dao.RateDAO;
import model.Currency;
import model.Rate;
import javax.persistence.EntityManager;

public class RateService extends JpaDAO<Rate, String> implements RateDAO {

    public RateService(EntityManager em) {
        super(em, Rate.class);
    }

    @Override
    public void init() {
        getEm().getTransaction().begin();
        try{
            getEm().persist(new Rate(Currency.USD.toString()+Currency.UAH.toString(), Currency.USD, Currency.UAH, (long)2810));
            getEm().persist(new Rate(Currency.EUR.toString()+Currency.UAH.toString(), Currency.EUR, Currency.UAH, (long)3245));
            getEm().persist(new Rate(Currency.USD.toString()+Currency.EUR.toString(), Currency.USD, Currency.EUR, (long)86));
            getEm().persist(new Rate(Currency.UAH.toString()+Currency.USD.toString(), Currency.UAH, Currency.USD, (long)35));
            getEm().persist(new Rate(Currency.UAH.toString()+Currency.EUR.toString(), Currency.UAH, Currency.EUR, (long)3));
            getEm().persist(new Rate(Currency.EUR.toString()+Currency.USD.toString(), Currency.EUR, Currency.USD, (long)116));
            getEm().getTransaction().commit();
        } catch (Exception ex){
            getEm().getTransaction().rollback();
        }
    }
}
