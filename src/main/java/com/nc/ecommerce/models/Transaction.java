package com.nc.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Long transaction_id;
    @Column
    private TransactionType type;
    private  double amount;
    private LocalDateTime date;
    private String motive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="account_id")
    private Procuctos account_id;
   // @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name="transaction_id")
    // private Account account;



    public Transaction(TransactionType _type, double _amount, String _motive, LocalDateTime _date, Procuctos _account) {
        this.type = _type;
        this.amount = _amount;
        this.account_id = _account;
        this.motive = _motive;
        this.date = _date;

    }

    public Long getTransaction_id() {
        return transaction_id;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getMotive() {
        return motive;
    }

    public Procuctos getAccount() {
        return account_id;
    }


    public Transaction() {
    }
}
