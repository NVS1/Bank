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
    @OneToOne(cascade = CascadeType.ALL)
    private Rate rate;
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
    public boolean doTransaction (){
      if (from.withdraw(money)){
          if (rate ==null){
              to.debit(money);
          }else {
              Long newSum = rate.exchange(money);
              to.debit(newSum);
          }
          client.addTransaction(this);
          return true;
      }else {
          return false;
      }
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

    public Rate getRate() {
        return rate;
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

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
