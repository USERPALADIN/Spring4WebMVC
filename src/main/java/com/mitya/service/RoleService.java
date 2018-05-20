package com.mitya.service;

import com.mitya.dao.RoleDao;
import com.mitya.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;
    public void insert(Role role) {
        roleDao.insert(role);
    }
    public Role getByName(String name) {

      Role role  = roleDao.getByName(name);
      return  role;

    }
}
