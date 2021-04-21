package com.fintech.demo.dao;



import com.fintech.demo.model.Role;
import com.fintech.demo.model.enums.ERole;

import java.util.Optional;

public interface RoleDao extends CrudDao<Role, Long> {
    Optional<Role> findByRole(ERole role);
}
