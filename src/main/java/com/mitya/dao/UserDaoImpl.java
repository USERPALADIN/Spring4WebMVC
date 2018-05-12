package com.mitya.dao;

import com.mitya.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository
@Transactional(readOnly = false)
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void delete(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void insert(User user) {
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
    public List<User> getByLogin(String login) {
        List<User> users = entityManager.createQuery("SELECT e FROM User e  WHERE login ='" + login + "'").getResultList();
        return users;
    }
}
