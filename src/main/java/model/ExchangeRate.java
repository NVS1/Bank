package model;

import javax.persistence.*;

@Entity
public class ExchangeRate {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private Currency from;
    @Enumerated(EnumType.STRING)
    private Currency to;
    private Long rate;

    public ExchangeRate() {
    }

    public ExchangeRate(Currency from, Currency to, Long rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }
    public Long exchange(Long money){
        return money*rate;
    }

    public Long getId() {
        return id;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    public Long getRate() {
        return rate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFrom(Currency from) {
        this.from = from;
    }

    public void setTo(Currency to) {
        this.to = to;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }
}
