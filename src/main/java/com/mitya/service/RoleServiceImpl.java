package com.mitya.service;

import com.mitya.dao.RoleDao;
import com.mitya.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService {
    final
    RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void insert(Role role) {
        roleDao.insert(role);
    }

    public Role getByName(String name) {

        Role role = roleDao.getByName(name);
        return role;

    }

    @Override
    public Collection<Role> getByRoleAll() {
        return roleDao.getByRoleAll();
    }
}
