package com.mitya.service;

import com.mitya.dao.UserDao;
import com.mitya.model.Role;
import com.mitya.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final
    UserDao userDao;
    private final
    AuthenticationManager authManager;
    private final RoleService roleService;
    @Autowired
    public UserDetailsServiceImpl(UserDao userDao, AuthenticationManager authManager, RoleService roleService) {
        this.userDao = userDao;
        this.authManager = authManager;
        this.roleService = roleService;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        UserDetails user = userDao.getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User" + login + " not found");
        }
        return user;
    }

    public void authenticationByToken(User user, HttpServletRequest req, Collection<Role> roles) {
        SecurityContext sc = SecurityContextHolder.getContext();

        sc.setAuthentication(new UsernamePasswordAuthenticationToken(user,null,roles));
        HttpSession session = req.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
    }

}
