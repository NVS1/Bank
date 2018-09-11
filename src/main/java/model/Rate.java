package model;

import javax.persistence.*;

@Entity
public class Rate {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private Currency fromCurrency;
    @Enumerated(EnumType.STRING)
    private Currency toCurrency;
    private Long rate;

    public Rate() {
    }

    public Rate(String id, Currency fromCurrency, Currency toCurrency, Long rate) {
        this.id = id;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
    }

    public Long exchange(Long money){
        return money/1000*rate;
    }

    public String getId() {
        return id;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }

    public Long getRate() {
        return rate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFromCurrency(Currency from) {
        this.fromCurrency = from;
    }

    public void setToCurrency(Currency to) {
        this.toCurrency = to;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }
}
