package com.mitya.service;

import com.mitya.dao.UserDao;
import com.mitya.model.Role;
import com.mitya.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDetails user  =  userDao.getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User" + login + " not found");
        }
        //   UserDetails userDetails = (UserDetails) new User(user.getName(), user.getLogin(), user.getPassword(), user.getRole());
//        List<GrantedAuthority> authority = buildUserAuthority(user.getRoles());
//        UserDetails userDetails =  new org.springframework.security.core.userdetails.User
//                (user.getLogin(), user.getPassword(), authority);

        return user;
    }
//    private List<GrantedAuthority> buildUserAuthority(Collection<Role> roles) {
//
//        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
//
//        // Build user's authorities
//        for (Role role : roles) {
//            setAuths.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
//
//        return Result;
//    }
}
