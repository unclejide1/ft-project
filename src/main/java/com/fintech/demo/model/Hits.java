package com.fintech.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hits")
public class Hits extends AbstractBaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    private Accounts account;
}
