package com.fintech.demo.model;


import com.fintech.demo.model.enums.CardSchemes;
import com.fintech.demo.model.enums.CardType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Accounts extends AbstractBaseEntity<Long> {
    @Column(unique =  true, updatable = false, nullable = false)
    @NotBlank
    private String accountNo;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Enumerated(EnumType.STRING)
    private CardSchemes cardSchemes;

    private String bank;
}
