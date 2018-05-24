package com.mitya.controller;

import com.mitya.model.Role;
import com.mitya.model.User;
import com.mitya.service.RoleServiceImpl;
import com.mitya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    String helloGet( ) {
        return "hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String loginGet(Model model)
    {
        return "login";
    }


    @RequestMapping(value = "/insert_user", method = RequestMethod.GET)
    public String insertGet(Model model) {
        return "addUser";
    }

    @RequestMapping(value = "/insert_user", method = RequestMethod.POST)
    public String insertPost(@RequestParam("name") String name,
                             @RequestParam("login") String login, @RequestParam("password") String password) {
        String name_Role = "ROLE_USER";
        Collection<Role> roles = new ArrayList<>();
        Role role =  roleServiceImpl.getByName(name_Role);
        roles.add(role);
        User user = new User(name, login, password, roles);

        userService.insert(user);
        return "login";
    }
}