package com.mitya.controller;

import com.mitya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class SimpleController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    String helloGet(Model model) {
        return "hello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String loginGet() {
        return "login";
    }


    @RequestMapping(value = "/insert_user", method = RequestMethod.GET)
    public String insertGet(Model model) {
        return "addUser";
    }

    @RequestMapping(value = "/insert_user", method = RequestMethod.POST)
    public String insertPost(@RequestParam("name") String name,
                             @RequestParam("login") String login, @RequestParam("password") String password) {
//        String role = "ROLE_USER";
//        Role userRole = new Role(user,role);
//        User user = new User(name, login, password);
//        Role userRole = new Role(user,role);
//
//        userService.insert();
        return "login";
    }
//    @RequestMapping(value = "/log", method = RequestMethod.POST)
//    String autho(@RequestParam("login") String login, @RequestParam("password") String password) {
//       List<User> users =  userService.getByLogin(login);
//       User user =  users.get(0);
//        if (user != null) {
//            return "redirect:/admin/users_all";
//        } else {
//            return null;
//        }
//    }
}
