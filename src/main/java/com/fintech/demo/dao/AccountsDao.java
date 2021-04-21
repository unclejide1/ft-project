package com.fintech.demo.dao;

import com.fintech.demo.model.Accounts;

import java.util.List;


public interface AccountsDao extends CrudDao<Accounts, Long> {
    List<Accounts> getAllAccounts();
    long countAccounts();
}
