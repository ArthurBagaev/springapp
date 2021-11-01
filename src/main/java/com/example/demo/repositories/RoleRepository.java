package com.example.demo.repositories;

import com.example.demo.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findOneByName(String theRoleName);
}
