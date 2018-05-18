package com.mitya.dao;

import com.mitya.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = false)
@ComponentScan("com.mitya")
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void delete(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void insert(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("SELECT e FROM User e").getResultList();
    }

    @Override
    public User getById(long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public User getByLogin(String login) {
        List<User> users = entityManager.createQuery("SELECT e FROM User e  WHERE login ='" + login + "'").getResultList();

        return users.get(0);
    }

}
