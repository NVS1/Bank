package dao;

import model.Currency;
import model.ExchangeRate;

public interface ExchangeRateDAO extends GenericDAO<ExchangeRate, Integer>{
    boolean check (ExchangeRate exchangeRate);
    ExchangeRate getByCurrency (Currency from, Currency to);
}
