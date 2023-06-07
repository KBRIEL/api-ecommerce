package com.nc.ecommerce.service;

import com.nc.ecommerce.models.Procuctos;
import com.nc.ecommerce.models.Usuario;
import com.nc.ecommerce.models.Transaction;
import com.nc.ecommerce.repositories.AccountRepository;
import com.nc.ecommerce.repositories.ClientRepository;
import com.nc.ecommerce.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.nc.ecommerce.models.TransactionType.CREDITO;
import static com.nc.ecommerce.models.TransactionType.DEBITO;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepository txRepository;
    @Autowired
    private ClientRepository clRepository;
    @Autowired
    private AccountRepository accRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public TransactionService() {
    }


    public boolean parameters(String description, Double amount,
                              Procuctos originAccount, Procuctos destinationAccount){
        if (description.isEmpty() || amount == null || amount.isNaN() || amount.isInfinite() || originAccount.getNumber().isEmpty() || destinationAccount.getNumber().isEmpty()) {
            return false;
        }else{
            return true;
        }

    }

    public boolean parametersAccount(Procuctos originAccount, Procuctos destinationAccount){

        return originAccount == null || destinationAccount == null || originAccount.getNumber().equals(destinationAccount.getNumber());
    }

    public boolean parametersAmount(Double amount, Usuario client, Procuctos originAccount){

        return amount >0 || !client.getAccounts().contains(originAccount)||originAccount.getBalance() < amount;
    }


    public ResponseEntity<Object> createTransaction(Usuario client, String description, Double amount,
                                                    Procuctos originAccount, Procuctos destinationAccount) {
        if (this.parameters(description,amount,originAccount,destinationAccount) && this.parametersAmount(amount, client,originAccount) && !this.parametersAccount(originAccount,destinationAccount) ){


            originAccount.setBalance(originAccount.getBalance() - amount);
            Transaction transactionDebit = new Transaction(DEBITO, amount, description, LocalDateTime.now(), originAccount);

            destinationAccount.setBalance(destinationAccount.getBalance() + amount);
            Transaction transactionCredit = new Transaction(CREDITO, amount, description, LocalDateTime.now(), destinationAccount);


            txRepository.save(transactionDebit);
            txRepository.save(transactionCredit);
            accRepository.save(originAccount);
            accRepository.save(destinationAccount);


            return new ResponseEntity<>("Transaction success", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("the transaction could not be carried out", HttpStatus.FORBIDDEN);

        }






    }

}
