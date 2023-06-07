package com.nc.ecommerce.service;


import com.nc.ecommerce.models.Procuctos;
import com.nc.ecommerce.models.Usuario;
import com.nc.ecommerce.models.dtos.ProductoDTO;
import com.nc.ecommerce.repositories.AccountRepository;
import com.nc.ecommerce.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class AccountService {


    @Autowired
    private AccountRepository accRepository;
    @Autowired
    private ClientRepository clRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public AccountService() {
    }
    public List<ProductoDTO> getAll(){
        return accRepository.findAll().stream().map(a -> new ProductoDTO(a)).collect(toList());
    }

    public ProductoDTO getAccount(Long id){

        return accRepository.findById(id).map(a-> new ProductoDTO(a)).orElse(null);
    }



    public ResponseEntity<Object> createAccount(Authentication authentication) {
        Usuario client = clRepository.findByEmail(authentication.getName());
        Set<Procuctos> accounts = accRepository.findAll().stream().filter(account -> account.getClient_id() == client).collect(Collectors.toSet());

        if (accounts.size() < 3) {
            int num=(int)(Math.floor(Math.random()* 99999999+1));
            String number= "VIN" + num;
            accRepository.save(new Procuctos(number, LocalDateTime.now(), 0, client));
            return new ResponseEntity<>("successfully ", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("exceeded limit", HttpStatus.FORBIDDEN);
        }

    }



}
