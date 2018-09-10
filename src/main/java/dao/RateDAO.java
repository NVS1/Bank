package dao;

import model.Rate;

public interface RateDAO extends GenericDAO<Rate, String> {
    void init();
}
