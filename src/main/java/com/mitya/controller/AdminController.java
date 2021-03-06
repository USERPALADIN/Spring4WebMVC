package com.mitya.controller;

import com.mitya.model.Role;
import com.mitya.model.User;
import com.mitya.service.RoleService;
import com.mitya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @RequestMapping(value = "/users_all", method = RequestMethod.GET)
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("allRoles", roleService.getByRoleAll());
        return "users_all";
    }

    @RequestMapping(value = "/delete_user/{userId}", method = RequestMethod.GET)
    public String deleteGet(@PathVariable("userId") long id) {
        userService.delete(id);
        return "redirect:/admin/users_all";
    }

    @RequestMapping(value = "/update_user/{userId}", method = RequestMethod.GET)
    public String updateGet(@PathVariable("userId") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getByRoleAll());
        return "update";
    }

    @RequestMapping(value = "/update_user", method = RequestMethod.POST)
    public String updatePost(@RequestParam("userId") long id, @RequestParam("name") String name,
                             @RequestParam("login") String login, @RequestParam("password") String password,
                             @RequestParam("roles") List<Role> roles) {
        List<Role> resultRoles = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            Role role = roleService.getByName(roles.get(i).getName());
            resultRoles.add(role);
        }
        User user = new User(name, login, password, resultRoles);
        user.setId(id);
        userService.update(user);

//        SecurityContextHolder.getContext()
//                .setAuthentication(new UsernamePasswordAuthenticationToken(
//                        SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
//                        SecurityContextHolder.getContext().getAuthentication().getCredentials(),
//                        resultRoles));
        return "redirect:/admin/users_all";
    }
}
