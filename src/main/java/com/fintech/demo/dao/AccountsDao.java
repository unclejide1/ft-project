package com.fintech.demo.dao;

import com.fintech.demo.model.Accounts;

import java.util.List;
import java.util.Optional;


public interface AccountsDao extends CrudDao<Accounts, Long> {
    List<Accounts> getAllAccounts();
    long countAccounts();
    Accounts getCardByCardNumber(String accountNo);
    Optional<Accounts> findCardByCardNumber(String accountNo);
}
