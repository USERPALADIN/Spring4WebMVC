package com.mitya.dao;

import com.mitya.entity.User;

import java.util.List;

public interface UserDao  // extends JpaRepository<User, Long>
 {
    void delete(long  id);
    void insert(User user);
    void update(User user);
   List getAll();
    User getById(long id);
}
