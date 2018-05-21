package com.mitya.service;

import com.mitya.dao.UserDao;
import com.mitya.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl  implements  UserService{
    @Autowired
    private UserDao userDao;


    public void delete(long id) {
        userDao.delete(id);
    }

    public void insert(User user) {
        userDao.insert(user);
    }


    public void update(User user) {
        userDao.update(user);
    }


    public List<User> getAll() {
        List<User> users = userDao.getAll();
        return users;
    }

    public User getById(long id) {
        User user = userDao.getById(id);
        return user;
    }

    public User getByLogin(String login) {
        User user = userDao.getByLogin(login);
        return user;
    }
}
