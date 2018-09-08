package model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ExchangeRate {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private Currency from;
    @Enumerated(EnumType.STRING)
    private Currency to;
    private BigDecimal rate;

    public ExchangeRate() {
    }

    public ExchangeRate(Currency from, Currency to, BigDecimal rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }
    public BigDecimal exchange(BigDecimal money){
        return rate.multiply(money);
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

    public BigDecimal getRate() {
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

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
