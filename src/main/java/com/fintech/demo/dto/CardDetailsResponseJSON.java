package com.fintech.demo.dto;

import com.fintech.demo.model.enums.CardSchemes;
import com.fintech.demo.model.enums.CardType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDetailsResponseJSON {

//    private String accountNo;


    private String cardType;


    private String cardSchemes;

    private String bank;
}
