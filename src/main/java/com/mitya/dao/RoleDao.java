package com.mitya.dao;

import com.mitya.model.Role;

public  interface RoleDao {
    void insert(Role role);
    Role getByName (String name);
}
