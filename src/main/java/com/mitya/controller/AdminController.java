package com.mitya.controller;

import com.mitya.entity.User;
import com.mitya.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users_all", method = RequestMethod.GET)
    public String showUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users_all";
    }

    @RequestMapping(value = "/insert_user", method = RequestMethod.GET)
    public String insertGet(Model model) {
        return "addUser";
    }

    @RequestMapping(value = "insert_user", method = RequestMethod.POST)
    public String insertPost(@RequestParam("name") String name,
                             @RequestParam("login") String login, @RequestParam("password") String password) {
        String role = "user";
        userRepository.save(new User(name, login, password, role));
        return "redirect:/admin/users_all";
    }

    @RequestMapping(value = "/delete_user", method = RequestMethod.GET)
    public String insertGet(@RequestParam("userId") long id) {
        userRepository.delete(id);
        return "redirect:/admin/users_all";
    }
    @RequestMapping(value = "/update_user", method = RequestMethod.GET)
    public String updateGet(@RequestParam("userId") long id, Model model) {
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);

        return "update";
    }

    @RequestMapping(value = "/update_user", method = RequestMethod.POST)
    public String updatePost(@RequestParam("userId") long id, @RequestParam("name") String name,
                             @RequestParam("login") String login, @RequestParam("password") String password,
                             @RequestParam("role") String role) {
        User user = new User(name, login, password, role);
        user.setId(id);
        userRepository.save(user);

        return "redirect:/admin/users_all";
    }
}
