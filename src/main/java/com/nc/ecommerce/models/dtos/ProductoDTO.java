package com.nc.ecommerce.models.dtos;

import com.nc.ecommerce.models.Procuctos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductoDTO {


    private Long account_id;
    private String number;
    private LocalDateTime creationDate;
    private Double balance;
    private Set<TransactionDTO> transactions= new HashSet<>();

    public ProductoDTO(Procuctos _account ){
        this.account_id= _account.getId();
        this.balance= _account.getBalance();
        this.number= _account.getNumber();
        this.creationDate= _account.getCreationDate();
        this.transactions = _account.getTransacctions().stream().map(t->new TransactionDTO(t)).collect(Collectors.toSet());
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }
}
