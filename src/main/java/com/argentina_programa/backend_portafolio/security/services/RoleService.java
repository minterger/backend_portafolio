package com.argentina_programa.backend_portafolio.security.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.argentina_programa.backend_portafolio.security.entities.Role;
import com.argentina_programa.backend_portafolio.security.enums.RoleList;
import com.argentina_programa.backend_portafolio.security.respositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> getByRoleName(RoleList roleName) {
        return roleRepository.findByRoleName(roleName);
    }

}
