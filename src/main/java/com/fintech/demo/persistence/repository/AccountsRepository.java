package com.fintech.demo.persistence.repository;

import com.fintech.demo.model.Accounts;
import com.fintech.demo.model.enums.RecordStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    List<Accounts> getAllByRecordStatus(RecordStatusConstant recordStatusConstant);
    
}
