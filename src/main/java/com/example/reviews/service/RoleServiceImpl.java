package com.example.reviews.service;

import com.example.reviews.model.Role;
import com.example.reviews.repository.RoleRepository;

import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleRepository.getRoleByRoleName(roleName);
    }
}
