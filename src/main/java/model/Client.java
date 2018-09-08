package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {
    private String name;
    @Id
    private String phone;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Client() {
        Account uah = new Account(phone+Currency.UAH, Currency.UAH, this);
        Account usd = new Account(phone+Currency.USD, Currency.USD, this);
        Account eur = new Account(phone+Currency.EUR, Currency.EUR, this);
        accounts.add(uah);
        accounts.add(eur);
        accounts.add(usd);
    }

    public Client(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
