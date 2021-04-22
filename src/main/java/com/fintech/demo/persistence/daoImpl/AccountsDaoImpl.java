package com.fintech.demo.persistence.daoImpl;

import com.fintech.demo.dao.AccountsDao;
import com.fintech.demo.exceptions.BusinessLogicConflictException;
import com.fintech.demo.exceptions.NotFoundException;
import com.fintech.demo.model.Accounts;
import com.fintech.demo.model.enums.RecordStatusConstant;
import com.fintech.demo.persistence.repository.AccountsRepository;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Accounts getCardByCardNumber(String accountNo) {
        return findCardByCardNumber(accountNo).orElseThrow(() -> new NotFoundException("Not Found. Details for card with No: " + accountNo));
    }

    @Override
    public Optional<Accounts> findCardByCardNumber(String accountNo) {
        return repository.findFirstByAccountNoAndRecordStatus(accountNo, RecordStatusConstant.ACTIVE);
    }
}
