package com.fintech.demo.dao;



import com.fintech.demo.model.User;

import java.util.Optional;

public interface UserDao extends CrudDao<User, Long> {
    User getUserByUsername(String username);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByPhoneNumber(String phoneNumber);
    Boolean existsByUsername(String username);
//    Page<User> getAppUsers(CustomerSearchDTO searchDTO, int startPage, int size);
}
