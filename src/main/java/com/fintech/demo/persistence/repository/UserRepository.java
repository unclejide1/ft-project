package com.fintech.demo.persistence.repository;

import com.fintech.demo.model.User;
import com.fintech.demo.model.enums.RecordStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findFirstByUsernameAndRecordStatus(String username, RecordStatusConstant statusConstant);
    Optional<User> findFirstByPhoneNumber(String phoneNumber);
    Boolean existsByUsernameAndRecordStatus(String username, RecordStatusConstant statusConstant);
}
