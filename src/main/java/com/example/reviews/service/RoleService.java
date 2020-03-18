package com.example.reviews.service;

import com.example.reviews.model.Role;

public interface RoleService {

    Role save(Role role);
    Role getRoleByRoleName(String roleName);
}
