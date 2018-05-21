package com.mitya.dao;

import com.mitya.model.Role;
import com.mitya.model.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional(readOnly = false)
@ComponentScan("com.mitya")
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getByName(String name) {
        List<Role> roles = entityManager.createQuery("SELECT e FROM Role e  WHERE name ='" + name + "'").getResultList();
        return roles.get(0);
    }

    @Override
    public Collection<Role> getByRoleAll() {
        Collection <Role> roles = entityManager.createQuery("SELECT e FROM Role e ").getResultList();
        return  roles;
    }
}
