package com.mitya.service;

import com.mitya.dao.UserDao;

import com.mitya.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    UserDao userDao;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        User user = null;
//        List<User> users = userDao.getByLogin(login);
//        user = users.get(0);
//        if (user == null) {
//            throw new UsernameNotFoundException("User" + login + " not found");
//        }
//        //   UserDetails userDetails = (UserDetails) new User(user.getName(), user.getLogin(), user.getPassword(), user.getRole());
//        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
//        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User
//                (user.getLogin(), user.getPassword(), Arrays.asList(authority));
//        return userDetails;
//    }
//}
