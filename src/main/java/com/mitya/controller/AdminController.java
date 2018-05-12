package com.mitya.controller;

import com.mitya.entity.User;
import com.mitya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users_all", method = RequestMethod.GET)
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users_all";
    }

    @RequestMapping(value = "/insert_user", method = RequestMethod.GET)
    public String insertGet(Model model) {
        return "addUser";
    }

    @RequestMapping(value = "/insert_user", method = RequestMethod.POST)
    public String insertPost(@RequestParam("name") String name,
                             @RequestParam("login") String login, @RequestParam("password") String password) {
        String role = "user";
        userService.insert(new User(name, login, password, role));
        return "redirect:/admin/users_all";
    }

    @RequestMapping(value = "/delete_user/{userId}", method = RequestMethod.GET)
    public String deleteGer(@PathVariable("userId") long id) {
        userService.delete(id);
        return "redirect:/admin/users_all";
    }

    @RequestMapping(value = "/update_user/{userId}", method = RequestMethod.GET)
    public String updateGet(@PathVariable("userId") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);

        return "update";
    }

    @RequestMapping(value = "/update_user", method = RequestMethod.POST)
    public String updatePost(@RequestParam("userId") long id, @RequestParam("name") String name,
                             @RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("role") String role) {
        User user2 = new User(name, login, password, role);
        user2.setId(id);
        userService.update(user2);
        return "redirect:/admin/users_all";
    }
}
