package com.proton.services.role;

import com.proton.models.entities.Role;
import com.proton.models.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getRole(String roleName) {
        return roleRepository.findById(roleName).orElse(null);
    }
}
