package com.nc.ecommerce.models.dtos;

import com.nc.ecommerce.models.Card;
import com.nc.ecommerce.models.CardColor;
import com.nc.ecommerce.models.CardType;
import com.nc.ecommerce.models.Usuario;

import java.time.LocalDateTime;

public class CardDTO {

    private Usuario client_id ;
    private String cardHolder;
    private CardType type;
    private CardColor color;
    private String number;
    private int cvv;


    public String getCardHolder() {
        return cardHolder;
    }

    private LocalDateTime issueDate;
    private LocalDateTime expirationDate;
    public CardDTO(Card card) {
        this.client_id = card.getClient_id();
        this.color = card.getColor();
        this.type = card.getType();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.issueDate = card.getIssueDate();
        this.expirationDate = card.getExpirationDate();
        this.cardHolder=card.getCardHolder();
    }



    public CardColor getColor() {
        return color;
    }

    public CardType getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }
}
