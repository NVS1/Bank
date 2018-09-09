package model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;
    @ManyToOne(cascade = CascadeType.ALL)
    private Account from;
    @ManyToOne (cascade = CascadeType.ALL)
    private Account to;
    private Long money;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ExchangeRate exchangeRate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Transaction() {

    }

    public Transaction(Client client, Account from, Account to, Long money) {
        this.client = client;
        this.from = from;
        this.to = to;
        this.money = money;
    }
    public boolean doTransactoin (){
      if (from.withdraw(money)){
          if (exchangeRate==null){
              to.debit(money);
          }else {
              Long newSum = exchangeRate.exchange(money);
              to.debit(newSum);
          }
          client.addTransaction(this);
          return true;
      }
      return false;
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

    public Long getMoney() {
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

    public void setMoney(Long money) {
        this.money = money;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
