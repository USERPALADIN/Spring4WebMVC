package com.mitya.controller;

import com.mitya.entity.User;
import com.mitya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class SimpleController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/log", method = RequestMethod.GET)
    String authoGet(Model model) {
       return  "login";
    }
    @RequestMapping(value = "/log", method = RequestMethod.POST)
    String autho(@RequestParam("login") String login, @RequestParam("password") String password) {
       List<User> users =  userService.getByLogin(login);
       User user =  users.get(0);
        if (user != null) {
            return "redirect:/admin/users_all";
        } else {
            return null;
        }
    }
}
