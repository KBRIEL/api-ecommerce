package com.nc.ecommerce.controllers;


import com.nc.ecommerce.models.CardColor;
import com.nc.ecommerce.models.CardType;
import com.nc.ecommerce.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardService cdService;




    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(Authentication authentication, CardColor color, CardType type) {
    return cdService.createCard(authentication, color, type);

    }

}
