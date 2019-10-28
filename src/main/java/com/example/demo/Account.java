package com.example.demo;

import javax.persistence.*;
import javax.transaction.Transaction;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 1, max = 9)
    private String acctno;

    @NotNull
    @Size(min = 1, max = 9)
    private String firstName;

    @NotNull
    private String lastName;

    private double balance;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Transaction> transactions;

    public Account() {
    }

    public Account(@NotNull @Size(min = 1, max = 9) String acctno, @NotNull @Size(min = 1, max = 9) String firstName, @NotNull String lastName, double balance, User user, Set<Transaction> transactions) {
        this.acctno = acctno;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.user = user;
        this.transactions = transactions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAcctno() {
        return acctno;
    }

    public void setAcctno(String acctno) {
        this.acctno = acctno;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}