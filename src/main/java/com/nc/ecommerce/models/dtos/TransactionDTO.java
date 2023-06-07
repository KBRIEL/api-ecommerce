package com.nc.ecommerce.models.dtos;

import com.nc.ecommerce.models.Procuctos;
import com.nc.ecommerce.models.Transaction;
import com.nc.ecommerce.models.TransactionType;


import java.time.LocalDateTime;

public class TransactionDTO {

    private Long transaction_id;

    private TransactionType type;
    private  double amount;
    private LocalDateTime date;

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

    private String motive;

    public Long getTransaction_id() {
        return transaction_id;
    }

    private Procuctos account;



    public TransactionDTO(Transaction _transaction){
        this.transaction_id= _transaction.getTransaction_id();
        this.type = _transaction.getType();
        this.amount = _transaction.getAmount();
        this.account = _transaction.getAccount();
        this.motive = _transaction.getMotive();
        this.date = _transaction.getDate();
    }

}
