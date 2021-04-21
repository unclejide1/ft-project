package com.fintech.demo.persistence.daoImpl;



import com.fintech.demo.dao.RoleDao;
import com.fintech.demo.model.Role;
import com.fintech.demo.model.enums.ERole;
import com.fintech.demo.persistence.repository.RoleRepository;

import javax.inject.Named;
import java.util.Optional;

@Named
public class RoleDaoImpl extends CrudDaoImpl<Role, Long> implements RoleDao {

    private final RoleRepository repository;


    public RoleDaoImpl(RoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Optional<Role> findByRole(ERole role) {
        return repository.findByName(role);
    }


}
