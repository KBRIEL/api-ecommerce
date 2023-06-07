package com.nc.ecommerce.service;


import com.nc.ecommerce.models.*;
import com.nc.ecommerce.models.*;
import com.nc.ecommerce.repositories.AccountRepository;
import com.nc.ecommerce.repositories.CardRepository;
import com.nc.ecommerce.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static com.nc.ecommerce.models.CardType.CREDIT;
import static com.nc.ecommerce.models.CardType.DEBIT;

@Service
@Transactional
public class CardService {


    @Autowired
    private AccountRepository accRepository;
    @Autowired
    ClientRepository clRepository;
    @Autowired
    CardRepository cdRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public CardService() {
    }

    public ResponseEntity<Object> createCard(Authentication authentication, CardColor _color, CardType _type) {

        Usuario client = clRepository.findByEmail(authentication.getName());
        Set<Procuctos> accounts = accRepository.findAll().stream().filter(account -> account.getClient_id() == client).collect(Collectors.toSet());

        Set<Card> cardsDebit = client.getCards().stream().filter(c->c.getType().equals(DEBIT)).collect(Collectors.toSet());
        Set<Card> cardsCredit = client.getCards().stream().filter(c->c.getType().equals(CREDIT)).collect(Collectors.toSet());
        if ((cardsDebit.size() < 3 && _type.equals(DEBIT))||(cardsCredit.size() < 3 && _type.equals(CREDIT))){
            int num1=(int)(Math.floor(Math.random()* 9999+1000));
            int num2=(int)(Math.floor(Math.random()* 9999+1000));
            int num3=(int)(Math.floor(Math.random()* 9999+1000));
            int num4=(int)(Math.floor(Math.random()* 9999+1000));
            int cvv=(int)(Math.floor(Math.random()* 999+100));


            String number= num1 +"-"+num2 +"-"+num3 +"-"+num4;
            cdRepository.save(new Card( client,_color,_type,number,cvv, LocalDateTime.now(),LocalDateTime.now().plusYears(5)));
            return new ResponseEntity<>("successfully ", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("exceeded limit", HttpStatus.FORBIDDEN);
        }

    }


}
