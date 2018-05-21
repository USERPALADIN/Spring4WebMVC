package com.mitya.service;

import com.mitya.model.User;

import java.util.List;

public interface UserService {

     void delete(long id);

     void insert(User user);


     void update(User user);


     List<User> getAll();

     User getById(long id) ;

     User getByLogin(String login) ;

}
