package com.fintech.demo.persistence.daoImpl;

import com.fintech.demo.dao.AccountsDao;
import com.fintech.demo.model.Accounts;
import com.fintech.demo.model.enums.RecordStatusConstant;
import com.fintech.demo.persistence.repository.AccountsRepository;

import javax.inject.Named;
import java.util.List;

@Named
public class AccountsDaoImpl extends CrudDaoImpl<Accounts, Long> implements AccountsDao {

    private final AccountsRepository repository;

    public AccountsDaoImpl(AccountsRepository  repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Accounts> getAllAccounts() {
        return repository.getAllByRecordStatus(RecordStatusConstant.ACTIVE);
    }

    @Override
    public long countAccounts() {
        return repository.count();
    }
}
