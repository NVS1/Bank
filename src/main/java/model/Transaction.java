package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Account from;
    @ManyToOne
    private Account to;
    private BigDecimal money;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ExchangeRate exchangeRate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Transaction() {

    }

    public Transaction(Client client, Account from, Account to, BigDecimal money, Date date) {
        this.client = client;
        this.from = from;
        this.to = to;
        this.money = money;
        this.exchangeRate = exchangeRate;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Account getFrom() {
        return from;
    }

    public Account getTo() {
        return to;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
