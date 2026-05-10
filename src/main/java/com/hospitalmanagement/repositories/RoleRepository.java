package com.hospitalmanagement.repositories;

import com.hospitalmanagement.entities.Role;
import com.hospitalmanagement.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
    Role findByRoleType(RoleType roleType);
}
