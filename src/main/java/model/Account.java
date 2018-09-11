package model;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    private String number;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @ManyToOne
    private Client owner;
    private Long money = Long.valueOf(0);

    public Account(String number, Currency currency, Client owner) {
        this.number = number;
        this.currency = currency;
        this.owner = owner;
    }

    public Account() {
    }
    public boolean withdraw (Long amount){
        if (amount>money){
            return false;
        } else {
            money-=amount;
            return true;
        }
    }
    public void debit (Long amount){
        money+=amount;
    }

    public Long getMoneyInUAH (Rate rate){
        if (currency.equals(Currency.UAH)){
            return money;
        }else {
            return rate.exchange(money);
        }
    }

    public String getNumber() {
        return number;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Client getOwner() {
        return owner;
    }

    public Long getMoney() {
        return money;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public void setMoney(Long money) {
        this.money = money;
    }
}

