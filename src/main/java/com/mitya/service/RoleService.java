package com.mitya.service;

import com.mitya.model.Role;

import java.util.Collection;

public interface RoleService {
    void insert(Role role);

    Role getByName(String name);
    Collection<Role> getByRoleAll ();
}
