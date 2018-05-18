package com.mitya.service;

import com.mitya.dao.UserDao;
import com.mitya.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user  = userDao.getByLogin(login);
//        if (user == null) {
//            throw new UsernameNotFoundException("User" + login + " not found");
//        }
//        //   UserDetails userDetails = (UserDetails) new User(user.getName(), user.getLogin(), user.getPassword(), user.getRole());
//        List<GrantedAuthority> authority = buildUserAuthority(user.getUserRole());
        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User
                (user.getLogin(), user.getPassword(), null);
        return userDetails;
    }
//    private List<GrantedAuthority> buildUserAuthority(Set<Role> roles) {
//
//        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
//
//        // Build user's authorities
//        for (Role role : roles) {
//            setAuths.add(new SimpleGrantedAuthority(role.getRole()));
//        }
//        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
//
//        return Result;
//    }
}
