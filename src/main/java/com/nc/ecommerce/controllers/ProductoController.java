package com.nc.ecommerce.controllers;


import com.nc.ecommerce.models.dtos.ProductoDTO;
import com.nc.ecommerce.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;




@RestController
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    private AccountService accService;

    @RequestMapping("/productos")
    public List<ProductoDTO> getAll(){
      return accService.getAll();

    }

    @RequestMapping("/productos/{id}")
    public ProductoDTO getAccount(@PathVariable Long id){
        return accService.getAccount(id);

    }
    @PostMapping( "/clients/current/accounts")
    public ResponseEntity<Object> createAccount(Authentication authentication){
        return accService.createAccount(authentication);
    }

}