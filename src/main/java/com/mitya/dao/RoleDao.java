package com.mitya.dao;

import com.mitya.model.Role;

import java.util.Collection;

public  interface RoleDao {
    void insert(Role role);
    Role getByName (String name);
    Collection<Role> getByRoleAll ();
}
