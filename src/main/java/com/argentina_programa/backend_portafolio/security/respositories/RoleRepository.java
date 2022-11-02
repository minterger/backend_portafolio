package com.argentina_programa.backend_portafolio.security.respositories;

import java.util.Optional;

import com.argentina_programa.backend_portafolio.security.entities.Role;
import com.argentina_programa.backend_portafolio.security.enums.RoleList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleList roleName);

}
