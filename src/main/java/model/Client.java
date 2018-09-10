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
        addAccounts();
    }
    public Client(String name, String phone) {
        this.name = name;
        this.phone = phone;
        addAccounts();
    }
    public void addTransaction (Transaction transaction){
        transactions.add(transaction);
    }

    private void addAccounts (){
        Account uah = new Account(phone+Currency.UAH, Currency.UAH, this);
        Account usd = new Account(phone+Currency.USD, Currency.USD, this);
        Account eur = new Account(phone+Currency.EUR, Currency.EUR, this);
        accounts.add(uah);
        accounts.add(usd);
        accounts.add(eur);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!name.equals(client.name)) return false;
        if (!phone.equals(client.phone)) return false;
        if (!accounts.equals(client.accounts)) return false;
        return transactions.equals(client.transactions);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + accounts.hashCode();
        result = 31 * result + transactions.hashCode();
        return result;
    }
}
