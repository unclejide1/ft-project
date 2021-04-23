package com.fintech.demo.dto;

import com.fintech.demo.model.enums.CardSchemes;
import com.fintech.demo.model.enums.CardType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;


@Builder
@Data
public class CardDetailsResponseJSON {
    public CardDetailsResponseJSON(String cardType, String cardSchemes, String bank) {
        this.cardType = cardType;
        this.cardSchemes = cardSchemes;
        this.bank = bank;
    }

    public CardDetailsResponseJSON() {
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardSchemes() {
        return cardSchemes;
    }

    public void setCardSchemes(String cardSchemes) {
        this.cardSchemes = cardSchemes;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
    //    private String accountNo;


    private String cardType;


    private String cardSchemes;

    private String bank;
}
