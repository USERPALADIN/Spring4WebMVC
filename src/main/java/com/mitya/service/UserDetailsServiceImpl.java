package com.mitya.service;

import com.mitya.dao.UserDao;
import com.mitya.dao.UserDaoImpl;
import com.mitya.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

//@Service
//    public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    UserDao userDao;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//// с помощью нашего сервиса UserService получаем User
//        User user = userDao.getByLogin(login);
//        // указываем роли для этого пользователя
//
////        Set<GrantedAuthority> roles = new HashSet();
////            roles.add(new SimpleGrantedAuthority(user.getRole()));
//
//
//        // на основании полученныйх даных формируем объект UserDetails
//        // который позволит проверить введеный пользователем логин и пароль
//        // и уже потом аутентифицировать пользователя
////        UserDetails userDetails =
////                new org.springframework.security.core.userdetails.User(user.getLogin(),
////                        user.getPassword(),
////                        roles);
//
//        return (UserDetails) user;
//    }
//}
